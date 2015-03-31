import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

public class ColorGraph {

	HashMap<Part, Integer> neighbors;	
	Problem pb;
	
	private Solution bestSolution;
	
	public ColorGraph(Problem pb) {
		this.pb = pb;
		this.neighbors = new HashMap<Part, Integer>();		
		this.bestSolution = new Solution(pb);		
	}
	
	public Solution solve(Solution baseSolution, int maxHam) {	
		
		GraphBuilder gb = new GraphBuilder(pb, baseSolution, maxHam);
		long startTimeGraphBuilding = System.currentTimeMillis();
		Graph g = gb.build();
		long endTimeGraphBuilding = System.currentTimeMillis();
		System.out.println("Time to build the graph : " + (endTimeGraphBuilding-startTimeGraphBuilding) + "ms");
		g.print();	
		
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
			baseSolution.addPart(p);
			
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
		
		System.out.println("Score of the solution : " + baseSolution.getScore());
		
		return baseSolution;
	}
	
	public void solveWithRandomRemovals(double prob, int max_iterations){
		
		//Start initial solver
		bestSolution = solve(bestSolution, 3);
		
		int bestEverSolution = bestSolution.getScore();
		int countNoProgression = 0;
		
		double probChange = prob;
		
		int resetCounter = 0;
		
		int i=1;
		
		Random random = new Random(25);
		
		//Iterate
		while(true){
			
			//Copy the best solution
			Solution baseSolution = new Solution(bestSolution);
			HashSet<Part> parts = new HashSet<Part>();			
			
			for(Part p : baseSolution.getParts()){
				if(random.nextDouble()<=probChange){
					parts.add(p);
				}
			}
			
			baseSolution.resetParts(parts);
			
			System.out.println("Iteration #" + (i));
			i++;
			
			if(bestSolution.getScore()>9600){
				baseSolution = solve(baseSolution, 8);
				System.out.println("Solving with more than 3 Hams / piece");
			}
			
			else{
				baseSolution = solve(baseSolution, 3);
			}			
			
			if(baseSolution.getScore()>=bestSolution.getScore()){
				System.out.println("Score : " + bestSolution.getScore() + " -> " + baseSolution.getScore());
				if(baseSolution.getScore()>bestSolution.getScore()){
					countNoProgression = 0;
					System.out.println("Writing to file...");
					try {
						baseSolution.save("results" + resetCounter + ".txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					countNoProgression++;
					System.out.println("No progression for the " + countNoProgression + "th time");
					if(countNoProgression>=100){
						countNoProgression = 0;
						if (bestSolution.getScore()>bestEverSolution){
							bestEverSolution = bestSolution.getScore();
						}
						
						//Reset
						baseSolution = new Solution(pb);
						resetCounter++;					
					}
				}
				
				bestSolution = baseSolution;
				probChange = prob;				
			}	
			else{
				System.out.println("Did not improve best score which is : " + bestSolution.getScore());
				probChange = Math.min((probChange + 0.02), 1.0);
				
			}
			
			System.out.println("Resetted the solution " + resetCounter + " times");
			System.out.println("Best ever solution is " + bestEverSolution);
			System.out.println("For the next iteration, p=" + probChange);
			System.out.println("\n");
		}		
	}
	
	Solution getSolution(){
		return bestSolution;
	}
}