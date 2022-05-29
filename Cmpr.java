import java.io.IOException;

class Cmpr {
    // 1 means '==', 2 means '<', 3 means '<='
    int whichCmpr;

    public void parseCmpr(Core currentToken, Scanner S) throws IOException {
        Expr expr = new Expr();
        expr.parseExpr(currentToken, S);
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
        }
        nextToken = S.nextToken();
        expr.parseExpr(nextToken, S);
    }
}
