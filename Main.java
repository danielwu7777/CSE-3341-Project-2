import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
	// Queue of lists of strings for printing
	Queue<List<String>> queue = new LinkedList<List<String>>();
	public static void main(String[] args) throws IOException {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);

		// Generate parse tree for the input Core program using recursive descent.

		// Parse the program
		Core firstToken = S.currentToken();
		Program program = new Program();
		program.parse(firstToken, S);

		// Perform semantic checks on the parse tree.

		// Use recursive descent to print the Core program from the parse tree.
		program.print();
	}
}