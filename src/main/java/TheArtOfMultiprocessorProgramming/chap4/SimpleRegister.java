package TheArtOfMultiprocessorProgramming.chap4;

public class SimpleRegister<T> implements Register<T> {
    private T value;

    public SimpleRegister(T init) {
        this.value = init;
    }

    @Override
    public T read(int threadId) {
        return this.value;
    }

    @Override
    public void write(T v) {
        this.value = v;
    }
}
