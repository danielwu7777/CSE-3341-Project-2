import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Term {
    // Queue to keep track of if there's a multiplication symbol where
    // true means there is '*' and false means there isn't
    Queue<Boolean> queueIsMult = new LinkedList<Boolean>();
    Factor factor;
    Term term;

    public void parse(Scanner S) throws IOException {
        factor = new Factor();
        factor.parse(S);
        if (S.currentToken() == Core.MULT) {
            queueIsMult.add(true);
            S.nextToken();
            term = new Term();
            term.parse(S);
        } else {
            queueIsMult.add(false);
        }
    }

    public void print() {
        factor.print();
        if (queueIsMult.remove()) {
            System.out.print("*");
            term.print();
        }
    }
}
