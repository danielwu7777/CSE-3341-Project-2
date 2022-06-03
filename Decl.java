import java.io.IOException;
import java.util.List;

class Decl {
    // Boolean to store which <decl> where
    // true means <decl-int> and false means <decl-ref>
    boolean b;
    DeclInt declInt;
    DeclRef declRef;

    // Boolean input indicates if the variables in <decl> should have global scope
    // stmtList input to add to if boolean input is false
    public void parse(Scanner S, boolean isDeclAfterProg, List<String> stmtList) throws IOException {
        if (S.currentToken() == Core.INT) {
            b = true;
            declInt = new DeclInt();
            declInt.parse(S, isDeclAfterProg, stmtList);
        } else {
            // Must be <decl-ref>
            b = false;
            declRef = new DeclRef();
            declRef.parse(S, isDeclAfterProg, stmtList);
        }
    }

    public void semantic() {
        if (b) {
            declInt.semantic();
        } else {
            declRef.semantic();
        }
    }

    public void print() {
        if (b) {
            declInt.print();
        } else {
            declRef.print();
        }
    }
}
