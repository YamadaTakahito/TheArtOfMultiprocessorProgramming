package TheArtOfMultiprocessorProgramming.chap2;

public class Filter implements Lock{
    private int[] level;
    private int[] victim;
    private int n;
    private int minThraedId;
    private int maxThraedId;

    public Filter(int n) {
        this.n = n;
        this.level = new int[n];
        this.victim = new int[n];

        for (int i = 0; i < this.n; i++){
            this.level[i] = 0;
        }

        this.minThraedId = 0;
        this.maxThraedId = n - 1;
    }

    @Override
    public void lock(int threadId) {
        if (threadId < minThraedId || threadId > maxThraedId){
            throw new RuntimeException("thread id is invalid");
        }

        for (int i = 1; i < this.n; i++){
            level[threadId] = i;
            victim[i] = threadId;

            boolean flag = true;
            while(flag){
                for (int k = this.minThraedId; k < this.maxThraedId; k++){
                    if (k == threadId){
                        continue;
                    }

                    flag = level[k] >= i && victim[i] == threadId;
                    if (flag){
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void unlock(int threadId) {
        this.level[threadId] = 0;
    }
}
