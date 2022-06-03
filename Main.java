import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	// Queue of queues of strings for printing
	Queue<Queue<String>> queue = new LinkedList<Queue<String>>();
	public static void main(String[] args) throws IOException {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);

		// Generate parse tree for the input Core program using recursive descent.

		// Parse the program
		Program program = new Program();
		program.parse(S);

		// Perform semantic checks on the parse tree.

		// Use recursive descent to print the Core program from the parse tree.
		program.print();
	}
}
