import java.io.IOException;

class DeclInt {

    public void parseDeclInt(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.INT) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-int> must end start 'int' token");
        } else {
            Core nextToken = S.nextToken();
            IdList idList = new IdList();
            idList.parseIdList(nextToken, S);
            nextToken = S.nextToken();
            if (nextToken != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The last token in <decl-int> must be ';' terminal");
            }
        }
    }

}
