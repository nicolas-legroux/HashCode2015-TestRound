import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphBuilder {
	
	Problem pb;
	Graph g;	
	Map< Pair, List<Part> > intersectingParts;
	
	GraphBuilder(Problem pb) {
		this.pb = pb;
		intersectingParts = new HashMap<Pair, List<Part>>();
		this.g = new Graph();
		for(int x=0; x<pb.width; x++){
			for(int y=0; y < pb.height; y++){				
				intersectingParts.put(new Pair(x, y), new LinkedList<Part>());
			}
		}
	}
	
	private void addPresentPart(int x, int y, Part part) {
		List<Part> parts = intersectingParts.get(new Pair(x, y));		
		parts.add(part);
	}
	
	private List<Part> getPresentParts(int x, int y) {
		return intersectingParts.get(new Pair(x, y));
	}
	
	private void addEdgeAndPresence(Part part, int x0, int y0, int w, int h) {
		for(int x = x0; x < x0 + w; x++) {
			for(int y = y0; y < y0 + h; y++) {
				
				if(x<pb.width && y<pb.height){
				
					List<Part> lp = getPresentParts(x, y);
					
					if(lp != null) {					
						for(Part pa : lp) {
							g.addUndirectedEdge(part, pa);
						}
					}
					
					addPresentPart(x, y, part);
				}
			}
		}
	}
	
	private void addPartsOfSize(int w, int h) {
		System.out.println("Processing for parts of w = " + w + " and h = " + h + " for surface "+ (h*w));
		int numberParts = 0;
		for(int y0 = 0; y0 <= pb.height - h; y0++) {
			for(int x0 = 0; x0 <= pb.width - w; x0++) {
				if(isPart(x0, y0, w, h)) {
					System.out.println("Found part with x0=" + x0 + ", y0=" + y0 + " (w=" + w + ", h=" + h + ")");
					Part part = new Part(x0, y0, w, h);
					g.addPart(part);
					numberParts++;
					addEdgeAndPresence(part, x0, y0, w, h);
				}
			}
		}
		
		System.out.println("Found " + numberParts + " parts");
	}
	
	public Graph build() {

		for(int s = 12; s >= 3; s--) {		
			for(int w = 1; w <= s; w++) {
				if(s%w == 0) // only if the part is possible					
					addPartsOfSize(w, s/w);
			}				
		}
		
		return g;
	}
	
	private boolean isPart(int x0, int y0, int w, int h) {
		int countHam = 0;
		
		for(int x = x0; x < x0 + w; x++) {
			for(int y = y0; y < y0 + h; y++) {
				switch(pb.pizza[x][y]) {
				case HAM:
					countHam++;
					break;
				default:
					break;
				}
			}				
		}

		if(countHam == 3)
			return true;
		return false;
	}	
}