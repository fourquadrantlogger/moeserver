package com.github.timeloveboy.moeserver;

import java.io.IOException;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class DefaultHandle {
    public DefaultHandle() {

    }

    public void GET(HttpRequest req, HttpResponse resp) throws IOException {
        resp.code(404).close();
    }

    public void POST(HttpRequest req, HttpResponse resp) throws IOException {
        resp.code(404).close();
    }

    public void PUT(HttpRequest req, HttpResponse resp) throws IOException {
        resp.code(404).close();
    }

    public void DELETE(HttpRequest req, HttpResponse resp) throws IOException {
        resp.code(404).close();
    }

    public void TRACE(HttpRequest req, HttpResponse resp) throws IOException {
        resp.code(404).close();
    }

    public void HEAD(HttpRequest req, HttpResponse resp) throws IOException {
        resp.code(404).close();
    }
}
