import java.io.IOException;
import java.util.Random;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Random random = new Random(25);
		
		Problem pb = new Problem();
		pb.load("data/test_round.in");		
		//pb.print();
		
		if(args.length>0){
			Solution solution = Solution.loadFromFile(args[0], pb);	
			ColorGraph colorGraphSolver = new ColorGraph(pb, solution);
			colorGraphSolver.solveWithRandomRemovals(0.8, 1000000);	
		}			
	}
}