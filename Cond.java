import java.io.IOException;

class Cond {
    // 1 means <cond>, 2 means !(<cond>), 3 means <expr> or <expr>
    int whichCond;

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.NEGATION) {
            whichCond = 2;
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
                whichCond = 3;
                nextToken = S.nextToken();
                parse(nextToken, S);
            } else {
                // Reset scanner position
                S.in.reset();
                whichCond = 1;
            }
        }
    }

    public void print() {
        switch (whichCond) {
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
