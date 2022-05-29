import java.io.IOException;

class Cond {
    // 1 means <cond>, 2 means !(<cond>), 3 means <expr> or <expr>
    int whichCond;

    public void parseCond(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.NEGATION) {
            whichCond = 2;
            Core nextToken = S.nextToken();
            if (nextToken != Core.LPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The token after '!' in <cond> must be '(' terminal");
            } else {
                nextToken = S.nextToken();
                parseCond(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RPAREN) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Missing the last ')' termimal in !(<cond>)");
                }
            }
        } else {
            Cmpr cmpr = new Cmpr();
            cmpr.parseCmpr(currentToken, S);
            // Save scanner position
            S.in.mark(1);
            Core nextToken = S.nextToken();
            if (nextToken == Core.OR) {
                whichCond = 3;
                nextToken = S.nextToken();
                parseCond(nextToken, S);
            } else {
                // Reset scanner position
                S.in.reset();
                whichCond = 1;
            }
        }
    }
}
