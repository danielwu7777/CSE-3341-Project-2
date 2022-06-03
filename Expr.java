import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Expr {
    // Queue to store type of expression where
    // 0 means just term, 1 means '+' between terms, and 2 means '-' between terms
    Queue<Integer> queueWhichExpr = new LinkedList<Integer>();
    Term term;
    Expr expr;
    public void parse(Scanner S) throws IOException {
        term = new Term();
        term.parse(S);
        if (S.currentToken() == Core.ADD) {
            queueWhichExpr.add(1);
            S.nextToken();
            expr = new Expr();
            expr.parse(S);
        } else if (S.currentToken() == Core.SUB) {
            queueWhichExpr.add(2);
            S.nextToken();
            expr = new Expr();
            expr.parse(S);
        } else {
            queueWhichExpr.add(0);
        }
    }

    public void print() {
        term.print();
        switch (queueWhichExpr.remove()) {
            case 0:
                // Just a single term
                break;
            case 1:
                System.out.print("+");
                expr.print();
                break;
            case 2:
                System.out.print("-");
                expr.print();
                break;
        }
    }
}
