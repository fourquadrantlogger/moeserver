package com.github.timeloveboy.moeserver.ServerDriver.jetty;

import com.github.timeloveboy.moeserver.IHttpServer;
import com.github.timeloveboy.moeserver.ServerDriver.Dispatcher;
import org.eclipse.jetty.server.Server;

import java.net.InetSocketAddress;

/**
 * Created by timeloveboy on 2016/10/22.
 */
public class jettyServer implements IHttpServer {

    private InetSocketAddress addr;
    private Server server;

    @Override
    public void create(InetSocketAddress addr) {
        this.addr = addr;

    }

    @Override
    public void createContext(String ModulePath) {
        Dispatcher.setModulePath(ModulePath);
    }

    @Override
    public void start() {
        try {
            server = new Server(addr);
            server.setHandler(new jettyRouterDispatcher());
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
