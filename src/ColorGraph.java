import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;


public class ColorGraph {

	HashMap<Part, Integer> neighbors;
	static ColorGraph colorizer;
	
	class CompareParts implements Comparator<Part> {

		@Override
		public int compare(Part arg0, Part arg1) {
			return colorizer.neighbors.get(arg0) - colorizer.neighbors.get(arg1);
		}
	}
	
	public ColorGraph() {
		neighbors = new HashMap<Part, Integer>();
	}
	
	Solution color(Graph g) {
		Solution solution = new Solution();

		PriorityQueue<Part> queue = new PriorityQueue<Part>(g.graph.size(), new CompareParts());
		
		for (Entry<Part, HashSet<Part>> e : g.graph.entrySet()) {
			neighbors.put(e.getKey(), e.getValue().size());
			queue.add(e.getKey());
		}
		
		while (!queue.isEmpty()) {
			Part p = queue.poll();
			HashSet<Part> n = g.graph.remove(p);
			
			for (Part part : n) {
				queue.remove(part);
				if (!g.graph.get(part).remove(p))
					System.err.println("Missing edge !");
				neighbors.put(part, neighbors.get(part) - 1);
				queue.add(part);
			}
			
			solution.parts.add(p);
		}
		
		return solution;
	}

}