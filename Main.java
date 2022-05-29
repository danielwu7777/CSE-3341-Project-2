import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);

		// Generate parse tree for the input Core program using recursive descent.

		// Parse the program
		Core firstToken = S.currentToken();
		Program program = new Program();
		program.parseProgram(firstToken, S);

		// Perform semantic checks on the parse tree.

		// Use recursive descent to print the Core program from the parse tree.
	}
}