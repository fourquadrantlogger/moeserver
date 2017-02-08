package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by paidian on 17-2-8.
 */
public class StreamUtil {
    public static String getBody(InputStream data) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(data));
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return sb.toString();
    }
}
