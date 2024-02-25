package utils;

public class Timer {

    private long startTime;
    private long targetDelayNanos; // Delay in nanoseconds

    public Timer(long delayMillis) {
        this.targetDelayNanos = delayMillis * 1000000; // Convert milliseconds to nanoseconds
        startTime = System.nanoTime();
    }

    public boolean isReady() {
        long currentTime = System.nanoTime();
        return currentTime - startTime >= targetDelayNanos;
    }

    public void reset() {
        startTime = System.nanoTime();
    }
}