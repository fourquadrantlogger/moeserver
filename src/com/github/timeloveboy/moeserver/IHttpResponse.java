package com.github.timeloveboy.moeserver;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by timeloveboy on 2016/10/22.
 */
public class IHttpResponse {
    public IHttpResponse code(Integer responsecode) throws Exception {
        return null;
    }

    ;

    public IHttpResponse header(String key, String value) throws Exception {
        return null;
    }

    ;

    public IHttpResponse setcookie(String key, String value) throws Exception {
        return null;
    }

    ;

    public void write(String data) throws IOException {
    }

    ;

    public void write(byte[] data) throws IOException {
    }

    public void write(InputStream data) throws IOException {

    }
    ;

    public void close() throws IOException {
    }

    ;

}
