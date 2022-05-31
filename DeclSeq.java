import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class DeclSeq {
    // Queue to store if <decl-seq> exists where 
    // 1 means it exists and 0 means it does not
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        Decl decl = new Decl();
        decl.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.INT || nextToken == Core.REF) {
            queue.add(1);
            parse(nextToken, S);
        } else {
            queue.add(0);
            S.in.reset();
        }
    }

    public void print() {
        Decl decl = new Decl();
        decl.print();
        if (queue.remove() == 1) {
            print();
        }
    }
}
