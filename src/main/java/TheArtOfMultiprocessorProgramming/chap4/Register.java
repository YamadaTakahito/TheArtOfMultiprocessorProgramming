package TheArtOfMultiprocessorProgramming.chap4;

public interface Register<T> {
    T read(int threadId);
    void write(T v);
}
