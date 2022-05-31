import java.io.IOException;

class Decl {

    boolean isDeclInt; // this isnt getting saved after parsing!!!!!!!!!!!!!!!!!!!!!!111

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.INT) {
            isDeclInt = true;
            DeclInt declInt = new DeclInt();
            declInt.parse(currentToken, S);
        } else if (currentToken == Core.REF) {
            isDeclInt = false;
            DeclRef declRef = new DeclRef();
            declRef.parse(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl> must start with 'int' or 'ref' token");
            System.exit(1);
        }
    }

    public void print() {
        System.out.println(isDeclInt);
        if (isDeclInt) {
            System.out.println("we should get here");
            DeclInt declInt = new DeclInt();
            declInt.print();
        } else {
            System.out.println("we should NOT get here");
            DeclRef declRef = new DeclRef();
            declRef.print();
        }
    }

}
