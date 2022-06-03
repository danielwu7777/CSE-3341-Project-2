import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Program {
    // Boolean to store if a <decl-seq> exists where
    // true means it exists and false means it does not
    boolean hasDeclSeq;
    DeclSeq ds;
    StatementSeq ss;
    // Stack of lists of strings for semantic checks
    static Stack<List<String>> stack = new Stack<>();
    // List of variables for <decl-seq> which have global scope (semantic checking)
    static List<String> declSeqList = new ArrayList<String>();
    // Note: Variables stored in these lists will have "i" as the first character
    // for integers and "r" as the first character for references with the variable
    // name appended after

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
                stack.add(declSeqList);
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

    public void semantic() {
        if (hasDeclSeq) {
            ds.sematic();
        }
        ss.sematic();
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
