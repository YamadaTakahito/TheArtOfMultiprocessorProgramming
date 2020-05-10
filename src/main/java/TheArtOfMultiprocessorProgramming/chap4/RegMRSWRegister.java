package TheArtOfMultiprocessorProgramming.chap4;

import java.awt.image.DataBufferByte;

public class RegMRSWRegister implements Register<Byte>{
    private static int RANGE = Byte.MAX_VALUE - Byte.MIN_VALUE + 1;
    boolean[] r_bit = new boolean[RANGE]; // regular boolean MRSW

    public RegMRSWRegister(int capacity){
        for (int i = 0; i < r_bit.length; i++) {
            r_bit[i] = false;
            r_bit[0] = true;
        }
    }

    @Override
    public void write(Byte x){
        r_bit[x] = true;
        for (int i = x - 1; i >= 0; i--) {
            r_bit[i] = false;
        }
    }

    @Override
    public Byte read(int threadId){
        for (int i = 0; i < RANGE; i++) {
            if (r_bit[i]){
                return i;
            }
        }

        return -1; // impossible
    }
}
