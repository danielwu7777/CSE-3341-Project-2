import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Expr {
    // List to store type of expression where
    // 0 means just term, 1 means '+' between terms, and 2 means '-' between terms
    static List<Integer> list = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        Term term = new Term();
        term.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.ADD) {
            list.add(1);
            nextToken = S.nextToken();
            parse(nextToken, S);
        } else if (nextToken == Core.SUB) {
            list.add(2);
            nextToken = S.nextToken();
            parse(nextToken, S);
        } else {
            list.add(0);
            // Reset scanner position
            S.in.reset();
        }
    }

    public void print() {
        Term term = new Term();
        term.print();
        Expr expr = new Expr();
        switch (list.remove(0)) {
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
