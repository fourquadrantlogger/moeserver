package xyz.moexiang;


import com.sun.net.httpserver.HttpServer;


import java.net.InetSocketAddress;

import java.util.concurrent.Executors;


public class Main {//这里必须要有一个Main类,否则会报找不到主类的错误

    public static void main(String[] args) throws Exception{
        InetSocketAddress addr = new InetSocketAddress(9090);//
        HttpServer server = HttpServer.create(addr, 0);
        //server.createContext("/index",InderHandler());

        //前缀
        //rename后，这里所有调用到的都被呗自动修改
        server.createContext("/api", new MyHandler());//所有的路由,都交给Mainhandler处理
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 9090 ...");

    }

}