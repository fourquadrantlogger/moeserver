package webdemo;

import com.github.timeloveboy.moeserver.Server;
import com.github.timeloveboy.moeserver.ServerDriver.netty.nettyServer;

import java.util.Date;

public class Main {
    public static int count = 1;
    public static Date t=new Date();
    public static void main(String[] args) throws Exception {
        Server s = Server.getInstance();

        s.RegisterDriver(new nettyServer().setBufMax(1024 * 1));
        //s.RegisterDriver(new sunServer());
        //s.RegisterDriver(new jettyServer());
        s.RegisterModulePath("webdemo.routers").Static("/static", "/CODE/github.com/timeloveboy/moeserver").SetPort(8098);
        s.Run();
    }
}
