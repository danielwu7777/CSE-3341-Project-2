import java.io.IOException;

class Term {
    boolean mult = false;

    public void parse(Core currentToken, Scanner S) throws IOException {
        Factor factor = new Factor();
        factor.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.MULT) {
            mult = true;
            nextToken = S.nextToken();
            parse(nextToken, S);
        } else {
            // Reset scanner position
            S.in.reset();
        }
    }
}
