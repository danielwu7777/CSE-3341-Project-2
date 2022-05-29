import java.io.IOException;

class Program {
    boolean hasDeclSeq = false;

    public void parseProgram(Core currentToken, Scanner S) throws IOException {
        // Program must start with program token
        if (currentToken != Core.PROGRAM) {
            S.t = Core.ERROR;
            System.out.println("ERROR: Program must start with program token");
        } else {
            StatementSeq statementSeq = new StatementSeq();
            // Get the next token and parse accordingly
            Core nextToken = S.nextToken();
            if (nextToken == Core.BEGIN) {
                // Parse statement sequence
                nextToken = S.nextToken();
                statementSeq.parseStatementSeq(nextToken, S);
                // Program must finish with End token
                nextToken = S.nextToken();
                if (nextToken != Core.END) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Program must end with 'end' token");
                }
            }
            // Parse DeclarationSeq
            else {
                hasDeclSeq = true;
                DeclSeq declSeq = new DeclSeq();
                declSeq.parseDeclSeq(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken == Core.BEGIN) {
                    // Parse statement sequence
                    nextToken = S.nextToken();
                    statementSeq.parseStatementSeq(nextToken, S);
                    // Program must finish with End token
                    nextToken = S.nextToken();
                    if (nextToken != Core.END) {
                        S.t = Core.ERROR;
                        System.out.println("ERROR: Program must end with end token");
                    } else {
                        // process end token
                    }
                }
            }
        }
    }
}
