package com.github.timeloveboy.moeserver.ServerDriver.jetty;

import com.github.timeloveboy.moeserver.Dispatcher;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timeloveboy on 2016/10/22.
 */

public class jettyRouterDispatcher extends AbstractHandler {
    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
        IHttpRequest req = new HttpRequest(s, baseRequest, httpServletRequest);
        IHttpResponse resp = new HttpResponse(response);
        baseRequest.setHandled(true);
        Dispatcher.dispatch(req, resp);
    }
}