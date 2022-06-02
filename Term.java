import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Term {
    // Queue to keep track of if there's a multiplication symbol where
    // 1 means there is '*' and 0 means there isn't 
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        Factor factor = new Factor();
        factor.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        System.out.println(S.currentToken() + "this should be =="); // something wrong here
        if (nextToken == Core.MULT) {
            queue.add(1);
            nextToken = S.nextToken();
            parse(nextToken, S);
        } else {
            queue.add(0);
            // Reset scanner position
            S.in.reset(); 
            System.out.println(S.currentToken() + " this should be 1");
            System.out.println(S.nextToken());
        }
    }

    public void print() { 
        Factor factor = new Factor();
        factor.print();
        int removed = queue.remove();
        if (removed == 1) {
            System.out.print("*");
            print();
        }
    }
}
