package webdemo.routers;

import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import utils.Log;
import webdemo.Main;

import java.util.Date;

/**
 * Created by timeloveboy on 2016/10/23.
 */
public class I extends DefaultHandle {
    @Override
    public void GET(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(200).write("hello");
        Main.count++;
        if (Main.count % 1000 == 0) {
            Main.count = 1;
            Log.v(new Date().getTime() / 1000);
        }
    }
}
