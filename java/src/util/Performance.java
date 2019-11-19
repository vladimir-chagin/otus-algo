package util;

public class Performance {
    public static final String measure(String message, final SimpleFunction fn) {
        final long duration = measure(fn);
        return (message != null && message.length() > 0 ? message + " " : "") + duration + "ms";
    }

    public static final long measure(SimpleFunction fn) {
        final long start = System.currentTimeMillis();

        fn.execute();

        final long duration = System.currentTimeMillis() - start;

        return duration;
    }
}
