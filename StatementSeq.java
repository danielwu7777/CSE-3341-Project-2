import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class StatementSeq {
    // Boolean to store if <stmt-seq> exists where
    // true means it exists and false means it does not
    boolean b;
    // Queue to store <stmt>'s
    Queue<Statement> queueStmts = new LinkedList<Statement>();
    Statement stmt;
    StatementSeq stmtSeq;

    public void parse(Scanner S) throws IOException {
        stmt = new Statement();
        stmt.parse(S);
        queueStmts.add(stmt);
        if (S.currentToken() == Core.END || S.currentToken() == Core.RBRACE) {
            b = false;
        } else {
            b = true;
            stmtSeq = new StatementSeq();
            stmtSeq.parse(S);
        }
    }

    public void print() {
        queueStmts.remove().print();
        if (b) {
            stmtSeq.print();
        }
    }
}
