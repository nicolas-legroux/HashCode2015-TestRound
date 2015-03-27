import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;


public class Solution {
	
	HashSet<Part> parts;
	
	Solution() {
		parts = new HashSet<Part>();
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
	
	void print(Problem p) {
		for (int y = 0 ; y < p.height ; ++y) {
			for (int x = 0 ; x < p.width ; ++x) {
				for (Part part : parts) {
					if (part.contains(x, y))
						System.out.print("*");
					else
						System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
	
}
