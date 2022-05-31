import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Factor {

    // List to store id's
    static List<String> listID = new LinkedList<String>();
    // List to store types of factors where 
    // 1 means 'id', 2 means 'const', and 3 means '('
    static List<Integer> listINT = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken == Core.ID) {
            // Add get ID from scanner and add to list
            listID.add(S.getID());
            listINT.add(1);
        } else if (currentToken == Core.CONST) {
            // Add CONST from scanner and add to list as a string
            listID.add(Integer.toString(S.getCONST()));
            listINT.add(2);
        } else if (currentToken == Core.LPAREN) {
            listINT.add(3);
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
        if (listINT.size() > 0) {
            switch (listINT.remove(0)) {
                case 1:
                    System.out.print(listID.remove(0));
                    break;
                case 2:
                    System.out.print(listID.remove(0));
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
}
