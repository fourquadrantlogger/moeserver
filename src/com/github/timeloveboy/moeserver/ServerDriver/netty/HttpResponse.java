package com.github.timeloveboy.moeserver.ServerDriver.netty;

import com.github.timeloveboy.moeserver.IHttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpResponse extends IHttpResponse {
    private ChannelHandlerContext ctx;
    private Integer code = 400;
    private FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);

    public HttpResponse(ChannelHandlerContext ctx) throws IOException {
        this.ctx = ctx;
    }

    @Override
    public HttpResponse code(Integer responsecode) throws Exception {
        code = responsecode;
        return this;
    }

    @Override
    public HttpResponse header(String key, String value) {
        if (response.headers().contains(key)) {
            List<String> values = new LinkedList<>();
            values.add(value);
            response.headers().set(key, values);
        } else {
            response.headers().add(key, value);
        }
        return this;
    }

    @Override
    public HttpResponse setcookie(String key, String value) {
        String cookie = " " + key + "=" + value;
        List<String> values = new LinkedList<>();
        if (!response.headers().contains("Set-Cookie")) {
            response.headers().add("Set-Cookie", cookie);
        } else {
            values = Arrays.asList(response.headers().get("Set-Cookie").split(";"));
        }

        values.add(cookie);
        response.headers().set("Set-Cookie", values);

        return this;
    }

    @Override
    public void write(String data) throws IOException {
        response.content().writeBytes(Unpooled.wrappedBuffer(data.getBytes("UTF-8")));
        response.setStatus(new HttpResponseStatus(code, ""));
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

        ctx.write(response);
        ctx.flush();
        ctx.close();
    }


    @Override
    public void write(byte[] data) throws IOException {
        response.content().writeBytes(data);
        response.setStatus(new HttpResponseStatus(code, ""));
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

        ctx.write(response);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void write(InputStream in) throws IOException {

        response.setStatus(new HttpResponseStatus(code, ""));
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

        byte[] b = new byte[1024];
        int bytesRead = in.read(b);
        while (bytesRead != -1) {
            response.content().writeBytes(b, 0, bytesRead);
            bytesRead = in.read(b);
        }

        ctx.write(response);
        ctx.flush();
        ctx.close();

    }
    @Override
    public void close() throws IOException {
        ctx.close();
    }

}
