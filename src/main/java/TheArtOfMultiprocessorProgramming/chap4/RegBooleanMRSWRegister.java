package TheArtOfMultiprocessorProgramming.chap4;

public class RegBooleanMRSWRegister implements Register<Boolean>{
    private final ThreadLocal<Boolean> last;
    private boolean s_value;

    public RegBooleanMRSWRegister(int capacity) {
        this.last = ThreadLocal.withInitial(() -> false);
    }

    @Override
    public Boolean read(int threadId) {
        return s_value;
    }

    @Override
    public void write(Boolean x) {
        if (x != last.get()){
            last.set(x);
            s_value = x;
        }
    }
}
