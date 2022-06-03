import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Cond {
    // Queue to store which condition where
    // 1 means just <cmpr>, 2 means negation, 3 means 'or''
    Queue<Integer> queueWhichCond = new LinkedList<Integer>();
    Cmpr cmpr;
    Cond cond;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.NEGATION) {
            queueWhichCond.add(2);
            if (S.nextToken() != Core.LPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The token after '!' in <cond> must be '(' terminal");
                System.exit(1);
            } else {
                S.nextToken();
                cond = new Cond();
                cond.parse(S);
                if (S.currentToken() != Core.RPAREN) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Missing the last ')' termimal in !(<cond>)");
                    System.exit(1);
                }
                S.nextToken();
            }
        } else {
            cmpr = new Cmpr();
            cmpr.parse(S);
            if (S.currentToken() != Core.OR) {
                queueWhichCond.add(1);
            } else {
                // Current token is 'or'
                queueWhichCond.add(3);
                S.nextToken();
                cond = new Cond();
                cond.parse(S);
                S.nextToken();
            }
        }
    }

    public void print() {
        switch (queueWhichCond.remove()) {
            case 1:
                cmpr.print();
                break;
            case 2:
                System.out.print("!(");
                cond.print();
                System.out.print(")");
                break;
            case 3:
                cmpr.print();
                System.out.print("or");
                cond.print();
                break;
        }
    }
}
