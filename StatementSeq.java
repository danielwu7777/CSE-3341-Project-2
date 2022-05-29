import java.io.IOException;

class StatementSeq {
    boolean hasStatementSeq = false;

    public void parse(Core currentToken, Scanner S) throws IOException {
        Statement statement = new Statement();
        statement.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.ID || nextToken == Core.IF || nextToken == Core.WHILE || nextToken == Core.OUTPUT
                || nextToken == Core.INT || nextToken == Core.REF) {
            hasStatementSeq = true;
            parse(nextToken, S);
        } else {
            // Reset scanner position
            S.in.reset();
        }
    }

    public void print() {
        Statement statement = new Statement();
        statement.print();
        if (hasStatementSeq) {
            print();
        }
    }
}
