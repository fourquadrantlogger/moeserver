package com.github.timeloveboy.moeserver.ServerDriver.netty;

import com.github.timeloveboy.moeserver.IHttpRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpRequest extends IHttpRequest {

    public HttpRequest(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof io.netty.handler.codec.http.FullHttpRequest) {
            io.netty.handler.codec.http.FullHttpRequest request = (io.netty.handler.codec.http.FullHttpRequest) msg;
            try {
                url = new URI(request.getUri());
                requestMethod = request.getMethod().name();
                String clientIP = request.headers().get("X-Forwarded-For");
                if (clientIP == null) {
                    remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                }
                buf = request.content();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public InputStream getBody() {
        byte[] bs = new byte[buf.readableBytes()];
        buf.readBytes(bs);
        InputStream body = new ByteArrayInputStream(bs);
        return body;
    }

    @Override
    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public URI getUrl() {
        return url;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        for (Map.Entry i : httpheaders) {
            headers.put(i.getKey().toString(), i.getValue().toString());
        }
        return headers;
    }

    @Override
    public Map<String, String> getCookies() {
        if (cookies != null) {
            return cookies;
        }
        String cs = httpheaders.get("Cookie");
        cookies = new HashMap<>();
        if (cs != null)
            for (int i = 0; i < cs.split(";").length; i++) {
                String[] s = cs.split("")[i].split("=");
                if (s.length == 2) {
                    cookies.put(s[0], s[1]);
                }
            }
        return cookies;
    }

    public InetSocketAddress remoteAddress;
    public ByteBuf buf;
    public String requestMethod;
    public URI url;
    public HttpHeaders httpheaders;
    public Map<String, String> cookies;
}
