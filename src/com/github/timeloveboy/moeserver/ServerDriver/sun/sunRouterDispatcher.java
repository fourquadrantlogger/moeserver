package com.github.timeloveboy.moeserver.ServerDriver.sun;

import com.github.timeloveboy.moeserver.Dispatcher;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by timeloveboy on 16/5/30.
 */
public class sunRouterDispatcher implements HttpHandler {

    public sunRouterDispatcher() {
    }

    public void handle(HttpExchange exchange) throws IOException {
        IHttpRequest req = new HttpRequest(exchange);
        IHttpResponse resp = new HttpResponse(exchange);
        Dispatcher.dispatch(req, resp);
    }

}