import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;


public class ColorGraph {

	HashMap<Part, Integer> neighbors;
	
	class CompareParts implements Comparator<Part> {
		
		ColorGraph colorizer;
		
		CompareParts(ColorGraph colorizer) {
			this.colorizer = colorizer;
		}

		@Override
		public int compare(Part arg0, Part arg1) {
			return colorizer.neighbors.get(arg0) - colorizer.neighbors.get(arg1);
		}
	}
	
	static Solution color(Graph g) {
		ColorGraph colorizer = new ColorGraph();
		return colorizer.doColor(g);
	}
	
	private ColorGraph() {
		neighbors = new HashMap<Part, Integer>();
	}
	
	private Solution doColor(Graph g) {
		Solution solution = new Solution();

		//HashSet<Part> queue = new HashSet<Part>();
		
		for (Entry<Part, HashSet<Part>> e : g.graph.entrySet()) {
			neighbors.put(e.getKey(), e.getValue().size());
			//queue.add(e.getKey());
		}
		
		int count = g.graph.size();
		
		while (!neighbors.isEmpty()) {
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
			solution.parts.add(p);
			
			count -= 1 + neighbors.get(p);
			HashSet<Part> n = g.graph.get(p);
			
			//*
			neighbors.remove(p);
			for (Part part : n) {
				neighbors.remove(part);
			}
			//*/

			/*
			for (Part part : n) {
				queue.remove(part);
			}
			//*/
			
			for (Part part : n) {
				for (Part _part : g.graph.get(part)) {
					if (neighbors.containsKey(_part)) {
						//if (!g.graph.get(_part).remove(part))
							//System.err.println("Missing edge !");
						neighbors.put(_part, neighbors.get(_part) - 1);
						//queue.add(_part);
					}
				}
			}
			
			System.out.println("Remaining nodes : " + count);
		}
		
		return solution;
	}

}
