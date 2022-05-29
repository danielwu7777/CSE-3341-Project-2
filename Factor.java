import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Factor {
    // 1 means 'id', 2 means 'const', 3 means '('
    int whichFactor;
    // List to store id's
    List<String> list = new LinkedList<String>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.ID) {
            // Add get ID from scanner and add to list
            list.add(S.getID());
            whichFactor = 1;
        } else if (currentToken == Core.CONST) {
            // Add CONST from scanner and add to list as a string
            list.add(Integer.toString(S.getCONST()));
            whichFactor = 2;
        } else if (currentToken == Core.LPAREN) {
            whichFactor = 3;
            Core nextToken = S.nextToken();
            Expr expr = new Expr();
            expr.parse(nextToken, S);
            nextToken = S.nextToken();
            // Token after <expr> must be ')' terminal
            if (nextToken != Core.RPAREN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: Token after expression in <factor> must be ')' terminal");
                System.exit(1);
            }
        } else {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 1st token in <factor> must be 'id', 'const', or '(' terminal");
            System.exit(1);
        }
    }

    public void print() {
        switch (whichFactor) {
            case 1:
                System.out.print(list.remove(0));
                break;
            case 2:
                System.out.print(list.remove(0));
                break;
            case 3:
                System.out.print("(");
                Expr expr = new Expr();
                expr.print();
                System.out.print(")");
                break;
        }
    }

}
