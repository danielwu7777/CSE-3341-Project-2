import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Cond {
    // Queue to store which condition where 
    // 1 means just <cmpr>, 2 means negation, 3 means 'or''
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.NEGATION) {
            queue.add(2);
            Core nextToken = S.nextToken();
            if (nextToken != Core.LPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The token after '!' in <cond> must be '(' terminal");
                System.exit(1);
            } else {
                nextToken = S.nextToken();
                parse(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RPAREN) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Missing the last ')' termimal in !(<cond>)");
                    System.exit(1);
                }
            }
        } else {
            Cmpr cmpr = new Cmpr();
            cmpr.parse(currentToken, S);
            // Save scanner position
            S.in.mark(1);
            Core nextToken = S.nextToken();
            if (nextToken == Core.OR) {
                queue.add(3);
                nextToken = S.nextToken();
                parse(nextToken, S);
            } else {
                queue.add(1);
                // Reset scanner position
                S.in.reset();
            }
        }
    }

    public void print() {
        switch (queue.remove()) {
            case 1:
                Cmpr cmpr = new Cmpr();
                cmpr.print();
                break;
            case 2:
                System.out.print("!(");
                print();
                System.out.print(")");
                break;
            case 3:
                Cmpr cmpr2 = new Cmpr();
                cmpr2.print();
                System.out.print("or");
                print();
                break;
        }
    }
}
