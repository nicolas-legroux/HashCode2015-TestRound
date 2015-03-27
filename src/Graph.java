import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;


public class Graph {
	
	HashMap<Part, HashSet<Part> > graph;
	
	Graph() {
		graph = new HashMap<Part, HashSet<Part> >();
	}
	
	private void addLink(Part p1, Part p2) {
		HashSet<Part> s1 = addNode(p1);
		s1.add(p2);
		//System.out.println("Neighbors : " + s1.size());
	}
	
	void addPair(Part p1, Part p2) {
		//System.out.println("Adding pair");
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
	
	void print() {
		for (Entry<Part, HashSet<Part>> e : graph.entrySet()) {
			e.getKey().print();
			System.out.print(" -> " + e.getValue().size());
			/*
			for (Part part : e.getValue()) {
				part.print();
				System.out.print(", ");
			}
			//*/
			System.out.print("\n");
		}
		System.out.println("Entries : " + graph.size());
	}

}
