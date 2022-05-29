import java.io.IOException;

class DeclRef {

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.REF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-ref> must start with 'ref' token");
            System.exit(1);
        } else {
            Core nextToken = S.nextToken();
            IdList idList = new IdList();
            idList.parse(nextToken, S);
            nextToken = S.nextToken();
            if (nextToken != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The last token in <decl-ref> must be ';' terminal");
                System.exit(1);
            }
        }
    }

    public void print() {
        System.out.print("    ");
        System.out.println("ref ");
        IdList idList = new IdList();
        idList.print();
        System.out.println(";");
    }

}
