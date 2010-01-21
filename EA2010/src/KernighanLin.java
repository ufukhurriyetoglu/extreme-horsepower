import java.util.ArrayList;

public class KernighanLin implements LocalSearch {	

	public Partition search(Graph g) {

		Partition bestknown = null, overallbest = null;

		boolean improovement;
		
		do  {			
			improovement = false;
			
			if (overallbest != null) {
				g.setPartition(overallbest);
			}
			
			SwapDescriptor bestswap = null;

			// make a copy of the two current partition lists
			// java cast rules can suck my dick
			ArrayList<Vertex> p0 = (ArrayList<Vertex>) ((ArrayList<Vertex>) g.getPartition0()).clone();
			ArrayList<Vertex> p1 = (ArrayList<Vertex>) ((ArrayList<Vertex>) g.getPartition1()).clone();

			// for every vertices pair

			do {
				// find the swap that maximizes GainA + GainB - (connected(A,B) ? 1 : 0)
				bestswap = null;

				for (int iVa = 0; iVa < p0.size(); iVa++) {
					for (int iVb = 0; iVb < p1.size(); iVb++) {

						SwapDescriptor th_swap = new SwapDescriptor(p0.get(iVa), p1.get(iVb), iVa, iVb, g);

						if (bestswap == null || th_swap.compareTo(bestswap) == 1) {
							bestswap = th_swap;							
						}					
					}			
				}			

				// do the swap
				g.switchVertex(bestswap.getvA()); g.switchVertex(bestswap.getvB());
				//System.out.println("swap "+bestswap.getvA()+" "+bestswap.getvB());

				// easily remove the swapped vertices from the local copies of the lists
				p0.remove(bestswap.getvAindex()); p1.remove(bestswap.getvBindex());
				
				// keep track of the best known partition
				if (bestknown == null || g.getPartitioning().getScore() < bestknown.getScore()) {					
					try {
						bestknown = (Partition) g.getPartitioning().clone();						
					} catch (CloneNotSupportedException e) {}
				}

			} while (p0.size() > 0);
			
			if (overallbest == null || overallbest.getScore() > bestknown.getScore()) {
				improovement = true;
				
				try {
					overallbest = (Partition) bestknown.clone();
				} catch (CloneNotSupportedException e) {}
			}

		} while (improovement);

		return overallbest;
	}

	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("G500.005");
		g.initializePartition();

		System.out.println("startig score: "+g.getPartitioning().getScore());
		
		Partition p = (new KernighanLin()).search(g);

		System.out.println("final score with FM:"+p.getScore());
	}
}
