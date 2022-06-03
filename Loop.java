import java.io.IOException;

class Loop {
    Cond cond;
    StatementSeq stmtSeq;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() != Core.WHILE) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <loop> must start with 'while' terminal");
            System.exit(1);
        } else {
            S.nextToken();
            cond = new Cond();
            cond.parse(S);
            if (S.currentToken() != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 3rd token in <loop> must be the '{'terminal");
                System.exit(1);
            } else {
                S.nextToken();
                stmtSeq = new StatementSeq();
                stmtSeq.parse(S);
                if (S.currentToken() != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The last in <loop> must be the '}'terminal");
                    System.exit(1);
                }
                S.nextToken();
            }
        }
    }

    public void print() {
        System.out.print("while ");
        cond.print();
        System.out.println(" {");
        System.out.print("       ");
        stmtSeq.print();
        System.out.println("   }");
    }
}
