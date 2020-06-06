package TheArtOfMultiprocessorProgramming.chap4;

public class StampedValue<T>{
    public long stamp;
    public T value;

    public StampedValue(T init) {
        this.stamp = 0;
        this.value = init;
    }

    public StampedValue(long stamp, T value) {
        this.stamp = stamp;
        this.value = value;
    }

    public static StampedValue max(StampedValue x, StampedValue y){
        if (x.stamp > y.stamp){
            return x;
        }else {
            return y;
        }
    }

    public static StampedValue MIN_VALUE = new StampedValue(null);
}
