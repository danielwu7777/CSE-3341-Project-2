import java.io.IOException;
import java.util.List;

class DeclInt {
    IdList idList;

    // Boolean input indicates if the variable should have global scope
    // stmtList input to add to if globalScope is false
    public void parse(Scanner S, boolean globalScope, List<String> stmtList) throws IOException {
        if (S.currentToken() != Core.INT) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-int> must start with 'int' token");
            System.exit(1);
        } else {
            S.nextToken();
            idList = new IdList();
            idList.parse(S, true, globalScope, stmtList);
            if (S.currentToken() != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: <decl-int> must end with semicolon");
                System.exit(1);
            }
            S.nextToken();
        }
    }
    public void semantic() {
        idList.semantic();
    }
    public void print() {
        System.out.print("int ");
        idList.print();
        System.out.println(";");
    }

    
}
