package xyz.moeserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpRequest {
    public HttpRequest(HttpExchange exchange) {
        remoteAddress = exchange.getRemoteAddress();
        requestMethod = exchange.getRequestMethod();
        url = exchange.getRequestURI();//获取url链接饿信息
        headers = exchange.getRequestHeaders();
        body = exchange.getRequestBody();
    }

    public InetSocketAddress remoteAddress;
    public InputStream body;
    public String requestMethod;
    public URI url;
    public Headers headers;
}
