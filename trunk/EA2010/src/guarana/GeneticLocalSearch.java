package guarana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneticLocalSearch {

	static Random rand = new Random();
	public static int RANDOM = 0;
	public static int TOURNAMENT = 1;
	public static int TRUNCATION = 2;
	
	/**
	 * 
	 * @param ls local search algorithm
	 * @param p population
	 * @param t times
	 * @param g graph
	 * @return
	 */
	public static SearchResult search_fixedPopulation(LocalSearch ls, int p, int t, int sel, Graph g) {
		List<Partition> partitionList = new ArrayList<Partition>();
		List<Partition> alloptima = new ArrayList<Partition>();

		long time = System.currentTimeMillis();
		
		//generate initial population
		for (int i=0; i<p; i++) {
			Partition part = new Partition(g.getVertices().size());
			//System.out.print(part.getScore() + "/");
			//calculate score of partition
			g.setPartition(part);
			//System.out.print(part.getScore() + "/");
			part = ls.search(g);
			//System.out.println(part.getScore());
			partitionList.add(part);
		}
		alloptima.addAll(partitionList);
		Collections.sort(partitionList);
		
		
		for (int i=0; i<t; i++) {
			Partition parent1, parent2;			
			
			//Tournament selection
			if (sel == 1) {
				int tournamentSize = (int) (0.4*p);
				List<Partition> tournament = new ArrayList<Partition>();
				ArrayList<Partition> cl = (ArrayList<Partition>) ((ArrayList<Partition>) partitionList).clone();
				int counter = 1;
				while (counter < tournamentSize) {
					int r = rand.nextInt(cl.size());
					tournament.add(cl.get(r));
					cl.remove(r);
					counter++;
				}
				Collections.sort(tournament);
				int index1 = expdistchoose(-1, tournamentSize-1);
				parent1 = tournament.get(index1);
				parent2 = tournament.get(expdistchoose(index1, tournamentSize-1));			
			} //Tournament end
			
			//Truncation selection
			else if (sel == 2) {
				//Truncation selection
				float proportion = 2;
				int trunc = (int) (p/proportion);
				int index = rand.nextInt(trunc-1);
				parent1 = partitionList.get(index);
				int index2;
				do {
					index2 = rand.nextInt(trunc-1);
				} while(index2 == index);
				parent2 = partitionList.get(index2);
			}//Truncation end
			
			//Random selection
			else {				
				int p1_index = rand.nextInt(p-1), p2_index;			
				parent1 = partitionList.get(p1_index);				
				do {
					p2_index = rand.nextInt(p-1);
				} while (p2_index == p1_index);
				parent2 = partitionList.get(p2_index);			
			} //Random end
			
			/*// random choice with exponential distributions						
			int p1_index = expdistchoose(-1, p);
			int p2_index = expdistchoose(p1_index, p);			
			parent1 = partitionList.get(p1_index);
			parent2 = partitionList.get(p2_index);			
			*/// Random 2
			
			// offspring
			
			Partition child = new Partition(crossover(parent1, parent2));
			g.setPartition(child);
			
			child = ls.search(g);
			alloptima.add(child);
			
			// insert in the population
			
			if (child.compareTo(partitionList.get(p-1)) == -1 ) {
				
				for (int j = 0; j<p; j++) {
					Partition p1 = partitionList.get(j);
					
					if (p1.compareTo(child) == 1 || (p1.compareTo(child) == 0 && !p1.equals(child))) {
						partitionList.add(j, child);
						break;
					}					
				}
				
				partitionList.remove(p);
			}
		}
		
		Collections.sort(partitionList);
		System.out.println(partitionList.get(0).getScore());
		time = System.currentTimeMillis() - time;
		return new SearchResult(alloptima, partitionList.get(0), time);		
	}
	
	// chose an index from a random exponential distribution such that it's different from j and < p
	private static int expdistchoose(int j, int p) {
		int r; 
	
		do {
			float f = rand.nextFloat();
			r = (int) (Math.round( -Math.log(f) / 0.1));			
		} while (r >= p || r == j);
		
		return r;
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
	
	public static void main(String[] args) {
		int population = 50;
		int times = 1000;
		
		Graph g = Util.makeGraphFromFile("U500.05");
		SearchResult s = search_fixedPopulation(new FiducciaMattheyses(), population, times, RANDOM, g);
		System.out.println("GLS-FM Pop= "+population
				+" Times= "+times+" LO distances ----------------");
		Util.outHammingDistandScores(s);
		System.out.println("\nBest Result Score: " + s.best.getScore());
		System.out.println("Time needed: " + s.elapsed);
	}
}
