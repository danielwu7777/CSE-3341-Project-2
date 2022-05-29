import java.io.IOException;

class Factor {
    // 1 means 'id', 2 means 'const', 3 means '('
    int whichFactor;

    public void parseFactor(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.ID) {
            whichFactor = 1;
        } else if (currentToken == Core.CONST) {
            whichFactor = 2;
        } else if (currentToken == Core.LPAREN) {
            whichFactor = 3;
            Core nextToken = S.nextToken();
            Expr expr = new Expr();
            expr.parseExpr(nextToken, S);
            nextToken = S.nextToken();
            // Token after <expr> must be ')' terminal
            if (nextToken != Core.RPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: Token after expression in <factor> must be ')' terminal");
            }
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 1st token in <factor> must be 'id', 'const', or '(' terminal");
        }
    }

}
