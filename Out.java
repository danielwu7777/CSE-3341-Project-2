import java.io.IOException;

class Out {

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.OUTPUT) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <out> token must start with 'output' terminal");
            System.exit(1);
        } else {
            Core nextToken = S.nextToken();
            if (nextToken != Core.LPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 2nd token in <out> must be '(' terminal");
                System.exit(1);
            } else {
                nextToken = S.nextToken();
                Expr expr = new Expr();
                expr.parse(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RPAREN) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The token after expr in <out> must be ')' terminal");
                    System.exit(1);
                } else {
                    nextToken = S.nextToken();
                    if (nextToken != Core.SEMICOLON) {
                        S.t = Core.ERROR;
                        System.out.println("ERROR: The last token in <out> must be ';' terminal");
                        System.exit(1);
                    }
                }
            }
        }
    }
}
