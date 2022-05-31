import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class IdList {
    // Queue to store if a comma exists where 
    // 1 means means it exists and 0 means it does not
    static Queue<Integer> queueComma = new LinkedList<Integer>();
    // Queue to store id's
    static Queue<String> queueID = new LinkedList<String>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <id-list> must start with 'id' token");
            System.exit(1);
        } else {
            // Get ID and add it to list
            queueID.add(S.getID());
            // Save scanner position
            S.in.mark(1);
            Core nextToken = S.nextToken();
            if (nextToken == Core.COMMA) {
                queueComma.add(1);
                nextToken = S.nextToken();
                parse(nextToken, S);
            } else {
                queueComma.add(0);
                // Reset scanner position
                S.in.reset();
            }
        }
        // Add list to main queue
        Main main = new Main();
        main.queue.add(queueID);
    }

    public void print() {
        System.out.print(queueID.remove());
        if (queueComma.remove() == 1) {
            System.out.print(", ");
            print();
        }
    }

}
