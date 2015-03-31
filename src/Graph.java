import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Graph {
	
	HashMap<Part, HashSet<Part> > graph;
	
	Graph() {
		graph = new HashMap< Part, HashSet<Part> >();
	}
	
	void addPart(Part p){		
		if(graph.get(p)==null){			
			graph.put(p, new HashSet<Part>());	
		}
		else{
			System.out.println("The part is already in the graph");
			p.print();
		}
	}
	
	HashSet<Part> getNeighbors(Part p) {
		return graph.get(p);
	}
	
	private void addDirectedEdge(Part p1, Part p2) {
		HashSet<Part> s1 = getNeighbors(p1);
		s1.add(p2);		
	}
	
	public void addUndirectedEdge(Part p1, Part p2) {		
		addDirectedEdge(p1, p2);
		addDirectedEdge(p2, p1);
	}
	
	public void print() {
		
		int numberOfEdges = 0;
		for (Entry<Part, HashSet<Part>> e : graph.entrySet()) {
			//e.getKey().print();
			//System.out.print(" -> " + e.getValue().size());
			//System.out.print("\n");
			numberOfEdges += e.getValue().size();
		}
		
		//System.out.println("Graph statistics : ");
		System.out.println("Number of nodes : " + graph.size());
		System.out.println("Number of edges : " + numberOfEdges);
	}
}