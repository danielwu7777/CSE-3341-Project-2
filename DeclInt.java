import java.io.IOException;

class DeclInt {

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.INT) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-int> must end start 'int' token");
            System.exit(1);
        } else {
            Core nextToken = S.nextToken();
            IdList idList = new IdList();
            idList.parse(nextToken, S);
            nextToken = S.nextToken();
            if (nextToken != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The last token in <decl-int> must be ';' terminal");
                System.exit(1);
            }
        }
    }

    public void print() {
        System.out.print("    ");
        System.out.println("int ");
        IdList idList = new IdList();
        idList.print();
        System.out.println(";");
    }

}
