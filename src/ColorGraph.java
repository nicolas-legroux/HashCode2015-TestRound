import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;


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
		
		for (Entry<Part, HashSet<Part>> e : g.graph.entrySet()) {
			neighbors.put(e.getKey(), e.getValue().size());
		}
		
		;
		
		return solution;
	}

}
