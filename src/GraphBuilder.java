
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class GraphBuilder {
	
	Problem pb;
	Graph g;
	
	Map<Pair, List<Part>> presentparts;
	
	GraphBuilder(Problem pb) {
		this.pb = pb;
		this.presentparts = new HashMap<Pair, List<Part>>();
		this.g = new Graph();
	}
	
	private void addPresentPart(Pair pos, Part p) {
		List<Part> lp = presentparts.get(pos);
		if(lp == null) {
			lp = new LinkedList<Part>();
			presentparts.put(pos, lp);
		}
		
		lp.add(p);
	}
	
	private List<Part> getPresentParts(Pair pos) {
		return presentparts.get(pos);
	}
	
	private void addEdgeAndPresence(Part part, int i, int j, int h, int w) {
		for(int p = j; p < j + w; p++) {
			for(int q = i; q < i + h; q++) {
				
				List<Part> lp = getPresentParts(new Pair(p,q));
				
				if(lp != null) {
					for(Part pa : lp) {
						g.addPair(part, pa);
					}
				}
				
				addPresentPart(new Pair(p,q), part);
			}
		}
	}
	
	public Graph build() {

		for(int s = 12; s >= 3; s--) {
		//for(int s = 12; s >= 11; s--) {
			for(int w = 1; w <= s; w++) {
				if(s%w != 0) // only if the part is possible
					continue;
				addPartOfSize(s/w, w);
			}
				
		}
		
		return g;
	}
	
	private void addPartOfSize(int h, int w) {
		System.out.println("Processing for parts of w = " + w + " and h = " + h + " for surface "+ (h*w));
		int npart = 0;
		for(int i = 0; i <= pb.height - h; i++) {
			for(int j = 0; j <= pb.width - w; j++) {
				if(isPart(i, j, h, w)) {
					//System.out.println("Adding a part");
					Part pa = new Part(i,j,w,h);
					g.addNode(pa);
					npart++;
					addEdgeAndPresence(pa, i, j, h, w);
				}
			}
		}
		
		System.out.println("Found " + npart + " parts");
	}
	
	private boolean isPart(int i, int j, int h, int w) {
		int njambon = 0;
		
		for(int p = j; p < j + w; p++) {
			for(int q = i; q < i + h; q++) {
				switch(pb.map[q][p]) {
				case Jambon:
					njambon++;
					break;
				default:
					break;
				}
			}
				
		}

		if(njambon == 3)
			return true;
		return false;
	}
	
}
