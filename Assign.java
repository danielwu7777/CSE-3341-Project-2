import java.io.IOException;

class Assign {
    // 1 meants 'input', 2 means <expr>, 3 means 'new', 4 means 'share'
    int tokenAfterAssign;

    public void parse(Core currentToken, Scanner S) throws IOException {
        // Must start with ID token
        if (currentToken != Core.ID) {
            S.t = Core.ERROR;
            System.out.println("ERROR: Assign must start with ID token");
            System.exit(1);
        } else {
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
                        tokenAfterAssign = 1;
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
                        tokenAfterAssign = 2;
                        expr.parse(nextToken, S);
                        break;
                    case CONST: // <expr>
                        tokenAfterAssign = 2;
                        expr.parse(nextToken, S);
                        break;
                    case LPAREN: // <expr>
                        tokenAfterAssign = 2;
                        expr.parse(nextToken, S);
                        break;
                    case NEW:
                        tokenAfterAssign = 3;
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
                        tokenAfterAssign = 4;
                        // Next token must be 'id' terminal
                        nextToken = S.nextToken();
                        if (nextToken != Core.ID) {
                            S.t = Core.ERROR;
                            System.out.println(
                                    "ERROR: Token after 'share' terminal (in <assign>) must be 'id' terminal");
                            System.exit(1);
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
                    System.out.println("ERROR: The last token in <assign> must be ';' terminal");
                    System.exit(1);
                }
            }
        }
    }
}
