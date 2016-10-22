package com.github.timeloveboy.moeserver.ServerDriver.netty;

import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import com.github.timeloveboy.moeserver.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by timeloveboy on 2016/10/22.
 */

public class nettyRouterDispatcher extends ChannelInboundHandlerAdapter {

    private ConcurrentMap<Router, Class<? extends DefaultHandle>> routermap;
    private String ModulePath;

    public nettyRouterDispatcher(String modulePath) {
        ModulePath = modulePath;
        routermap = new ConcurrentHashMap<>();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            IHttpRequest req = new com.github.timeloveboy.moeserver.ServerDriver.netty.HttpRequest(ctx, msg);
            IHttpResponse resp = new com.github.timeloveboy.moeserver.ServerDriver.netty.HttpResponse(ctx);

            String classname = req.getUrl().getPath().substring(1).replace('/', '.');
            Router router = new Router(classname, req.getRequestMethod());

            Class c[] = new Class[2];
            c[0] = IHttpRequest.class;
            c[1] = IHttpResponse.class;
            try {
                if (routermap.containsKey(router)) {
                    Class modulehandle = routermap.get(router);
                    Object o = modulehandle.newInstance();

                    Method method = modulehandle.getDeclaredMethod(req.getRequestMethod(), c);
                    method.invoke(o, req, resp);
                } else {
                    Class modulehandle = Class.forName(ModulePath + "." + classname);
                    if (DefaultHandle.class.isAssignableFrom(modulehandle)) {
                        Object o = modulehandle.newInstance();


                        Method method = modulehandle.getDeclaredMethod(req.getRequestMethod(), c);
                        method.invoke(o, req, resp);
                    } else {
                        DefaultHandle handle = new DefaultHandle();

                        Method method = handle.getClass().getDeclaredMethod(req.getRequestMethod(), c);
                        method.invoke(handle, req, resp);
                    }
                    routermap.put(router, modulehandle);
                }

                return;

            } catch (ClassNotFoundException e) {
                DefaultHandle handle = new DefaultHandle();

                try {
                    Method method = handle.getClass().getDeclaredMethod(req.getRequestMethod(), c);
                    method.invoke(handle, req, resp);
                } catch (Exception ee) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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