import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Solution {
	
	private HashSet<Part> parts;
	private boolean free[][];
	
	Problem pb;
	
	Solution(Problem pb) {
		this.parts = new HashSet<Part>();
		this.pb = pb;
		this.free = new boolean[pb.width][pb.height];
		for(int x=0; x<pb.width; x++){
			for(int y=0; y<pb.height; y++){
				free[x][y] = true;
			}
		}
	}
	
	Solution(Solution solution){
		this.pb = solution.pb;
		this.free = new boolean[pb.width][pb.height];
		for(int x=0; x<pb.width; x++){
			for(int y=0; y<pb.height; y++){
				free[x][y] = solution.free[x][y];
			}
		}
		this.parts = new HashSet<Part>();
		for(Part p : solution.parts){
			this.parts.add(p);
		}
	}
	
	public void addPart(Part p){
		parts.add(p);
		for(int x=p.left; x<=p.right; x++){
			for(int y=p.top; y<=p.bottom; y++){
				free[x][y] = false;
			}
		}
	}
	
	public void removePart(Part p){
		parts.remove(p);
		for(int x=p.left; x<=p.right; x++){
			for(int y=p.top; y<=p.bottom; y++){
				free[x][y] = true;
			}
		}		
	}
	
	public boolean isFree(int x, int y){
		return free[x][y];
	}
	
	public void resetParts(HashSet<Part> parts){
		for(int x=0; x<pb.width; x++){
			for(int y=0; y<pb.height; y++){
				free[x][y] = true;
			}
		}
		this.parts = new HashSet<Part>();
		for(Part p : parts){
			addPart(p);
		}
	}
	
	public HashSet<Part> getParts(){
		return parts;
	}

	void save(String filename) throws IOException {
		File file = new File(filename);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(parts.size() + "\n"));
		
		for(Part part : parts){
			bw.write(String.valueOf(part.top) + " " + 
					String.valueOf(part.left) + " " +
					String.valueOf(part.bottom) + " " +
					String.valueOf(part.right) + "\n");
		}	
		
		bw.close();
		
		System.out.println("Done writing");
	}
	
	int getScore(){
		int score = 0;
		for (int y = 0 ; y < pb.height ; ++y) {
			for (int x = 0 ; x < pb.width ; ++x) {
				if(!free[x][y])
					score++;
			}
		}
		
		return score;
	}
	
	void print() {		
		for (int y = 0 ; y < pb.height ; ++y) {
			for (int x = 0 ; x < pb.width ; ++x) {			
				if (!free[x][y] && pb.pizza[x][y] == Problem.PizzaTopping.HAM)
					System.out.print("X");
				else if (!free[x][y])
					System.out.print("x");				
				else if(pb.pizza[x][y] == Problem.PizzaTopping.HAM)
					System.out.print(".");
				else
					System.out.print(" ");
			}
			System.out.print("\n");			
		}
		
		System.out.println("\nThe score of the solution is " + getScore());
	}	
}