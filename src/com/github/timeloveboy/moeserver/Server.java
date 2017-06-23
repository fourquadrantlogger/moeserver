package com.github.timeloveboy.moeserver;


import com.github.timeloveboy.utils.Log;
import com.github.timeloveboy.utils.Printer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class Server {
    private static Server ourInstance = new Server();
    private String ModulePath = "";
    private InetSocketAddress addr;
    private IHttpServer server;

    private Server() {
    }

    public static Server getInstance() {
        return ourInstance;
    }

    public void RegisterDriver(IHttpServer s) throws Exception {
        if (s != null) {
            server = s;
        } else {
            throw new Exception("Driver Cann't ==null");
        }
    }

    public Server Static(String staticurlroot, String localpathroot) {
        Dispatcher.staticFileHandle = new StaticFileHandle(staticurlroot, localpathroot);
        return this;
    }
    public Server SetPort(int port) {
        addr = new InetSocketAddress(port);//
        return this;
    }

    public Server RegisterModulePath(String Path) {
        ModulePath = Path;
        return this;
    }

    public void Run() throws IOException {
        new Printer().Print();
        server.create(addr);
        server.createContext(ModulePath);
        Log.v(new Date(), " Running at:", addr);
        server.start();

    }

}
