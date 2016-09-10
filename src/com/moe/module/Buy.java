package com.moe.module;

import utils.Log;
import xyz.moeserver.DefaultHandle;
import xyz.moeserver.HttpRequest;
import xyz.moeserver.HttpResponse;

import java.io.IOException;

/**
 * Created by timeloveboy on 16-9-10.
 */
public class Buy extends DefaultHandle {
    @Override
    public void GET(HttpRequest req, HttpResponse resp) throws IOException {
        Log.V(req.cookies.get("JSESSIONID"));

        resp.setcookie("JSESSIONID", "3212412235235");
        resp.code(200).write("get");
    }
}
