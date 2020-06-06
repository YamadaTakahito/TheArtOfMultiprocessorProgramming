package TheArtOfMultiprocessorProgramming.chap6;

public class Node {
    public Invocation invoc; // method name and args
    public Consensus<Node> decideNext; // decide next Node in list
    public Node next; // the next node
    public int seq; // sequence number

    public Node(Invocation invoc){
        this.invoc = invoc;
        this.decideNext = new Consensus<Node>();
        this.seq = 0;
    }

    public static Node max(Node[] array){
        Node max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max.seq < array[i].seq){
                max = array[i];
            }
        }
        return max;
    }
}
