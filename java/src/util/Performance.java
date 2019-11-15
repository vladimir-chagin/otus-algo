package util;

public class Performance {
    public static final void measure(final String message, SimpleFunction fn) {
        final long start = System.currentTimeMillis();

        fn.execute();

        final long duration = System.currentTimeMillis() - start;
        System.out.println((message != null && message.length() > 0 ? message : "" ) + "; time: " + duration + "ms");
    }
}
