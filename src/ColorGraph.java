import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class ColorGraph {

	HashMap<Part, Integer> neighbors;
	Problem pb;
	
	static Solution color(Graph g, Problem pb) {
		ColorGraph colorizer = new ColorGraph(pb);
		return colorizer.doColor(g);
	}
	
	private ColorGraph(Problem pb) {
		this.pb = pb;
		neighbors = new HashMap<Part, Integer>();
	}
	
	private Solution doColor(Graph g) {
		
		Solution solution = new Solution(pb);
		
		for (Entry<Part, HashSet<Part>> e : g.graph.entrySet()) {
			neighbors.put(e.getKey(), e.getValue().size());			
		}
		
		int count = g.graph.size();
		
		while (!neighbors.isEmpty()) {
			
			//Part1 is "smaller" than Part2 if it has a larger size. If they have the same size, we take the part with the least neighbors in the graph 
			Entry<Part, Integer> min = Collections.min(neighbors.entrySet(), new Comparator<Entry<Part, Integer>>() {
			    public int compare(Entry<Part, Integer> entry1, Entry<Part, Integer> entry2) {
			    	int s1 = (entry1.getKey().right + 1 - entry1.getKey().left)
			    			* (entry1.getKey().bottom + 1 - entry1.getKey().top);
			    	int s2 = (entry2.getKey().right + 1 - entry2.getKey().left)
			    			* (entry2.getKey().bottom + 1 - entry2.getKey().top);
			    	if (s1 == s2)
			    		return entry1.getValue().compareTo(entry2.getValue());
			    	else
			    		return s2 - s1; 
			    }
			    
			});
			
			Part p = min.getKey();
			//Add the part to the solution
			solution.parts.add(p);
			
			count -= 1 + neighbors.get(p);
			HashSet<Part> n = g.graph.get(p);
			
			//*
			neighbors.remove(p);
			for (Part part : n) {
				neighbors.remove(part);
			}
			
			for (Part part : n) {
				for (Part _part : g.graph.get(part)) {
					if (neighbors.containsKey(_part)) {						
						neighbors.put(_part, neighbors.get(_part) - 1);						
					}
				}
			}
			
			//System.out.println("Remaining nodes : " + count);
		}
		
		return solution;
	}
}