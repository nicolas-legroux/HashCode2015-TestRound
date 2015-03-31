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
	
		ColorGraph colorGraphSolver = new ColorGraph(p);
		colorGraphSolver.solveWithRandomRemovals(0.30, 10000);
		Solution solution = colorGraphSolver.getSolution();
		
		solution.print();
		solution.save("result.txt");	
	}

}
