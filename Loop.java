import java.io.IOException;

class Loop {

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.WHILE) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <loop> must start with 'while' terminal");
            System.exit(1);
        } else {
            Core nextToken = S.nextToken();
            Cond cond = new Cond();
            cond.parse(nextToken, S);
            nextToken = S.nextToken();
            if (nextToken != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 3rd token in <loop> must be the '{'terminal");
                System.exit(1);
            } else {
                nextToken = S.nextToken();
                StatementSeq statementSeq = new StatementSeq();
                statementSeq.parse(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The last in <loop> must be the '}'terminal");
                    System.exit(1);
                }
            }
        }
    }

    public void print() {
        System.out.print("while ");
        Cond cond = new Cond();
        cond.print();
        System.out.println(" {");
        System.out.print("       ");
        StatementSeq statementSeq = new StatementSeq();
        statementSeq.print();
        System.out.println("   }");

    }
}
