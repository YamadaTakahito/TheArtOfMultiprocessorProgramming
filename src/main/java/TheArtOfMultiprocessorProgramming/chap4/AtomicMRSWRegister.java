package TheArtOfMultiprocessorProgramming.chap4;

public class AtomicMRSWRegister<T> implements Register<T> {
    private ThreadLocal<Long> lastStamp;
    private StampedValue<T>[][] a_table; // each entry is SRSW atomic

    public AtomicMRSWRegister(T init, int readers){
        lastStamp = ThreadLocal.withInitial(() -> 0L);
        a_table = (StampedValue<T>[][]) new StampedValue[readers][readers];
        StampedValue<T> value = new StampedValue(init);
        for (int i = 0; i < readers; i++) {
            for (int j = 0; j < readers; j++) {
                a_table[i][j] = value;
            }
        }
    }

    @Override
    public T read(int threadId) {
        StampedValue<T> value = this.a_table[threadId][threadId];
        for (int i = 0; i < this.a_table.length; i++) {
            value = StampedValue.max(value, this.a_table[i][threadId]);
        }
        for (int i = 0; i < this.a_table.length; i++) {
            this.a_table[threadId][i] = value;
        }
        return value.value;
    }

    @Override
    public void write(T v) {
        long stamp = this.lastStamp.get() + 1;
        this.lastStamp.set(stamp);
        StampedValue<T> value = new StampedValue(stamp, v);
        for (int i = 0; i < this.a_table.length; i++) {
            this.a_table[i][i] = value;
        }
    }
}
