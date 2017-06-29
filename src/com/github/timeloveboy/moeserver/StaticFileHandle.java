package com.github.timeloveboy.moeserver;


import com.github.timeloveboy.utils.FileUtil;

import java.io.File;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class StaticFileHandle {
    boolean handlestatic = false;
    String staticurlroot;
    String localpathroot;

    public StaticFileHandle(String staticurlroot, String localpathroot) {
        this.staticurlroot = staticurlroot;
        this.localpathroot = localpathroot;
        handlestatic = true;
    }

    public boolean isHandlestatic() {
        return handlestatic;
    }

    public String getStaticurlroot() {
        return staticurlroot;
    }

    public void setStaticurlroot(String staticurlroot) {
        this.staticurlroot = staticurlroot;
    }

    public void GET(IHttpRequest req, IHttpResponse resp) throws Exception {
        String path = req.getUrl().getPath();
        resp.code(200).write(FileUtil.getContent(new File(localpathroot + path)));
    }

    public void NOTFOUNT(IHttpRequest req, IHttpResponse resp) throws Exception {
        resp.code(404).write("file not found");
    }
}
