import java.io.IOException;

class StatementSeq {
    boolean hasStatementSeq = false;

    public void parseStatementSeq(Core currentToken, Scanner S) throws IOException {
        Statement statement = new Statement();
        statement.parseStatement(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.ID || nextToken == Core.IF || nextToken == Core.WHILE || nextToken == Core.OUTPUT
                || nextToken == Core.INT || nextToken == Core.REF) {
            hasStatementSeq = true;
            parseStatementSeq(nextToken, S);
        } else {
            // Reset scanner position
            S.in.reset();
        }
    }

}
