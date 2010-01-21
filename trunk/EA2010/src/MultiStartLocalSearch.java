
public class MultiStartLocalSearch {
	
	public static Partition search_fixedNOptima(LocalSearch s, int n, Graph g) {
		
		long start, end;		
		Partition best = null, cur;
		
		start = System.currentTimeMillis();		
		
		for (int k = 0; k < n; k++) {
			g.initializePartition();
			
			cur = s.search(g);
			System.out.println(cur.getScore());
			
			if (best == null || cur.getScore() < best.getScore()) {
				
				// not sure the clone is needed, for safety
				try {
					best =(Partition) cur.clone();
				} catch (CloneNotSupportedException e) {}
			}						
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("Best found: "+best.getScore()+" elapsed: "+(end-start));
		
		return best;		
	}
	
	public static Partition search_fixedTime(LocalSearch s, int t, Graph g) {
		
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
		} while (System.currentTimeMillis() < start + t);		
		
		System.out.println("Best found: "+best.getScore());
		
		return best;
	}
	
	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("G500.005");
		
		search_fixedTime(new FiducciaMattheyses(), 10000, g);
		search_fixedTime(new KernighanLin(), 10000, g);
		
	}
}
