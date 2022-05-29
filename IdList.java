import java.io.IOException;

class IdList {
    boolean comma = false;

    public void parseIdList(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <id-list> must start with 'id' token");
        } else {
            // Save scanner position
            S.in.mark(1);
            Core nextToken = S.nextToken();
            if (nextToken == Core.COMMA) {
                comma = true;
                nextToken = S.nextToken();
                parseIdList(nextToken, S);
            } else {
                // Reset scanner position
                S.in.reset();
            }
        }
    }

}
