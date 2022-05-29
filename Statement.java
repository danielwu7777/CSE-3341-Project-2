import java.io.IOException;

class Statement {
    // 1 means <assign>, 2 means <if>, 3 means <loop>, 4 means <out>, 5 means <decl>
    int whichStatement;

    public void parseStatement(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.ID) {
            whichStatement = 1;
            Assign assign = new Assign();
            assign.parseAssign(currentToken, S);
        } else if (currentToken == Core.IF) {
            whichStatement = 2;
            If ifToken = new If();
            ifToken.parseIf(currentToken, S);
        } else if (currentToken == Core.WHILE) {
            whichStatement = 3;
            Loop loop = new Loop();
            loop.parseLoop(currentToken, S);
        } else if (currentToken == Core.OUTPUT) {
            whichStatement = 4;
            Out out = new Out();
            out.parseOut(currentToken, S);
        } else if (currentToken == Core.INT || currentToken == Core.REF) {
            whichStatement = 5;
            Decl decl = new Decl();
            decl.parseDecl(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: Statement must be an assign, if, loop, out, or decl token");
        }
    }

}
