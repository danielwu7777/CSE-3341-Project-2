import java.io.IOException;

class Decl {
    // 1 means <decl-int>, 2 means <decl-ref>
    int whichDecl;

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.INT) {
            whichDecl = 1;
            DeclInt declInt = new DeclInt();
            declInt.parse(currentToken, S);
        } else if (currentToken == Core.REF) {
            whichDecl = 2;
            DeclRef declRef = new DeclRef();
            declRef.parse(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl> must start with 'int' or 'ref' token");
            System.exit(1);
        }
    }

}
