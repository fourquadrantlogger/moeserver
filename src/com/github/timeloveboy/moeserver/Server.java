package com.github.timeloveboy.moeserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

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
    private HttpServer server;

    public Server SetPort(int port) {
        addr = new InetSocketAddress(port);//
        return this;
    }

    public Server RegisterModulePath(String Path) {
        ModulePath = Path;
        return this;
    }

    public void Run() throws IOException {
        server = HttpServer.create(addr, 0);
        server.createContext("/", new Routers(ModulePath));//所有的路由,都交给Mainhandler处理
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }
}
