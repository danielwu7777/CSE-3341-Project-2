import java.io.IOException;

class Decl {
    // Boolean to store which <decl> where
    // true means <decl-int> and false means <decl-ref>
    boolean b;
    DeclInt declInt;
    DeclRef declRef;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.INT) {
            b = true;
            declInt = new DeclInt();
            declInt.parse(S);
        } else {
            // Must be <decl-ref>
            b = false;
            declRef = new DeclRef();
            declRef.parse(S);
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
