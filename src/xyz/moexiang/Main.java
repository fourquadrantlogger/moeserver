package xyz.moexiang;


import com.sun.net.httpserver.HttpServer;


import java.net.InetSocketAddress;

import java.util.concurrent.Executors;


public class Main {//这里必须要有一个Main类,否则会报找不到主类的错误

    public static void main(String[] args) throws Exception{
        InetSocketAddress addr = new InetSocketAddress(9090);
        HttpServer server = HttpServer.create(addr, 0);

        server.createContext("/", new MainHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 9090 ...");

    }

}