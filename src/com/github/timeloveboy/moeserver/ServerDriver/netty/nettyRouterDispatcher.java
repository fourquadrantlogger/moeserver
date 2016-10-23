package com.github.timeloveboy.moeserver.ServerDriver.netty;

import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import com.github.timeloveboy.moeserver.ServerDriver.Dispatcher;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by timeloveboy on 2016/10/22.
 */

public class nettyRouterDispatcher extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            IHttpRequest req = new com.github.timeloveboy.moeserver.ServerDriver.netty.HttpRequest(ctx, msg);
            IHttpResponse resp = new com.github.timeloveboy.moeserver.ServerDriver.netty.HttpResponse(ctx);
            Dispatcher.dispatch(req, resp);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}