import java.io.IOException;

class Loop {

    public void parseLoop(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.WHILE) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <loop> must start with 'while' terminal");
        } else {
            Core nextToken = S.nextToken();
            Cond cond = new Cond();
            cond.parseCond(nextToken, S);
            nextToken = S.nextToken();
            if (nextToken != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 3rd token in <loop> must be the '{'terminal");
            } else {
                nextToken = S.nextToken();
                StatementSeq statementSeq = new StatementSeq();
                statementSeq.parseStatementSeq(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The last in <loop> must be the '}'terminal");
                }
            }
        }
    }
}
