package com.moe;

import xyz.moeserver.Server;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class app {
    public static void main(String[] args) throws Exception {
        Server s = Server.getInstance().RegisterModulePath("com.moe.module").SetPort(8090);
        s.Run();
    }
}
