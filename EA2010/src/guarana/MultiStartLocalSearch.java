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
		
		//System.out.println("Best found: "+best.getScore()+" elapsed: "+);
		
		return new SearchResult(alloptima, best, (end-start));		
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
		
		//System.out.println("Best found: "+best.getScore());
		
		return new SearchResult(alloptima, best, 0);
	}
	

	
	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("G500.005");
		
		
		SearchResult s = search_fixedNOptima(new KernighanLin(), 1, g);
		System.out.println("best: "+s.best.getScore()+" elapsed: "+s.elapsed);
		
		//search_fixedTime(new KernighanLin(), 10000, g);		
	}
}
