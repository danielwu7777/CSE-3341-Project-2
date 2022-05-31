import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class StatementSeq {
    // Queue to keep track of if there's a statement sequence where
    // 1 means it exists and 0 means it doesn't
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        Statement statement = new Statement();
        statement.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.ID || nextToken == Core.IF || nextToken == Core.WHILE || nextToken == Core.OUTPUT
                || nextToken == Core.INT || nextToken == Core.REF) {
            queue.add(1);
            parse(nextToken, S);
        } else {
            queue.add(0);
            // Reset scanner position
            S.in.reset();
        }
    }

    public void print() {
        Statement statement = new Statement();
        statement.print();
        if (queue.remove() == 1) {
            print();
        }
    }
}
