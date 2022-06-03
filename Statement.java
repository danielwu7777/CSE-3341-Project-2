import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Statement {
    // Integer to keep track of the of statement where
    // 1 means <assign>, 2 means <if>, 3 means <loop>, 4 means <out>, 5 means <decl>
    int i;
    Assign assign;
    If ifToken;
    Loop loop;
    Out out;
    Decl decl;
    // List of variables for <stmt> which have local scope (semantic checking)
    List<String> stmtList = new ArrayList<String>();
    public void parse(Scanner S) throws IOException {
        switch (S.currentToken()) {
            case ID:
                i = 1;
                assign = new Assign();
                assign.parse(S, stmtList);
                break;
            case IF: 
                i = 2;
                ifToken = new If();
                ifToken.parse(S);
                break;
            case WHILE:
                i = 3;
                loop = new Loop();
                loop.parse(S);
                break;
            case OUTPUT:
                i = 4;
                out = new Out();
                out.parse(S);
                break;
            default:
                // Then must be <decl>
                i = 5;
                decl = new Decl();
                decl.parse(S, false, stmtList);
        }
        // Add stmtList to stack
        Program.stack.add(stmtList);
    }
    public void semantic() {
        //
    }
    public void print() {
        switch (i) {
            case 1:
                assign.print();
                break;
            case 2:
                ifToken.print();
                break;
            case 3:
                loop.print();
                break;
            case 4:
                out.print();
                break;
            case 5:
                decl.print();
                break;
        }
    }

    
}
