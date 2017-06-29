package com.github.timeloveboy.moeserver;

import java.net.InetSocketAddress;

/**
 * Created by timeloveboy on 2016/10/22.
 */
public interface IHttpServer {
    public void create(InetSocketAddress addr);

    public void createContext(String ModulePath);

    public void start();
}
