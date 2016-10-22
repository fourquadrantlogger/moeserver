package com.github.timeloveboy.moeserver.ServerDriver.sun;

import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import com.github.timeloveboy.moeserver.Router;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by timeloveboy on 16/5/30.
 */
public class sunRouterDispatcher implements HttpHandler {
    private ConcurrentMap<Router, Class<? extends DefaultHandle>> routermap;
    private String ModulePath;

    public sunRouterDispatcher(String Path) {
        ModulePath = Path;
        routermap = new ConcurrentHashMap<>();
    }

    public void handle(HttpExchange exchange) throws IOException {
        IHttpRequest req = new HttpRequest(exchange);
        IHttpResponse resp = new HttpResponse(exchange);
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