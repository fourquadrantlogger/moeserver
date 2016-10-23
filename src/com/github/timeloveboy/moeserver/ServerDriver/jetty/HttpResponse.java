package com.github.timeloveboy.moeserver.ServerDriver.jetty;

import com.github.timeloveboy.moeserver.IHttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpResponse extends IHttpResponse {

    private Integer code = 400;
    private HttpServletResponse response;

    public HttpResponse(HttpServletResponse resp) throws IOException {
        response = resp;
    }

    @Override
    public HttpResponse code(Integer responsecode) throws Exception {
        code = responsecode;
        return this;
    }

    @Override
    public HttpResponse header(String key, String value) {
        response.setHeader(key, value);
        return this;
    }

    @Override
    public HttpResponse setcookie(String key, String value) {
        response.addCookie(new Cookie(key, value));
        return this;
    }

    @Override
    public void write(String data) throws IOException {
        response.setStatus(code);
        response.getOutputStream().write(data.getBytes());
        response.flushBuffer();
        response.getOutputStream().close();
    }


    @Override
    public void write(byte[] data) throws IOException {
        response.setStatus(code);
        response.getOutputStream().write(data);
        response.flushBuffer();
        response.getOutputStream().close();
    }

    @Override
    public void close() throws IOException {
        response.getOutputStream().close();
    }

}
