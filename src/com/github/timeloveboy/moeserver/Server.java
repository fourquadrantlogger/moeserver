package com.github.timeloveboy.moeserver;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class Server {
    private static Server ourInstance = new Server();

    public static Server getInstance() {
        return ourInstance;
    }

    private Server() {
    }

    private String ModulePath = "";
    private InetSocketAddress addr;
    private IHttpServer server;

    public void RegisterDriver(IHttpServer s) throws Exception {
        if (s != null) {
            server = s;
        } else {
            throw new Exception("Driver Cann't ==null");
        }
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
        server.create(addr);
        server.createContext(ModulePath);
        server.start();

    }
}
