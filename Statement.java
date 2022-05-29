import java.io.IOException;

class Statement {
    // 1 means <assign>, 2 means <if>, 3 means <loop>, 4 means <out>, 5 means <decl>
    int whichStatement;

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.ID) {
            whichStatement = 1;
            Assign assign = new Assign();
            assign.parse(currentToken, S);
        } else if (currentToken == Core.IF) {
            whichStatement = 2;
            If ifToken = new If();
            ifToken.parse(currentToken, S);
        } else if (currentToken == Core.WHILE) {
            whichStatement = 3;
            Loop loop = new Loop();
            loop.parse(currentToken, S);
        } else if (currentToken == Core.OUTPUT) {
            whichStatement = 4;
            Out out = new Out();
            out.parse(currentToken, S);
        } else if (currentToken == Core.INT || currentToken == Core.REF) {
            whichStatement = 5;
            Decl decl = new Decl();
            decl.parse(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: Statement must be an assign, if, loop, out, or decl token");
            System.exit(1);
        }
    }

    public void print() {
        switch (whichStatement) {
            case 1:
                Assign assign = new Assign();
                assign.print();
                break;
            case 2:
                If ifToken = new If();
                ifToken.print();
                break;
            case 3:
                Loop loop = new Loop();
                loop.print();
                break;
            case 4:
                Out out = new Out();
                out.print();
                break;
            case 5:
                Decl decl = new Decl();
                decl.print();
                break;
        }
    }
}
