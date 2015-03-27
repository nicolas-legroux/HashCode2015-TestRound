import java.util.HashMap;
import java.util.HashSet;


public class Graph {
	
	HashMap<Part, HashSet<Part> > graph;
	
	Graph() {
		graph = new HashMap<Part, HashSet<Part> >();
	}
	
	private void addLink(Part p1, Part p2) {
		HashSet<Part> s1 = graph.get(p1);
		if (s1 == null) {
			s1 = new HashSet<Part>();
			graph.put(p1, s1);
		}
		s1.add(p2);
	}
	
	void addPair(Part p1, Part p2) {
		addLink(p1, p2);
		addLink(p2, p1);
	}

}
