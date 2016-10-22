package com.github.timeloveboy.moeserver.ServerDriver.sun;

import com.github.timeloveboy.moeserver.IHttpResponse;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpResponse extends IHttpResponse {
    private HttpExchange exchange;
    private Integer code = 400;

    public HttpResponse(HttpExchange exchange) throws IOException {
        this.exchange = exchange;
    }

    @Override
    public HttpResponse code(Integer responsecode) throws Exception {
        code = responsecode;
        return this;
    }

    @Override
    public HttpResponse header(String key, String value) {
        if (exchange.getResponseHeaders().containsKey(key)) {
            List<String> values = new LinkedList<>();
            values.add(value);
            exchange.getResponseHeaders().replace(key, values);
        } else {
            exchange.getResponseHeaders().add(key, value);
        }
        return this;
    }

    @Override
    public HttpResponse setcookie(String key, String value) {
        String cookie = " " + key + "=" + value;
        List<String> values = new LinkedList<>();
        if (!exchange.getResponseHeaders().containsKey("Set-Cookie")) {
            exchange.getResponseHeaders().add("Set-Cookie", cookie);
        } else {
            values = exchange.getResponseHeaders().get("Set-Cookie");
        }

        values.add(cookie);
        exchange.getResponseHeaders().replace("Set-Cookie", values);

        return this;
    }

    @Override
    public void write(String data) throws IOException {
        exchange.sendResponseHeaders(code, 0);
        exchange.getResponseBody().write(data.getBytes());
        exchange.getResponseBody().close();
    }


    @Override
    public void write(byte[] data) throws IOException {
        exchange.sendResponseHeaders(code, 0);
        exchange.getResponseBody().write(data);
        exchange.getResponseBody().close();
    }

    @Override
    public void close() throws IOException {
        exchange.getResponseBody().close();
    }

}
