import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class DeclSeq {
    // Boolean to store if <decl-seq> exists where
    // true means it exists and false means it does not
    boolean b;
    // Queue to store <decl>'s
    Queue<Decl> queueDecl = new LinkedList<Decl>();
    Decl decl;
    DeclSeq declSeq;

    public void parse(Scanner S) throws IOException {
        decl = new Decl();
        decl.parse(S);
        queueDecl.add(decl);
        if (S.currentToken() != Core.BEGIN) {
            b = true;
            declSeq = new DeclSeq();
            declSeq.parse(S);
        } else {
            b = false;
        }
    }

    public void print() {
        queueDecl.remove().print();
        if (b) {
            declSeq.print();
        }
    }
}
