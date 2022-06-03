import java.io.IOException;

class Factor {
    // Integer to store which factor where
    // 1 means 'id', 2 means 'const', and 3 means (<expr>)
    int whichFactor;
    String id;
    int constant;
    Expr expr;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.ID) {
            id = S.getID();
            whichFactor = 1;
        } else if (S.currentToken() == Core.CONST) {
            constant = S.getCONST();
            whichFactor = 2;
        } else if (S.currentToken() == Core.LPAREN) {
            whichFactor = 3;
            S.nextToken();
            expr = new Expr();
            expr.parse(S);
            // Token after <expr> must be ')' terminal
            if (S.currentToken() != Core.RPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: Token after expression in <factor> must be ')' terminal");
                System.exit(1);
            }
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 1st token in <factor> must be 'id', 'const', or '(' terminal");
            System.exit(1);
        }
        S.nextToken();
    }

    public void print() {
        switch (whichFactor) {
            case 1:
                System.out.print(id);
                break;
            case 2:
                System.out.print(Integer.toString(constant));
                break;
            case 3:
                System.out.print("(");
                expr.print();
                System.out.print(")");
                break;
        }
    }
}
