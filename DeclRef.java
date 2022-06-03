import java.io.IOException;
import java.util.List;

class DeclRef {
    IdList idList;

    // Boolean input indicates if the variable should have global scope
    // stmtList input to add variables to if globalScope is false
    public void parse(Scanner S, boolean globalScope, List<String> stmtList) throws IOException {
        if (S.currentToken() != Core.REF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-ref> must start with 'ref' token");
            System.exit(1);
        } else {
            S.nextToken();
            idList = new IdList();
            idList.parse(S, false, globalScope, stmtList);
            if (S.currentToken() != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The last token in <decl-ref> must be ';' terminal");
                System.exit(1);
            }
            S.nextToken();
        }
    }

    public void semantic() {
        idList.semantic();
    }

    public void print() {
        System.out.println("ref ");
        idList.print();
        System.out.println(";");
    }
}
