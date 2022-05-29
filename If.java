import java.io.IOException;

class If {
    boolean elseStatement = false;

    public void parseIf(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.IF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <if> token must start with 'if' terminal");
        }
        Core nextToken = S.nextToken();
        Cond cond = new Cond();
        cond.parseCond(nextToken, S);
        nextToken = S.nextToken();
        if (nextToken != Core.THEN) {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 3rd token in <if> token must be 'then' terminal");
        } else {
            nextToken = S.nextToken();
            if (nextToken != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 4th token in <if> token must be the '{' terminal");
            } else {
                nextToken = S.nextToken();
                StatementSeq statementSeq = new StatementSeq();
                statementSeq.parseStatementSeq(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The token after stmt-seq in <if> must be the '}' terminal");
                } else {
                    // Check for 'else'
                    // Save scanner position
                    S.in.mark(1);
                    nextToken = S.nextToken();
                    if (nextToken == Core.ELSE) {
                        nextToken = S.nextToken();
                        if (nextToken != Core.LBRACE) {
                            S.t = Core.ERROR;
                            System.out.println("ERROR: The token after 'else' in <if> must be the '{' terminal");
                        } else {
                            nextToken = S.nextToken();
                            statementSeq.parseStatementSeq(nextToken, S);
                            nextToken = S.nextToken();
                            if (nextToken != Core.RBRACE) {
                                S.t = Core.ERROR;
                                System.out.println(
                                        "ERROR: The token after the stmt-seq in the 'else' case of <if> must be the '}' terminal");
                            }
                        }
                    } else {
                        // Reset scanner position
                        S.in.reset();
                        elseStatement = false;
                    }
                }
            }
        }
    }

}
