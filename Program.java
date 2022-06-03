import java.io.IOException;

class Program {
    // Boolean to store if a <decl-seq> exists where
    // true means it exists and false means it does not
    boolean hasDeclSeq;
    DeclSeq ds;
    StatementSeq ss;

    public void parse(Scanner S) throws IOException {
        // Program must start with program token
        if (S.currentToken() != Core.PROGRAM) {
            S.t = Core.ERROR;
            System.out.println("ERROR: Program must start with program token");
            System.exit(1);
        } else {
            if (S.nextToken() != Core.BEGIN) {
                hasDeclSeq = true;
                // Parse <decl-seq>
                ds = new DeclSeq();
                ds.parse(S);
            } else {
                hasDeclSeq = false;
            }
            if (S.currentToken() != Core.BEGIN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: Program expected 'begin' before <stmt-seq>");
                System.exit(1);
            } else {
                S.nextToken();
                ss = new StatementSeq();
                ss.parse(S);
                if (S.currentToken() != Core.END) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Program must end with 'end' token");
                    System.exit(1);
                } else {
                    if (S.nextToken() != Core.EOS) {
                        S.t = Core.ERROR;
                        System.out.println("ERROR: Program expected EOS token after 'end'");
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
            ds.print();
        }
        System.out.println("begin");
        System.out.print("    ");
        ss.print();
        System.out.print("end");
    }
}
