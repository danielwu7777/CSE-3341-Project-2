import java.io.IOException;

class DeclRef {

    public void parseDeclRef(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.REF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <decl-ref> must start with 'ref' token");
        } else {
            Core nextToken = S.nextToken();
            IdList idList = new IdList();
            idList.parseIdList(nextToken, S);
            nextToken = S.nextToken();
            if (nextToken != Core.SEMICOLON) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The last token in <decl-ref> must be ';' terminal");
            }
        }
    }

}
