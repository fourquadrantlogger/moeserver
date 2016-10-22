package com.github.timeloveboy.moeserver;


/**
 * Created by timeloveboy on 16-9-10.
 */
public class DefaultHandle {
    public DefaultHandle() {

    }

    public void GET(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).close();
    }

    public void POST(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).close();
    }

    public void PUT(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).close();
    }

    public void DELETE(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).close();
    }

    public void TRACE(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).close();
    }

    public void HEAD(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).close();
    }
}
