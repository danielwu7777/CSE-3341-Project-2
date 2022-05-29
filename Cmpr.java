import java.io.IOException;

class Cmpr {
    // 1 means '==', 2 means '<', 3 means '<='
    int whichCmpr;

    public void parse(Core currentToken, Scanner S) throws IOException {
        Expr expr = new Expr();
        expr.parse(currentToken, S);
        Core nextToken = S.nextToken();
        switch (nextToken) {
            case EQUAL:
                whichCmpr = 1;
                break;
            case LESS:
                whichCmpr = 2;
                break;
            case LESSEQUAL:
                whichCmpr = 3;
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
        switch (whichCmpr) {
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
