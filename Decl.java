import java.io.IOException;

class Decl {

    boolean isDeclInt = false;

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.INT) {
            isDeclInt = true;
            DeclInt declInt = new DeclInt();
            declInt.parse(currentToken, S);
        } else if (currentToken == Core.REF) {
            DeclRef declRef = new DeclRef();
            declRef.parse(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl> must start with 'int' or 'ref' token");
            System.exit(1);
        }
    }

    public void print() {
        if (isDeclInt) {
            DeclInt declInt = new DeclInt();
            declInt.print();
        } else {
            DeclRef declRef = new DeclRef();
            declRef.print();
        }
    }

}
