import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class IdList {
    boolean comma = false;
    // List to store id's
    List<String> list = new LinkedList<String>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <id-list> must start with 'id' token");
            System.exit(1);
        } else {
            // Get ID and add it to list
            list.add(S.getID());
            // Save scanner position
            S.in.mark(1);
            Core nextToken = S.nextToken();
            if (nextToken == Core.COMMA) {
                comma = true;
                nextToken = S.nextToken();
                parse(nextToken, S);
            } else {
                // Reset scanner position
                S.in.reset();
            }
        }
        // Add list to main queue
        Main main = new Main();
        main.queue.add(list);
    }

    public void print() {
        System.out.print(list.remove(0));
        if (comma) {
            System.out.print(", ");
            print();
        }
    }

}
