import java.io.IOException;

class If {
    boolean elseStatement = false;

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.IF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <if> token must start with 'if' terminal");
            System.exit(1);
        }
        Core nextToken = S.nextToken();
        Cond cond = new Cond();
        cond.parse(nextToken, S);
        nextToken = S.nextToken();
        if (nextToken != Core.THEN) {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 3rd token in <if> token must be 'then' terminal");
            System.exit(1);
        } else {
            nextToken = S.nextToken();
            if (nextToken != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 4th token in <if> token must be the '{' terminal");
                System.exit(1);
            } else {
                nextToken = S.nextToken();
                StatementSeq statementSeq = new StatementSeq();
                statementSeq.parse(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The token after stmt-seq in <if> must be the '}' terminal");
                    System.exit(1);
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
                            System.exit(1);
                        } else {
                            nextToken = S.nextToken();
                            statementSeq.parse(nextToken, S);
                            nextToken = S.nextToken();
                            if (nextToken != Core.RBRACE) {
                                S.t = Core.ERROR;
                                System.out.println(
                                        "ERROR: The token after the stmt-seq in the 'else' case of <if> must be the '}' terminal");
                                System.exit(1);
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
