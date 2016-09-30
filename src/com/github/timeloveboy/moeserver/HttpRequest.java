package com.github.timeloveboy.moeserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpRequest {
    public HttpRequest(HttpExchange exchange) {
        remoteAddress = exchange.getRemoteAddress();
        requestMethod = exchange.getRequestMethod();
        url = exchange.getRequestURI();//获取url链接饿信息
        headers = exchange.getRequestHeaders();
        List<String> ls = headers.get("Cookie");
        cookies = new HashMap<>();
        for (int i = 0; i < ls.size(); i++) {
            String[] s = ls.get(i).split("=");
            if (s.length == 2) {
                cookies.put(s[0], s[1]);
            }
        }
        body = exchange.getRequestBody();
    }

    public final InetSocketAddress remoteAddress;
    public final InputStream body;
    public final String requestMethod;
    public final URI url;
    public final Headers headers;
    public final Map<String, String> cookies;
}
