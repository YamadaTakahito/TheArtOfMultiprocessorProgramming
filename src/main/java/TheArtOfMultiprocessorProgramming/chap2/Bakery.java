package TheArtOfMultiprocessorProgramming.chap2;

import java.util.stream.IntStream;

public class Bakery implements Lock {
    private boolean[] flag;
    private int[] label;
    private int minThraedId;
    private int maxThraedId;

    public Bakery(int n) {
        this.flag = new boolean[n];
        this.label = new int[n];

        for (int i = 0; i < n; i++){
            flag[i] = false;
            label[i] = 0;
        }
        this.minThraedId = 0;
        this.maxThraedId = n - 1;
    }

    @Override
    public void lock(int threadId) {
        flag[threadId] = true;
        label[threadId] = IntStream.of(label).max().getAsInt() + 1;

        boolean f = true;
        while(f){
            for (int k = this.minThraedId; k < this.maxThraedId; k++){
                if (k == threadId){
                    continue;
                }

                f = flag[k];

                if (label[k] != label[threadId]){
                    f = f && (label[k] < label[threadId]);
                } else {
                    // lexicographical ordering(same label)
                    f = f && (k < threadId);
                }

//                existential quantifier
                if (f){
                    break;
                }
            }
        }
    }

    @Override
    public void unlock(int threadId) {
        flag[threadId] = false;
    }
}

