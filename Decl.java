import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Decl {

    // Queue to store which type of declaration where 
    // 1 means just <decl-int>, and 2 means <decl-ref>
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.INT) {
            queue.add(1);
            DeclInt declInt = new DeclInt();
            declInt.parse(currentToken, S);
        } else if (currentToken == Core.REF) {
            queue.add(2);
            DeclRef declRef = new DeclRef();
            declRef.parse(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl> must start with 'int' or 'ref' token");
            System.exit(1);
        }
    }

    public void print() {
        if (queue.remove() == 1) {
            DeclInt declInt = new DeclInt();
            declInt.print();
        } else {
            DeclRef declRef = new DeclRef();
            declRef.print();
        }
    }
}
