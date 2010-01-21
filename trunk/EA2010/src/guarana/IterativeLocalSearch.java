package guarana;
import java.util.ArrayList;
import java.util.Random;

public class IterativeLocalSearch {

	public static Partition search_fixedNOptima(LocalSearch s, int n, int nswaps, Graph g) {
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
		}

		end = System.currentTimeMillis();

		System.out.println("Best found: "+old.getScore()+" elapsed: "+(end-start)+"ms");

		return old;	
	}



	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("U500.05");

		search_fixedNOptima(new FiducciaMattheyses(), 10000, 80, g);		
	}
}
