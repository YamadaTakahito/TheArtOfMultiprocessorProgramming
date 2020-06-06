package TheArtOfMultiprocessorProgramming.chap4;

public class AtomicMRMWRegister<T> implements Register<T> {
    private StampedValue<T>[] a_table; // array of atomic MRSW registers

    public AtomicMRMWRegister(int capacity, T init) {
        this.a_table =  new StampedValue[capacity];
        StampedValue<T> value = new StampedValue(init);
        for (int i = 0; i < this.a_table.length; i++) {
            a_table[i] = value;
        }
    }

    @Override
    public T read(int threadId) {
        StampedValue<T> max = StampedValue.MIN_VALUE;
        for(StampedValue<T> val: this.a_table){
            max = StampedValue.max(max, val);
        }
        return max.value;
    }

    @Override
    public void write(T value) {
        int me = (int) Thread.currentThread().getId();
        StampedValue<T> max = StampedValue.MIN_VALUE;
        for (StampedValue<T> val: this.a_table){
            max = StampedValue.max(max, val);
        }
        this.a_table[me] = new StampedValue(max.stamp + 1, value);
    }
}
