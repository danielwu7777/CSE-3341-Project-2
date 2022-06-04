import java.io.IOException;
import java.util.List;

class Assign {
    String firstID;
    String secondID;
    // Integer to store type of assignment where
    // 1 meants 'input', 2 means <expr>, 3 means 'new', 4 means 'share'
    int i;
    Expr expr;

    public void parse(Scanner S, List<String> stmtList) throws IOException {
        // Must start with ID token
        if (S.currentToken() != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <assign> must start with ID token");
            System.exit(1);
        } else {
            // Store first ID
            firstID = S.getID();
            stmtList.add(firstID);

            // Next token must be '=' terminal
            if (S.nextToken() != Core.ASSIGN) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 2nd token in <assign> must be '=' terminal");
                System.exit(1);
            } else {
                switch (S.nextToken()) {
                    case INPUT:
                        i = 1;
                        if (S.nextToken() != Core.LPAREN) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'input' terminal (in <assign>) must be the left paren. terminal");
                            System.exit(1);
                        } else {
                            if (S.nextToken() != Core.RPAREN) {
                                S.t = Core.ERROR;
                                System.out.println(
                                        "ERROR: Token after left paren. terminal (in <assign>) must be the right paren. terminal");
                                System.exit(1);
                            }
                        }
                        S.nextToken();
                        break;

                    case NEW:
                        i = 3;
                        // Next token must be 'class' terminal
                        if (S.nextToken() != Core.CLASS) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'new' terminal (in <assign>) must be 'class' terminal");
                            System.exit(1);
                        }
                        S.nextToken();
                        break;
                    case SHARE:
                        i = 4;
                        // Next token must be 'id' terminal
                        if (S.nextToken() != Core.ID) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'share' terminal (in <assign>) must be 'id' terminal");
                            System.exit(1);
                        } else {
                            // Store second ID
                            secondID = S.getID();
                            stmtList.add(secondID);

                        }
                        S.nextToken();
                        break;
                    default:
                        i = 2;
                        // Parse <expr>
                        expr = new Expr();
                        expr.parse(S);
                }
                if (S.currentToken() != Core.SEMICOLON) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Missing ';' at the end of <assign>");
                    System.exit(1);
                }
                S.nextToken();
            }
        }
    }

    public void semantic() {
        List<String> list = Program.stack.pop();
        switch (i) {
            case 3:
                String s = list.remove(0);
                if (s.charAt(0) != 'r') {
                    System.out.println();
                }
        }
    }

    public void print() {
        System.out.print(firstID);
        System.out.print("=");
        switch (i) {
            case 1:
                System.out.println("input();");
                break;
            case 2:
                expr.print();
                System.out.println(";");
                break;
            case 3:
                System.out.println("new class;");
                break;
            case 4:
                System.out.println("share " + secondID + ";");
                break;
        }
    }
}
