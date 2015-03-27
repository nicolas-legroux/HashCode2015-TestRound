import java.io.IOException;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Problem problem = new Problem();
		problem.load("data/test_round.in");
		problem.print();
		Graph graph = null;
		Solution solution = ColorGraph.color(graph);
		solution.print(problem);
	}

}
