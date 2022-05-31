import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class If {
    // Queue to store if an 'else' statement exists where
    // 1 means means it exists and 0 means it does not
    static Queue<Integer> queue = new LinkedList<Integer>();

    public void parse(Core currentToken, Scanner S) throws IOException {
        if (currentToken != Core.IF) {
            S.t = Core.ERROR;
            System.out.println("ERROR: <if> token must start with 'if' terminal");
            System.exit(1);
        }
        Core nextToken = S.nextToken();
        Cond cond = new Cond();
        cond.parse(nextToken, S);
        nextToken = S.nextToken();
        if (nextToken != Core.THEN) {
            S.t = Core.ERROR;
            System.out.println("ERROR: The 3rd token in <if> token must be 'then' terminal");
            System.exit(1);
        } else {
            nextToken = S.nextToken();
            if (nextToken != Core.LBRACE) {
                S.t = Core.ERROR;
                System.out.println("ERROR: The 4th token in <if> token must be the '{' terminal");
                System.exit(1);
            } else {
                nextToken = S.nextToken();
                StatementSeq statementSeq = new StatementSeq();
                statementSeq.parse(nextToken, S);
                nextToken = S.nextToken();
                if (nextToken != Core.RBRACE) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: The token after stmt-seq in <if> must be the '}' terminal");
                    System.exit(1);
                } else {
                    // Check for 'else'
                    // Save scanner position
                    S.in.mark(1);
                    nextToken = S.nextToken();
                    if (nextToken == Core.ELSE) {
                        queue.add(1);
                        nextToken = S.nextToken();
                        if (nextToken != Core.LBRACE) {
                            S.t = Core.ERROR;
                            System.out.println("ERROR: The token after 'else' in <if> must be the '{' terminal");
                            System.exit(1);
                        } else {
                            nextToken = S.nextToken();
                            statementSeq.parse(nextToken, S);
                            nextToken = S.nextToken();
                            if (nextToken != Core.RBRACE) {
                                S.t = Core.ERROR;
                                System.out.println(
                                        "ERROR: The token after the stmt-seq in the 'else' case of <if> must be the '}' terminal");
                                System.exit(1);
                            }
                        }
                    } else {
                        queue.add(0);
                        // Reset scanner position
                        S.in.reset();
                    }
                }
            }
        }
    }

    public void print() {
        System.out.print("if ");
        Cond cond = new Cond();
        cond.print();
        System.out.println(" then {");
        System.out.print("        ");
        StatementSeq statementSeq = new StatementSeq();
        statementSeq.print();
        System.out.print("   }");
        if (queue.remove() == 1) {
            System.out.println(" else {");
            System.out.print("        ");
            statementSeq.print();
            System.out.println("   }");
        }
    }
}
