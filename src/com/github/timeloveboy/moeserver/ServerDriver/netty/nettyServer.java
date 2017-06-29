package com.github.timeloveboy.moeserver.ServerDriver.netty;

import com.github.timeloveboy.moeserver.Dispatcher;
import com.github.timeloveboy.moeserver.IHttpServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.net.InetSocketAddress;

/**
 * Created by timeloveboy on 2016/10/22.
 */
public class nettyServer implements IHttpServer {
    private int BufMax = 1024 * 1024 * 10;
    private InetSocketAddress addr;

    public int getBufMax() {
        return BufMax;
    }

    public nettyServer setBufMax(int bufMax) {
        BufMax = bufMax;
        return this;
    }

    @Override
    public void create(InetSocketAddress addr) {
        this.addr = addr;
    }

    @Override
    public void createContext(String ModulePath) {
        Dispatcher.setModulePath(ModulePath);
    }

    @Override
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrapserver = new ServerBootstrap();
            bootstrapserver.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            ch.pipeline().addLast(new HttpObjectAggregator(BufMax));
                            ch.pipeline().addLast(new nettyRouterDispatcher());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = bootstrapserver.bind(addr).sync();

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
