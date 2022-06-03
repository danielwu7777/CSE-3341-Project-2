import java.io.IOException;

class DeclInt {
    IdList idList;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() != Core.INT) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-int> must start with 'int' token");
            System.exit(1);
        } else {
            S.nextToken();
            idList = new IdList();
            idList.parse(S);
            if (S.currentToken() != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: <decl-int> must end with semicolon");
                System.exit(1);
            }
            S.nextToken();
        }
    }

    public void print() {
        System.out.print("int ");
        idList.print();
        System.out.println(";");
    }
}
