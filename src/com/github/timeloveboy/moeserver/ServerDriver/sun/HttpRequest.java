package com.github.timeloveboy.moeserver.ServerDriver.sun;

import com.github.timeloveboy.moeserver.IHttpRequest;
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
public class HttpRequest extends IHttpRequest {

    public HttpRequest(HttpExchange exchange) {
        remoteAddress = exchange.getRemoteAddress();
        requestMethod = exchange.getRequestMethod();
        url = exchange.getRequestURI();//获取url链接饿信息
        headers = exchange.getRequestHeaders();
        List<String> ls = headers.get("Cookie");
        cookies = new HashMap<>();
        if (ls != null)
        for (int i = 0; i < ls.size(); i++) {
            String[] s = ls.get(i).split("=");
            if (s.length == 2) {
                cookies.put(s[0], s[1]);
            }
        }
        body = exchange.getRequestBody();
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public URI getUrl() {
        return url;
    }

    @Override
    public Headers getHeaders() {
        return headers;
    }

    @Override
    public Map<String, String> getCookies() {
        return cookies;
    }

    public final InetSocketAddress remoteAddress;
    public final InputStream body;
    public final String requestMethod;
    public final URI url;
    public final Headers headers;
    public final Map<String, String> cookies;
}
