import java.io.IOException;
import java.util.Set;

public class Cluster_search {
	Problem pb;
	int[][] clusters;
	Set<Cluster> cluster_set;
	
	
	public Cluster_search() throws IOException{
		
		try {
			pb.load("data/test_round.in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clusters=new int[pb.height][pb.width];
		int cluster_compt=0;
		int ham_compt=0;
		
		for (int i=0;i<pb.height;i++){
			for (int j=0;j<pb.width;j++){
				if(clusters[i][j]==0 && pb.pizza[i][j].equals(Problem.PizzaTopping.HAM)){
					trouverCluster(i,j, cluster_compt);
				}
					
				}
			}
		}
		
	public void trouverCluster(int a, int b, int compt){
		int ham_compt=0;
		Cluster c=new Cluster(a, b, a,b,a, b);
		for (int i=0;i<6;i++){
			for (int j=0;j<6;j++){
				if (ham_compt<3){
					if(pb.pizza[a+i][b+j].equals(Problem.PizzaTopping.HAM)){
						ham_compt++;
						clusters[i][j]=compt;
						if (i<c.r1 && ham_compt==2){
							c.r2=c.r1;
							c.c2=c.c1;
							
						}
						
						if (i<c.r1 && ham_compt==3){
							c.r3=c.r1;
							c.c3=c.c1;
						
						}
				
						
						c.r1=i;
						c.r2=j;
						
					}
				}
			}
			 
		}
		
	}
		

}
