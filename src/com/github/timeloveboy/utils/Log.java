package com.github.timeloveboy.utils;

/**
 * Created by timeloveboy on 16-9-10.
 */
public final class Log {
    public static void v(Object... objects) {
        System.out.println();
        for (Object o : objects) {
            System.out.print(o);
        }
    }
}
