import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class IdList {
    // Boolean to store if <id-list> exists where
    // true means it exists and false means it does not
    Boolean b;
    // Queue to store id's
    Queue<String> queueID = new LinkedList<String>();
    IdList idList;

    public void parse(Scanner S) throws IOException {
        if (S.currentToken() != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <id-list> must start with 'id' token");
            System.exit(1);
        } else {
            // Get ID and add it to list
            queueID.add(S.getID());
            if (S.nextToken() == Core.COMMA) {
                b = true;
                S.nextToken();
                idList = new IdList();
                idList.parse(S);
            } else {
                b = false;
            }
            //S.nextToken();
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
