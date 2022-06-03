import java.io.IOException;

class Cmpr {
    // Integer to store which comparator where
    // 1 means means '==', 2 means '<', 3 means'<=''
    int i;
    Expr firstExpr;
    Expr secondExpr;

    public void parse(Scanner S) throws IOException {
        firstExpr = new Expr();
        firstExpr.parse(S);
        switch (S.currentToken()) {
            case EQUAL:
                i = 1;
                break;
            case LESS:
                i = 2;
                break;
            case LESSEQUAL:
                i = 3;
                break;
            default:
                S.t = Core.ERROR;
                System.out.println("ERROR: <cmpr> must have '==', '<', or '<=' between the expressions");
                System.exit(1);
        }
        S.nextToken();
        secondExpr = new Expr();
        secondExpr.parse(S);
    }

    public void print() {
        firstExpr.print();
        switch (i) {
            case 1:
                System.out.print("==");
                break;
            case 2:
                System.out.print("<");
                break;
            case 3:
                System.out.print("<=");
                break;
        }
        secondExpr.print();
    }
}
