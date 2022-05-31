import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


class Assign {
    // Queue to store id's
    static Queue<String> listID = new LinkedList<String>();
    // Queue to store type of assignment where 
    // 1 meants 'input', 2 means <expr>, 3 means 'new', 4 means 'share' 
    static Queue<Integer> listINT = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        // Must start with ID token
        if (currentToken != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: Assign must start with ID token");
            System.exit(1);
        } else {
            // Get ID and add it to list
            listID.add(S.getID());
            Core nextToken = S.nextToken();
            // Next token must be '=' terminal
            if (nextToken != Core.ASSIGN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 2nd token in <assign> must be '=' terminal");
                System.exit(1);
            } else {
                nextToken = S.nextToken();
                Expr expr = new Expr();
                switch (nextToken) {
                    case INPUT:
                        listINT.add(1);
                        nextToken = S.nextToken();
                        if (nextToken != Core.LPAREN) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'input' terminal (in <assign>) must be the left paren. terminal");
                            System.exit(1);
                        } else {
                            nextToken = S.nextToken();
                            if (nextToken != Core.RPAREN) {
                                S.t = Core.ERROR;
                                System.out.println(
                                        "ERROR: Token after left paren. terminal (in <assign>) must be the right paren. terminal");
                                System.exit(1);
                            }
                        }
                        break;
                    case ID: // <expr>
                        listINT.add(2);
                        expr.parse(nextToken, S);

                        break;
                    case CONST: // <expr>
                        listINT.add(2);
                        expr.parse(nextToken, S);

                        break;
                    case LPAREN: // <expr>
                        listINT.add(2);
                        expr.parse(nextToken, S);

                        break;
                    case NEW:
                        listINT.add(3);
                        // Next token must be 'class' terminal
                        nextToken = S.nextToken();
                        if (nextToken != Core.CLASS) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'new' terminal (in <assign>) must be 'class' terminal");
                            System.exit(1);
                        }
                        break;
                    case SHARE:
                        listINT.add(4); 
                        // Next token must be 'id' terminal
                        nextToken = S.nextToken();
                        if (nextToken != Core.ID) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'share' terminal (in <assign>) must be 'id' terminal");
                            System.exit(1);
                        } else {
                            // Get ID, append a semicolon, and add to list
                            listID.add(S.getID());

                        }
                        break;
                    default:
                        S.t = Core.ERROR;
                        System.out.println(
                                "ERROR: Token after '=' terminal (in <assign>) must be 'input', <expr>, 'new', or 'share'");
                        System.exit(1);
                }
                nextToken = S.nextToken();
                if (nextToken != Core.SEMICOLON) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Missing ';' after expr in <assign>");
                    System.exit(1);
                }else{

                }
            }
        }
    }

    public void print() {
        System.out.print(listID.remove());
        System.out.print("=");
        switch (listINT.remove()) {
            case 1:
                System.out.println("input();");
                break;
            case 2:
                Expr expr = new Expr();
                expr.print();
                System.out.println(";");
                // Indent if needed
                if (listID.size() > 0) {
                    System.out.print("    ");
                }
                break;
            case 3:
                System.out.println("new class;");
                break;
            case 4:
                System.out.println("share " + listID.remove(0) + ";");
        }
    }
}
