class Program {
    public static void parseProgram(Core currentToken, Scanner S) {
        // Program must start with program token
        if (currentToken != Core.PROGRAM) {
			S.t = Core.ERROR;
			System.out.println("ERROR: Program must start with program token");
		}
		else {
			// Get the next token and parse accordingly
			Core nextToken = S.nextToken();
			if (nextToken == Core.BEGIN) {
				// Parse statement sequence
                nextToken = S.nextToken();
                StatementSeq.parseStatementSeq(nextToken, S);
                // Program must finish with End token
                nextToken = S.nextToken();
                if (nextToken != Core.END) {
                    S.t = Core.ERROR;
                    System.out.println("ERROR: Program must end with end token");
                }
                else {
                    // process end token
                }
			}
            // Parse DeclarationSeq
            else {
                DeclarationSeq.parseDeclarationSeq(nextToken, S);
            }
		}
    }
}
