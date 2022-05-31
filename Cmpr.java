import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Cmpr {
    // Queue to store which comparator where
    // 1 means means '==', 2 means '<', 3 means'<=''
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        Expr expr = new Expr();
        expr.parse(currentToken, S);
        Core nextToken = S.nextToken();
        switch (nextToken) {
            case EQUAL:
                queue.add(1);
                break;
            case LESS:
                queue.add(2);
                break;
            case LESSEQUAL:
                queue.add(3);
                break;
            default:
                S.t = Core.ERROR;
                System.out.println("ERROR: <cmpr> must have '==', '<', or '<=' between the expressions");
                System.exit(1);
        }
        nextToken = S.nextToken();
        expr.parse(nextToken, S);
    }

    public void print() {
        Expr expr = new Expr();
        expr.print();
        switch (queue.remove()) {
            case 1:
                System.out.print("==");
                expr.print();
                break;
            case 2:
                System.out.print(" < ");
                expr.print();
                break;
            case 3:
                System.out.print(" <= ");
                expr.print();
                break;
        }
    }
}
