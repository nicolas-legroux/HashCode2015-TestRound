import java.io.IOException;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Problem p = new Problem();
		p.load("data/test_round.in");
		
		p.print();	
		
		Problem problem = new Problem();
		problem.load("data/test_round.in");
		problem.print();


		GraphBuilder gb = new GraphBuilder(p);
		Graph graph = gb.build();
		graph.print();
		
		Solution solution = ColorGraph.color(graph);
		solution.print(problem);

		solution.save("data/result.txt");
	}

}
