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
	    		
		GraphBuilder gb = new GraphBuilder(p);
		long startTimeGraphBuilding = System.currentTimeMillis();
		Graph graph = gb.build();
		long endTimeGraphBuilding = System.currentTimeMillis();
		System.out.println("Time to build the graph : " + (endTimeGraphBuilding-startTimeGraphBuilding) + "ms");
		//graph.print();	
	
		Solution solution = ColorGraph.color(graph, p);
		solution.print(p);

		solution.save("data/result.txt");
		
	}

}
