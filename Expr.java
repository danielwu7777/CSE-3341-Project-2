import java.io.IOException;

class Expr {
    // 0 means no tokens after 1st term, 1 means '+', 2 means '-'
    int tokenAfterTerm;

    public void parseExpr(Core currentToken, Scanner S) throws IOException {
        Term term = new Term();
        term.parseTerm(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.ADD) {
            tokenAfterTerm = 1;
            nextToken = S.nextToken();
            parseExpr(nextToken, S);
        } else if (nextToken == Core.SUB) {
            tokenAfterTerm = 2;
            nextToken = S.nextToken();
            parseExpr(nextToken, S);
        } else {
            // Reset scanner position
            S.in.reset();
        }
    }
}