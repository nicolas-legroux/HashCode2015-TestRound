import java.io.IOException;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Problem p = new Problem();
		p.load("data/test_round.in");
		Graph graph = null;
		Solution solution = ColorGraph.color(graph);
		solution.print(p);
	}

}
