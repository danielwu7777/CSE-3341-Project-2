import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class IdList {
    // Boolean to store if <id-list> exists where
    // true means it exists and false means it does not
    boolean b;
    // Queue to store id's
    Queue<String> queueID = new LinkedList<String>();
    IdList idList;

    // If boolean isInt is true, these variables are integers, otherwise,
    // they're references
    // globalScope boolean indicates if the variable should have global scope
    // stmtList input to add to if globalScope is false
    public void parse(Scanner S, boolean isInt, boolean globalScope, List<String> stmtList) throws IOException {
        if (S.currentToken() != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <id-list> must start with 'id' token");
            System.exit(1);
        } else {
            // Get ID and add it to list
            queueID.add(S.getID());
            // Add to declSeqList for semantic checking (i means int and r means ref)
            if (globalScope) {
                if (isInt) {
                    Program.declSeqList.add("i" + S.getID());
                } else {
                    Program.declSeqList.add("r" + S.getID());
                }
            } else {
                if (isInt) {
                    stmtList.add("i" + S.getID());
                } else {
                    stmtList.add("r" + S.getID());
                }
            }
            if (S.nextToken() == Core.COMMA) {
                b = true;
                S.nextToken();
                idList = new IdList();
                idList.parse(S, isInt, globalScope, stmtList);
            } else {
                b = false;
            }
        }
    }

    public void semantic() {
        List<String> list = Program.stack.pop();
        // Check for “doubly-declared” variable names.
        int size = list.size();
        for (int i = 1; i < size - 1; i++) {
            String removed = list.remove(0);
            if (list.contains(removed)) {
                System.out.println("SEMANTIC ERROR: " + removed.substring(1) + " was doubly-declared");
            }
        }
    }

    public void print() {
        System.out.print(queueID.remove());
        if (b) {
            System.out.print(", ");
            idList.print();
        }
    }
}
