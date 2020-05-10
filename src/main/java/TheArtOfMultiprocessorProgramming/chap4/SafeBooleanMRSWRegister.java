package TheArtOfMultiprocessorProgramming.chap4;

public class SafeBooleanMRSWRegister implements Register<Boolean>{
    boolean[] s_table;

    public SafeBooleanMRSWRegister(int capacity) {
        this.s_table = new boolean[capacity];
    }

    @Override
    public Boolean read(int threadId) {
        return s_table[threadId];
    }

    @Override
    public void write(Boolean v) {
        for (int i = 0; i < s_table.length; i++) {
            s_table[i] = v;
        }
    }
}
