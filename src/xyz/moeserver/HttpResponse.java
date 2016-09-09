package xyz.moeserver;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpResponse {
    private HttpExchange exchange;
    private Integer code = 400;

    public HttpResponse(HttpExchange exchange) throws IOException {
        this.exchange = exchange;
    }

    public HttpResponse code(Integer responsecode) throws IOException {
        code = responsecode;
        return this;
    }

    ;

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

    ;

    public void write(String data) throws IOException {
        exchange.sendResponseHeaders(code, 0);
        exchange.getResponseBody().write(data.getBytes());
        exchange.getResponseBody().close();
    }

    ;

    public void write(byte[] data) throws IOException {
        exchange.sendResponseHeaders(code, 0);
        exchange.getResponseBody().write(data);
        exchange.getResponseBody().close();
    }

    ;

    public void close() throws IOException {
        exchange.getResponseBody().close();
    }

    ;
}
