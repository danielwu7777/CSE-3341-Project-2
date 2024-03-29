import java.io.IOException;

class Out {
    Expr expr;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() != Core.OUTPUT) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <out> token must start with 'output' terminal");
            System.exit(1);
        } else {
            if (S.nextToken() != Core.LPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 2nd token in <out> must be '(' terminal");
                System.exit(1);
            } else {
                S.nextToken();
                expr = new Expr();
                expr.parse(S);
                if (S.currentToken() != Core.RPAREN) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The token after expr in <out> must be ')' terminal");
                    System.exit(1);
                } else {
                    if (S.nextToken() != Core.SEMICOLON) {
                        S.t = Core.ERROR;
                        System.out.println("ERROR: The last token in <out> must be ';' terminal");
                        System.exit(1);
                    }
                    S.nextToken();
                }
            }
        }
    }

    public void print() {
        System.out.print("output(");
        expr.print();
        System.out.println(");");
    }

    public void semantic() {
    }
}
