package xyz.moexiang;
//import com.google.common.io.CharStreams;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by timeloveboy on 16/5/30.
 */
public class MainHandler implements HttpHandler {

    /**
     * /api?level=10&location=13.332
     * ====handle hederinfo
     */
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        URI url=exchange.getRequestURI();//获取url链接饿信息

        InputStream inputStream=exchange.getRequestBody();//除了get以外的方法时候,将信息放入body中
        //使用get方法时候将信息放入url中
        //String text=CharStreams.toString(new InputStreamReader(inputStream,"utf-8"));
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb=new StringBuilder();
        String line="";
        String text="";
        char[] cs;
        try{
            while((line=reader.readLine())!=null){

                System.out.println(line);
            }

        }catch(IOException e){
            e.getMessage();
        }
        //String text=reader.readLine();

        //用inputstream来读取body里面的内容

            Headers responseHeaders = exchange.getResponseHeaders();

            responseHeaders.set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, 0);

            OutputStream responseBody = exchange.getResponseBody();
            Headers requestHeaders = exchange.getRequestHeaders();
            Set<String> keySet = requestHeaders.keySet();
            Iterator<String> iter;
            iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List<String> values = requestHeaders.get(key);
                String s = key + " = " + values.toString() + "\n";
                responseBody.write(s.getBytes());
            }
            responseBody.close();
        }

}