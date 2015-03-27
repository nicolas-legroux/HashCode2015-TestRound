import java.io.IOException;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Problem p = new Problem();
		p.load("data/test_round.in");
		
		GraphBuilder gb = new GraphBuilder(p);
		gb.build();
	}

}
