package com.github.timeloveboy.moeserver.ServerDriver.jetty;

import com.github.timeloveboy.moeserver.IHttpRequest;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.server.Request;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class HttpRequest extends IHttpRequest {
    private Request baseRequest;
    private HttpServletRequest httpServletRequest;

    public HttpRequest(String s, Request baseRequest, HttpServletRequest httpServletRequest) {
        this.baseRequest = baseRequest;
        this.httpServletRequest = httpServletRequest;
        url = baseRequest.getUri();
        remoteAddress = baseRequest.getRemoteInetSocketAddress();
        requestMethod = baseRequest.getMethod();

        try {
            body = baseRequest.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public InputStream getBody() {
        return (InputStream) body;
    }

    @Override
    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public URI getUrl() {
        try {
            return new URI(url.getScheme(), url.getHost(), url.getPath(), url.getFragment());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> getHeaders() {
        if (headers != null) {
            return headers;
        }
        headers = new HashMap<>();
        Enumeration<String> keys = baseRequest.getHeaderNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            headers.put(key, baseRequest.getHeader(key));
        }
        return headers;
    }

    @Override
    public Map<String, String> getCookies() {
        if (cookies != null) {
            return cookies;
        }
        String cs = getHeaders().get("Cookie");
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
    public ServletInputStream body;
    public String requestMethod;
    public HttpURI url;
    public Map<String, String> headers;
    public Map<String, String> cookies;
}
