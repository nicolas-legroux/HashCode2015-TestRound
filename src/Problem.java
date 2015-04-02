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
	
	//For stats
	int numberOfHam = 0;
	
	void load(String filename) throws IOException {
		
		BufferedReader data = new BufferedReader(new FileReader(filename));
		String pizzaData = data.readLine();
		String[] ParsedData = pizzaData.split(" ");
		height = Integer.parseInt(ParsedData[0]);
		width = Integer.parseInt(ParsedData[1]);
		min_ham = Integer.parseInt(ParsedData[2]);
		surf_max = Integer.parseInt(ParsedData[3]);	
		
		pizza = new PizzaTopping[width][height];
		
		for (int y=0; y<height; y++){
			pizzaData=data.readLine();
			
			for (int x=0; x<width; x++){
				pizza[x][y]=PizzaTopping.TOMATOES;
				String c=pizzaData.substring(x, x+1);
				if (c.equals("H")){
					numberOfHam++;
					pizza[x][y]=PizzaTopping.HAM;
				}
			}
		}			
	}
	
	void print(){
		for(int y=0; y< height; y++){
			for(int x=0; x<width; x++){
				if (pizza[x][y] == PizzaTopping.HAM){
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