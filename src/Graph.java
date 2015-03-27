import java.util.HashMap;
import java.util.HashSet;


public class Graph {
	
	HashMap<Part, HashSet<Part> > graph;
	
	Graph() {
		graph = new HashMap<Part, HashSet<Part> >();
	}
	
	private void addLink(Part p1, Part p2) {
		HashSet<Part> s1 = addNode(p1);
		s1.add(p2);
	}
	
	void addPair(Part p1, Part p2) {
		addLink(p1, p2);
		addLink(p2, p1);
	}
	
	HashSet<Part> addNode(Part p) {
		HashSet<Part> s = graph.get(p);
		if (s == null) {
			s = new HashSet<Part>();
			graph.put(p, s);
		}
		return s;
	}

}
