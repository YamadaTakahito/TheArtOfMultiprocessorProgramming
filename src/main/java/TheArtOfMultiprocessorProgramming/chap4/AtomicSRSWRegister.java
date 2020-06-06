package TheArtOfMultiprocessorProgramming.chap4;

public class AtomicSRSWRegister<T> implements Register<T> {
    ThreadLocal<Long> lastStamp;
    ThreadLocal<StampedValue<T>> lastRead;
    StampedValue<T> r_value; // regular SRSW

    public AtomicSRSWRegister(T init){
        this.r_value = new StampedValue<T>(init);
        this.lastStamp = ThreadLocal.withInitial(() -> 0L);
        this.lastRead = ThreadLocal.withInitial(() -> this.r_value);
    }

    @Override
    public T read(int threadId) {
        StampedValue<T> value = this.r_value;
        StampedValue<T> last =  this.lastRead.get();
        StampedValue<T> result = StampedValue.max(value, last);
        this.lastRead.set(result);
        return result.value;
    }

    @Override
    public void write(T v) {
        long stamp = this.lastStamp.get() + 1;
        this.r_value = new StampedValue(stamp, v);
        this.lastStamp.set(stamp);
    }
}
