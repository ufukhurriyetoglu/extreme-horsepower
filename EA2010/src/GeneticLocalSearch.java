import java.util.ArrayList;
import java.util.List;


public class GeneticLocalSearch {

	public static Partition search_fixedPopulation(LocalSearch ls, int p, Graph g) {
		List<Partition> partitionList = new ArrayList<Partition>();		

		//generate initial population
		for (int i=0; i<p; i++) {
			partitionList.add(new Partition(g.getVertices().size()));			
		}
		
		
		return null;		
	}
	
	private static Partition crossover(Partition p1, Partition p2) {
		int distance = Util.hammingDistance(p1, p2);
		System.out.println(distance);
		return null;
	}
	
	public static void main(String[] args) {
		Partition p1 = new Partition(500);
		Partition p2 = new Partition(500);
		int distance = Util.hammingDistance(p1, p2);
		System.out.println(distance);
	}
}
