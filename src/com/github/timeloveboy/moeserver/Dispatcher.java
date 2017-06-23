package com.github.timeloveboy.moeserver;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by timeloveboy on 2016/10/23.
 */
public class Dispatcher {
    public static StaticFileHandle staticFileHandle;
    private static ConcurrentMap<Router, Class<? extends DefaultHandle>> routermap = new ConcurrentHashMap<>();
    private static String ModulePath;

    public static String getModulePath() {
        return ModulePath;
    }

    public static void setModulePath(String modulePath) {
        ModulePath = modulePath;
    }

    static boolean EsixtClass(String classname) {
        try {
            Class modulehandle = Class.forName(classname);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void dispatch(IHttpRequest req, IHttpResponse resp) {
        String path = req.getUrl().getPath();
        if (staticFileHandle != null && path.substring(0, staticFileHandle.getStaticurlroot().length()).equals(staticFileHandle.getStaticurlroot())) {
            try {
                staticFileHandle.GET(req, resp);
            } catch (Exception e) {
                try {
                    staticFileHandle.NOTFOUNT(req, resp);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        String classname = path.substring(1).replace('/', '.');
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
            } else if (EsixtClass(ModulePath + "." + classname)) {

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
            } else if (EsixtClass(ModulePath + "." + classname + ".index")) {

                Class modulehandle = Class.forName(ModulePath + "." + classname + ".index");
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
            } else if (classname.equals("")) {

                Class modulehandle = Class.forName(ModulePath + ".index");
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
