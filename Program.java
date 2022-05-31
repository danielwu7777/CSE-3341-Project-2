import java.io.IOException;

class Program {
    static boolean hasDeclSeq = false;

    public void parse(Core currentToken, Scanner S) throws IOException {
        // Program must start with program token
        if (currentToken != Core.PROGRAM) {
            S.t = Core.ERROR;
            System.out.println("ERROR: Program must start with program token");
            System.exit(1);
        } else {
            StatementSeq statementSeq = new StatementSeq();
            Core nextToken = S.nextToken();
            if (nextToken == Core.BEGIN) {
                nextToken = S.nextToken();
                statementSeq.parse(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.END) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Program must end with 'end' token");
                    System.exit(1);
                }
            }
            // Parse DeclarationSeq
            else {
                hasDeclSeq = true;
                DeclSeq declSeq = new DeclSeq();
                declSeq.parse(nextToken, S);
                nextToken = S.currentToken();
                if (nextToken == Core.BEGIN) {
                    nextToken = S.nextToken();
                    statementSeq.parse(nextToken, S);
                    nextToken = S.currentToken(); 
                    if (nextToken != Core.END) {
                        S.t = Core.ERROR;
                        System.out.println("ERROR: Program must end with end token");
                        System.exit(1);
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println("program");
        System.out.print("    ");
        if (hasDeclSeq) {
            DeclSeq declSeq = new DeclSeq();
            declSeq.print();
        }
        System.out.println("begin");
        System.out.print("    ");
        StatementSeq statementSeq = new StatementSeq();
        statementSeq.print();
        System.out.print("end");
    }
}
