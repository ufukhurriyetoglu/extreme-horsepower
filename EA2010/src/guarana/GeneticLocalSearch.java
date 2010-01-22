package guarana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneticLocalSearch {

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
		
		//int tournamentSize = (int) (0.4*p);
		
		for (int i=0; i<t; i++) {
			//Collections.sort(partitionList);
			
			/* 
			
			//Tournament selection
			List<Partition> tournament = new ArrayList<Partition>();
			ArrayList<Partition> cl = (ArrayList<Partition>) ((ArrayList<Partition>) partitionList).clone();
			int counter = 1;
			while (counter < tournamentSize) {
				int r = rand.nextInt(p-counter);
				tournament.add(cl.get(r));
				cl.remove(r);
				counter++;
			}
			Collections.sort(tournament);
			Partition parent1 = tournament.get(0);
			Partition parent2 = tournament.get(1);
			
			*/ //Tournament
			
			/*
			 
			// Random parent choice without selection
			Partition parent1, parent2;
			
			
			int p1_index = rand.nextInt(p-1), p2_index;			
			parent1 = partitionList.get(p1_index);
			
			do {
				p2_index = rand.nextInt(p-1);
			} while (p2_index == p1_index);
			
			parent2 = partitionList.get(p2_index);			
			*/ //Random
			
			// random choice with exponential distributions
			Partition parent1, parent2;
						
			int p1_index = expdistchoose(-1, p);
			int p2_index = expdistchoose(p1_index, p);
			
			parent1 = partitionList.get(p1_index);
			parent2 = partitionList.get(p2_index);			
			
			// offspring
			
			Partition child = new Partition(crossover(parent1, parent2));
			g.setPartition(child);
			
			child = ls.search(g);
			
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
		
		
		return null;		
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
		/*Partition p1 = new Partition(500);
		Partition p2 = new Partition(500);
		int distance = Util.hammingDistance(p1, p2);
		System.out.println(distance);
		int[] p = crossover(p1,p2);
		Partition pp1 = new Partition(500);
		pp1.setPartition(p);
		int distance2 = Util.hammingDistance(p1, pp1);
		int distance3 = Util.hammingDistance(p2, pp1);
		System.out.println(distance2);
		System.out.println(distance3);
		pp1.setPartition(crossover(p1,pp1));
		System.out.println(Util.hammingDistance(p1, pp1));
		int c = 0;
		for (int i=0; i<p1.getSize(); i++) {
			//System.out.println(pp1[i] + "-" + pp2[i] + "-" + p[i]);
			if (p[i] == 1) c++;
		}
		//System.out.println(c);
		*/
		Graph g = Util.makeGraphFromFile("U500.05");

		search_fixedPopulation(new FiducciaMattheyses(), 50, 10000, g);
	}
}
