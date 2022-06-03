import java.io.IOException;

class If {
    // Boolean to store if an 'else' statement exists where
    // true means means it exists and false means it does not
    boolean b;
    Cond cond;
    StatementSeq firstStmtSeq;
    StatementSeq secondStatementSeq;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() != Core.IF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <if> token must start with 'if' terminal");
            System.exit(1);
        }
        S.nextToken();
        cond = new Cond();
        cond.parse(S);
        if (S.currentToken() != Core.THEN) {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 3rd token in <if> token must be 'then' terminal");
            System.exit(1);
        } else {
            S.nextToken();
            if (S.currentToken() != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 4th token in <if> token must be the '{' terminal");
                System.exit(1);
            } else {
                S.nextToken();
                firstStmtSeq = new StatementSeq();
                firstStmtSeq.parse(S);
                if (S.currentToken() != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The token after stmt-seq in <if> must be the '}' terminal");
                    System.exit(1);
                } else {
                    // Check for 'else'
                    S.nextToken();
                    if (S.currentToken() == Core.ELSE) {
                        b = true;
                        if (S.nextToken() != Core.LBRACE) {
                            S.t = Core.ERROR;
                            System.out.println("ERROR: The token after 'else' in <if> must be the '{' terminal");
                            System.exit(1);
                        } else {
                            S.nextToken();
                            secondStatementSeq = new StatementSeq();
                            secondStatementSeq.parse(S);
                            if (S.currentToken() != Core.RBRACE) {
                                S.t = Core.ERROR;
                                System.out.println(
                                        "ERROR: The token after the stmt-seq in the 'else' case of <if> must be the '}' terminal");
                                System.exit(1);
                            }
                            S.nextToken();
                        }
                    } else {
                        b = false;
                    }
                }
            }
        }
    }

    public void print() {
        System.out.print("if ");
        cond.print();
        System.out.println(" then {");
        System.out.print("        ");
        firstStmtSeq.print();
        System.out.print("   }");
        if (b) {
            System.out.println(" else {");
            System.out.print("        ");
            secondStatementSeq.print();
            System.out.println("   }");
        }
    }
}
