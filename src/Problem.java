import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Problem {
	
	enum Type {
		Jambon, Tomates
	}
	
	int width;
	int height;
	int min_ham;
	int surf_max;
	ArrayList<ArrayList<Type> > map;
	

	void load(String filename) throws IOException {
		// TODO
		BufferedReader data = new BufferedReader(new FileReader(filename));
		String pizzaData=data.readLine();
		String[] ParsedData=pizzaData.split(" ");
		height=Integer.parseInt(ParsedData[0]);
		width=Integer.parseInt(ParsedData[1]);
		min_ham=Integer.parseInt(ParsedData[2]);
		surf_max=Integer.parseInt(ParsedData[3]);
		pizzaData=data.readLine();
		while(!pizzaData.isEmpty()){
			ArrayList<Type> Pcase=new ArrayList<Type>();
			if (pizzaData.equals("H")){
				Pcase.add(Type.Jambon);
				
			}
			else{
				Pcase.add(Type.Tomates);
				
			}
			map.add(Pcase);
			
			pizzaData=data.readLine();
			
		}
		
	}

}
