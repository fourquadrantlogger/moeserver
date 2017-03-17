package com.github.timeloveboy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUtil {
    public static String getContent(File file) {
        String content = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                content += line + "\r\n";
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}