import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphBuilder {
	
	Problem pb;
	Graph g;
	
	Map<Pair, List<Part>> presentParts;
	
	GraphBuilder(Problem pb) {
		this.pb = pb;
		this.presentParts = new HashMap<Pair, List<Part>>();
		this.g = new Graph();
	}
	
	private void addPresentPart(Pair pos, Part p) {
		List<Part> lp = presentParts.get(pos);
		if(lp == null) {
			lp = new LinkedList<Part>();
			presentParts.put(pos, lp);
		}
		
		lp.add(p);
	}
	
	private List<Part> getPresentParts(Pair pos) {
		return presentParts.get(pos);
	}
	
	private void addEdgeAndPresence(Part part, int i, int j, int h, int w) {
		for(int p = j; p < j + w; p++) {
			for(int q = i; q < i + h; q++) {
				
				List<Part> lp = getPresentParts(new Pair(p,q));
				
				if(lp != null) {
					for(Part pa : lp) {
						g.addUndirectedEdge(part, pa);
					}
				}
				
				addPresentPart(new Pair(p,q), part);
			}
		}
	}
	
	private void addPartsOfSize(int h, int w) {
		System.out.println("Processing for parts of w = " + w + " and h = " + h + " for surface "+ (h*w));
		int npart = 0;
		for(int i = 0; i <= pb.height - h; i++) {
			for(int j = 0; j <= pb.width - w; j++) {
				if(isPart(i, j, h, w)) {					
					Part pa = new Part(i,j,w,h);
					g.addNode(pa);
					npart++;
					addEdgeAndPresence(pa, i, j, h, w);
				}
			}
		}
		
		System.out.println("Found " + npart + " parts");
	}
	
	public Graph build() {

		for(int s = 12; s >= 3; s--) {		
			for(int w = 1; w <= s; w++) {
				if(s%w != 0) // only if the part is possible
					continue;
				addPartsOfSize(s/w, w);
			}				
		}
		
		return g;
	}
	
	private boolean isPart(int i, int j, int h, int w) {
		int nHam = 0;
		
		for(int p = j; p < j + w; p++) {
			for(int q = i; q < i + h; q++) {
				switch(pb.pizza[q][p]) {
				case HAM:
					nHam++;
					break;
				default:
					break;
				}
			}				
		}

		if(nHam == 3)
			return true;
		return false;
	}	
}