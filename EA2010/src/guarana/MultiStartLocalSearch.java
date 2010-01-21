package guarana;
import java.util.ArrayList;

public class MultiStartLocalSearch {
	
	public static SearchResult search_fixedNOptima(LocalSearch s, int n, Graph g) {
		
		ArrayList<Partition> alloptima = new ArrayList<Partition>();
		
		long start, end;		
		Partition best = null, cur;
		
		start = System.currentTimeMillis();		
		
		for (int k = 0; k < n; k++) {
			g.initializePartition();
			
			cur = s.search(g);
						
			if (best == null || cur.getScore() < best.getScore()) {
				
				// not sure the clone is needed, for safety
				try {
					best =(Partition) cur.clone();
				} catch (CloneNotSupportedException e) {}
			}
			
			alloptima.add(cur); // it should not need cloning since every time the graph is initialized again 
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("Best found: "+best.getScore()+" elapsed: "+(end-start));
		
		return new SearchResult(alloptima, best);		
	}
	
	public static SearchResult search_fixedTime(LocalSearch s, int t, Graph g) {
		
		ArrayList<Partition> alloptima = new ArrayList<Partition>();
		
		long start;		
		Partition best = null, cur;
		
		start = System.currentTimeMillis();
		
		do {
			g.initializePartition();
			
			cur = s.search(g);
			
			if (best == null || cur.getScore() < best.getScore()) {
				
				// not sure the clone is needed, for safety
				try {
					best =(Partition) cur.clone();
				} catch (CloneNotSupportedException e) {}
			}
			
			alloptima.add(cur);
			
		} while (System.currentTimeMillis() < start + t);		
		
		System.out.println("Best found: "+best.getScore());
		
		return new SearchResult(alloptima, best);
	}
	

	
	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("G500.005");
		
		// this is the code that produces the output needed for point 1.
		
		// step 1 - find 1000 local optima and plot the hamming distance between them and the best found	
		
		System.out.println("STEP 1 -------------------------------- ");
		
		SearchResult s = search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
		
		System.out.println("FM 1000 LO distances ------------------ ");
		outHammingDistandScores(s);
		
		s = search_fixedNOptima(new KernighanLin(), 1000, g);
		
		System.out.println("KL 1000 LO distances ------------------ ");
		outHammingDistandScores(s);
		
		// step 2 - run 1000 times MSLS fixed Noptima (1000) with FM and KL output the best scores found
		System.out.println("STEP 2 -------------------------------- ");
		
		System.out.println("FM     -------------------------------- ");
		for (int k = 0; k < 1000; k++) {
			s = search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
			System.out.println(s.best.getScore());
		}
		
		System.out.println("KL     -------------------------------- ");
		for (int k = 0; k < 1000; k++) {
			s = search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
			System.out.println(s.best.getScore());
		}
		
		//search_fixedTime(new KernighanLin(), 10000, g);		
	}
	
	private static void outHammingDistandScores(SearchResult s) {
		for (Partition th : s.alloptima) {
			System.out.println (Util.hammingDistance(s.best, th)+", "+th.getScore());
		}
	}
	
	
}
