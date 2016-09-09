package xyz.moeserver;

import java.io.Serializable;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class Router implements Serializable {
    public String Method;
    public String UrlPath;

    public Router(String method, String urlPath) {
        Method = method;
        UrlPath = urlPath;
    }
}
