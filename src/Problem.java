import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Problem {
	
	enum Type {
		Jambon, Tomates
	}
	
	int width;
	int height;
	int min_ham;
	int surf_max;
	Type[][] map;
	

	void load(String filename) throws IOException {
		// TODO
		BufferedReader data = new BufferedReader(new FileReader(filename));
		String pizzaData=data.readLine();
		String[] ParsedData=pizzaData.split(" ");
		height=Integer.parseInt(ParsedData[0]);
		width=Integer.parseInt(ParsedData[1]);
		min_ham=Integer.parseInt(ParsedData[2]);
		surf_max=Integer.parseInt(ParsedData[3]);
		
		
		
		map=new Type[height][width];
		
		for (int i=0;i<height;i++){
			pizzaData=data.readLine();
			
			for (int j=0;j<width;j++){
				map[i][j]=Type.Tomates;
				String c=pizzaData.substring(j, j+1);
				if (c.equals("H")){
					map[i][j]=Type.Jambon;
				}
			}
		}		
	}
	
	void print(){
		for(int i=0; i< height; i++){
			for(int j=0; j<width; j++){
				if (map[i][j] == Type.Jambon){
					System.out.print("H ");
				}
				else{
					System.out.print("T ");
				}
			}
			
			System.out.print("\n");			
		}
	}

}
