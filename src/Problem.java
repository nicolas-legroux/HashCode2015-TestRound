import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Problem {
	
	enum PizzaTopping {
		HAM, TOMATOES
	}
	
	int width;
	int height;
	int min_ham;
	int surf_max;
	PizzaTopping[][] pizza;
	

	void load(String filename) throws IOException {
		// TODO
		BufferedReader data = new BufferedReader(new FileReader(filename));
		String pizzaData=data.readLine();
		String[] ParsedData=pizzaData.split(" ");
		height=Integer.parseInt(ParsedData[0]);
		width=Integer.parseInt(ParsedData[1]);
		min_ham=Integer.parseInt(ParsedData[2]);
		surf_max=Integer.parseInt(ParsedData[3]);		
		
		pizza=new PizzaTopping[height][width];
		
		for (int i=0;i<height;i++){
			pizzaData=data.readLine();
			
			for (int j=0;j<width;j++){
				pizza[i][j]=PizzaTopping.TOMATOES;
				String c=pizzaData.substring(j, j+1);
				if (c.equals("H")){
					pizza[i][j]=PizzaTopping.HAM;
				}
			}
		}		
	}
	
	void print(){
		for(int i=0; i< height; i++){
			for(int j=0; j<width; j++){
				if (pizza[i][j] == PizzaTopping.HAM){
					System.out.print("H");
				}
				else{
					System.out.print("T");
				}
			}
			
			System.out.print("\n");			
		}
	}

}
