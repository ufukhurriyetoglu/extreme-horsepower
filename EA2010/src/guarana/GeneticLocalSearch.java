package guarana;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GeneticLocalSearch {

	public static Partition search_fixedPopulation(LocalSearch ls, int p, Graph g) {
		List<Partition> partitionList = new ArrayList<Partition>();		

		//generate initial population
		for (int i=0; i<p; i++) {
			partitionList.add(new Partition(g.getVertices().size()));			
		}
		
		
		return null;		
	}
	
	private static int[] crossover(Partition p1, Partition p2) {
		//int distance = Util.hammingDistance(p1, p2);
		if (p1.getSize() != p2.getSize()) System.exit(-1);
		
		Random rand = new Random();
		int[] part1 = p1.getPartition();
		int[] part2 = p2.getPartition();
		int[] ret = new int[p1.getSize()];
		boolean[] fixed = new boolean[p1.getSize()];
		
		int counter = 0;
		for (int i=0; i<p1.getSize(); i++) {
			if (part1[i] == part2[i]) { 
				ret[i] = part1[i];
				fixed[i] = true;
				if (part1[i] == 1) counter++;
			}
		}
		while(counter < p1.getSize()/2) {
			int i = rand.nextInt(p1.getSize());
			if (!fixed[i] && ret[i] == 0) {
				ret[i] = 1;
				counter++;
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		Partition p1 = new Partition(500);
		Partition p2 = new Partition(500);
		int distance = Util.hammingDistance(p1, p2);
		System.out.println(distance);
		int[] p = crossover(p1,p2);
		int[] pp1 = p1.getPartition();
		int[] pp2 = p2.getPartition();
		
		int c = 0;
		for (int i=0; i<p1.getSize(); i++) {
			System.out.println(pp1[i] + "-" + pp2[i] + "-" + p[i]);
			if (p[i] == 1) c++;
		}
		System.out.println(c);
	}
}
