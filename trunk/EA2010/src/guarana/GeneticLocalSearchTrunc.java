package guarana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneticLocalSearchTrunc {

	static Random rand = new Random();
	
	/**
	 * 
	 * @param ls local search algorithm
	 * @param p population
	 * @param t times
	 * @param g graph
	 * @return
	 */
	public static Partition search_fixedPopulation(LocalSearch ls, int p, int t, Graph g) {
		List<Partition> partitionList = new ArrayList<Partition>();		

		//generate initial population
		for (int i=0; i<p; i++) {
			Partition part = new Partition(g.getVertices().size());
			System.out.print(part.getScore() + "/");
			//calculate score of partition
			g.setPartition(part);
			System.out.print(part.getScore() + "/");
			part = ls.search(g);
			System.out.println(part.getScore());
			partitionList.add(part);	
		}
		
		Collections.sort(partitionList);
		
		for (int i=0; i<t; i++) {
			Partition parent1, parent2;
			//Collections.sort(partitionList);
						
			//Truncation selection
			float proportion = 2;
			int trunc = (int) (p/proportion);
			//Collections.sort(partitionList);
			
			//for (int i1=0; i1<proportion; i1++ ) {
				int index = rand.nextInt(trunc-1);
				parent1 = partitionList.get(index);
				int index2;
				do {
					index2 = rand.nextInt(trunc-1);
				} while(index2 == index);
				parent2 = partitionList.get(index2);
				// offspring
				
				int[] cr = crossover(parent1, parent2);
				//mutate(cr);
				Partition child = new Partition(cr);
				g.setPartition(child);			
				child = ls.search(g);
				
				System.out.println(parent1.getScore() +"+"+parent2.getScore()+"->"+child.getScore());
				// insert in the population
				
				if (child.compareTo(partitionList.get(p-1)) == -1 ) {
					
					for (int j = 0; j<p; j++) {
						Partition p1 = partitionList.get(j);
						
						if (p1.compareTo(child) == 1 || (p1.compareTo(child) == 0 && !p1.equals(child))) {
							System.out.println("CHANGE");
							partitionList.add(j, child);
							break;
						}					
					}
					
					partitionList.remove(p);
				}
			//}
		}
		
		//Collections.sort(partitionList);
		System.out.println(partitionList.get(0).getScore());
		for (Partition potson: partitionList)
			System.out.println(potson.getScore());
		
		return null;		
	}
	
	private static int[] crossover(Partition p1, Partition p2) {
		
		if (p1.getSize() != p2.getSize()) System.exit(-1);
		//will invert one partition if needed
		Util.hammingDistance(p1, p2);
		
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
	
	/*
	private static void mutate(int[] partition) {
		//Probability of mutation in percentage
		int prob = 1;
		int counter0 = 0, counter1 = 0;
		for (int i=0; i<partition.length; i++) {
			if (rand.nextInt(100) > 99-prob) {
				if (partition[i] == 0) {
					partition[i] = 1;
					counter1++;
				}
				else {
					partition[i] = 0;
					counter0++;
				}
			}
		}
		System.out.println(counter0+"/"+counter1);
		while (counter0 != counter1) {
			int i;
			if (counter0 > counter1) {
				do {
					i = rand.nextInt(partition.length);
				} while(partition[i] == 0);
				partition[i] = 1;
				counter1++;
			}
			else {
				do {
					i = rand.nextInt(partition.length);
				} while(partition[i] == 1);
				partition[i] = 0;
				counter0++;
			}
		}
		System.out.println(counter0+"/"+counter1);
		counter0=0;counter1=0;
		for (int i=0; i<partition.length; i++) {
			if (partition[i]==0) counter0++;
			else counter1++;
		}
		System.out.println(counter0+"/"+counter1);
	} */
	
	public static void main(String[] args) {
		Graph g = Util.makeGraphFromFile("G500.005");

		search_fixedPopulation(new FiducciaMattheyses(), 150, 100, g);
	}
}
