import java.io.IOException;

class DeclRef {
    IdList idList;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() != Core.REF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-ref> must start with 'ref' token");
            System.exit(1);
        } else {
            S.nextToken();
            idList = new IdList();
            idList.parse(S);
            if (S.currentToken() != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The last token in <decl-ref> must be ';' terminal");
                System.exit(1);
            }
            S.nextToken();
        }
    }

    public void print() {
        System.out.println("ref ");
        idList.print();
        System.out.println(";");
    }
}
