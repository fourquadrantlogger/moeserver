package webdemo;

import com.github.timeloveboy.moeserver.Server;
import com.github.timeloveboy.moeserver.ServerDriver.netty.nettyServer;

public class Main {
    public static int count = 1;

    public static void main(String[] args) throws Exception {
        Server s = Server.getInstance();

        s.RegisterDriver(new nettyServer().setBufMax(1024 * 10));
        //s.RegisterDriver(new sunServer());
        s.RegisterModulePath("webdemo.routers").SetPort(8090);
        s.Run();
    }
}
