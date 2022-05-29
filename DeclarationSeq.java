import java.io.IOException;

class DeclSeq {
    boolean hasDeclSeq = false;

    public void parse(Core currentToken, Scanner S) throws IOException {
        Decl decl = new Decl();
        decl.parse(currentToken, S);
        // Save scanner position
        S.in.mark(1);
        Core nextToken = S.nextToken();
        if (nextToken == Core.INT || nextToken == Core.REF) {
            hasDeclSeq = true;
            parse(nextToken, S);
        } else {
            S.in.reset();
        }
    }

}
