import com.sun.javadoc.Type;


public class Cluster_search {
	Problem pb;
	int[][] clusters;
	Set<Cluster> cluster_set;
	
	
	public Cluster_search(){
		
		pb.load("data/test_round.in");
		clusters=new int[pb.height][pb.width];
		int cluster_compt=0;
		int ham_compt=0;
		
		for (int i=0;i<pb.height;i++){
			for (int j=0;j<pb.width;j++){
				if(cluster[i][j]==0 && pb.map[i][j].equals(Type.Jambon)){
					trouverCluster(i,j);
				}
					
				}
			}
		}
		
	public void trouverCluster(int i, int j){
		
	}
		

}
