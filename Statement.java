import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Statement {
    
    // Queue to keep track of types of statements where 
    // 1 means <assign>, 2 means <if>, 3 means <loop>, 4 means <out>, 5 means <decl> 
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.ID) {
            queue.add(1);
            Assign assign = new Assign();
            assign.parse(currentToken, S);
        } else if (currentToken == Core.IF) {
            queue.add(2);
            If ifToken = new If();
            ifToken.parse(currentToken, S);
        } else if (currentToken == Core.WHILE) {
            queue.add(3);
            Loop loop = new Loop();
            loop.parse(currentToken, S);
        } else if (currentToken == Core.OUTPUT) {
            queue.add(4);
            Out out = new Out();
            out.parse(currentToken, S);
        } else if (currentToken == Core.INT || currentToken == Core.REF) {
            queue.add(5);
            Decl decl = new Decl();
            decl.parse(currentToken, S);
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: Statement must be an assign, if, loop, out, or decl token");
            System.exit(1);
        }
    }

    public void print() {
        switch (queue.remove()) {
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
