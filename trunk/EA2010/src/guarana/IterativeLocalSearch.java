package guarana;

import java.util.ArrayList;

public class IterativeLocalSearch {

	public static SearchResult search_fixedNOptima(LocalSearch s, int n, int nswaps, Graph g) {
		ArrayList<Partition> alloptima = new ArrayList<Partition>();		
		
		long start, end;		
		Partition old = null, cur;

		g.initializePartition();

		start = System.currentTimeMillis();	

		for (int k = 0; k < n; k++) {

			GraphUtil.moveALittleBit(g, nswaps);

			cur = s.search(g);

			try {
				if (old == null || cur.getScore() < old.getScore()) {			
					old = (Partition) cur.clone();
				} else {
					g.setPartition((Partition) old.clone());
				}
			} catch (CloneNotSupportedException e) {}
			
			alloptima.add(cur);
		}

		end = System.currentTimeMillis();

		System.out.println("Best found: "+old.getScore()+" elapsed: "+(end-start)+"ms");

		return new SearchResult(alloptima, old);	
	}



	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("U500.05");

		// this is the code that produces the output needed for point 1.
		
		// step 1 - find 1000 local optima and plot the hamming distance between them and the best found	
		
		System.out.println("STEP 1 -------------------------------- ");
		
		SearchResult s = search_fixedNOptima(new FiducciaMattheyses(), 10000, 50, g);
		
		System.out.println("FM 1000 LO distances ------------------ ");
		Util.outHammingDistandScores(s);				
	}
}
