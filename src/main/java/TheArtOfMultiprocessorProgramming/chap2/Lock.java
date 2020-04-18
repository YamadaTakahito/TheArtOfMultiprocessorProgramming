package TheArtOfMultiprocessorProgramming.chap2;

public interface Lock {
    public void lock(int threadId);
    public void unlock(int threadId);
}

