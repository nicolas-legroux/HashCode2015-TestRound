import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;


public class ColorGraph {
	
	class CompareParts implements Comparator<Part> {

		@Override
		public int compare(Part arg0, Part arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
		;
	}
	
	static Solution color(Graph g) {
		Solution solution = new Solution();
		
		HashMap<Part, Integer> neighbors = new HashMap<Part, Integer>();
		for (Entry<Part, HashSet<Part>> e : g.graph.entrySet()) {
			neighbors.put(e.getKey(), e.getValue().size());
		}
		
		;
		
		return solution;
	}

}
