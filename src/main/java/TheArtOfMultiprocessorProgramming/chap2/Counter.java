package TheArtOfMultiprocessorProgramming.chap2;

public class Counter {
    private int value;
    private Lock lock;

    public Counter(int value) {
        this.value = value;
    }

    public long getAndIncrement() {
        lock.lock();
        try {
            int temp = value;
            value = temp + 1;
        } finally {
            lock.unlock();
        }
        return temp;
    }
}
