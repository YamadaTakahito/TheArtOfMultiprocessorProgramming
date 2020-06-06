package TheArtOfMultiprocessorProgramming.chap6;

public class LFUniversal implements SeqObject{
    private Node[] head;
    private Node tail;

    public LFUniversal(int n){
        this.tail = new Node();
        this.tail.seq = 1;
        for (int i = 0; i < n; i++) {
            this.head[i] = tail;
        }
    }

    public Response apply(Invocation invoc){
        int i = (int) Thread.currentThread().getId();
        Node prefer = new Node(invoc);

        while (prefer.seq == 0){
            Node before = Node.max(this.head);
            Node after = before.decideNext.decide(prefer);
            before.next = after;
            after.seq = before.seq + 1;
            this.head[i] = after;
        }

        SeqObject myObject = new SeqObject();
        Node current = tail.next;
        while (current != prefer){
            myObject.apply(current.invoc);
            current = current.next;
        }
        return myObject.apply(current.invoc);
    }
}
