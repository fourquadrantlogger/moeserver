package xyz.moeserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by timeloveboy on 16/5/30.
 */
public class Routers implements HttpHandler {
    private ConcurrentMap<Router, Class<? extends DefaultHandle>> routermap;
    private String ModulePath;

    public Routers(String Path) {
        ModulePath = Path;
        routermap = new ConcurrentHashMap<>();
    }

    public void handle(HttpExchange exchange) throws IOException {
        HttpRequest req = new HttpRequest(exchange);
        HttpResponse resp = new HttpResponse(exchange);
        String classname = req.url.getPath().substring(1).replace('/', '.');
        Router router = new Router(classname, req.requestMethod);
        Class c[] = new Class[2];
        c[0] = req.getClass();
        c[1] = resp.getClass();
        try {
            if (routermap.containsKey(router)) {
                Class modulehandle = routermap.get(router);
                Object o = modulehandle.newInstance();
                Method method = modulehandle.getDeclaredMethod(req.requestMethod, c);
                method.invoke(o, req, resp);
            } else {
                Class modulehandle = Class.forName(ModulePath + "." + classname);
                if (DefaultHandle.class.isAssignableFrom(modulehandle)) {
                    Object o = modulehandle.newInstance();
                    Method method = modulehandle.getDeclaredMethod(req.requestMethod, c);
                    method.invoke(o, req, resp);
                } else {
                    DefaultHandle handle = new DefaultHandle();
                    Method method = handle.getClass().getDeclaredMethod(req.requestMethod, c);
                    method.invoke(handle, req, resp);
                }
                routermap.put(router, modulehandle);
            }

        } catch (ClassNotFoundException e) {
            resp.write("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}