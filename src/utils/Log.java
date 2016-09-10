package utils;

/**
 * Created by timeloveboy on 16-9-10.
 */
public final class Log {
    public static void V(Object... objects) {
        System.out.println();
        for (Object o : objects) {
            System.out.print(o);
        }
    }
}
