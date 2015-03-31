import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Graph {
	
	HashMap<Part, HashSet<Part> > graph;
	
	Graph() {
		graph = new HashMap<Part, HashSet<Part> >();
	}
	
	HashSet<Part> addNode(Part p) {
		HashSet<Part> s = graph.get(p);
		if (s == null) {
			s = new HashSet<Part>();
			graph.put(p, s);
		}
		return s;
	}
	
	private void addDirectedEdge(Part p1, Part p2) {
		HashSet<Part> s1 = addNode(p1);
		s1.add(p2);		
	}
	
	void addUndirectedEdge(Part p1, Part p2) {		
		addDirectedEdge(p1, p2);
		addDirectedEdge(p2, p1);
	}
	
	void print() {
		int numberOfEdges = 0;
		for (Entry<Part, HashSet<Part>> e : graph.entrySet()) {
			e.getKey().print();
			System.out.print(" -> " + e.getValue().size());
			System.out.print("\n");
			numberOfEdges += e.getValue().size();
		}
		System.out.println("Graph statistics : ");
		System.out.println("Number of nodes : " + graph.size());
		System.out.println("Number of edges : " + numberOfEdges);
	}
}