package guarana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
				
				// after swapping the vertices the scores change so i need to sort the lists again
				// with the lists sorted chosing the best swap is done in (almost) constant time
				// if we could find a way to keep the lists sorted when we update neighbours gain it will be faster
				
				Collections.sort(p0, new VertexGainComparator());
				Collections.sort(p1, new VertexGainComparator());
				
				// find the swap that maximizes GainA + GainB - (connected(A,B) ? 1 : 0)
				bestswap = new SwapDescriptor(p0.get(0), p1.get(0), 0, 0, g);

				if (g.areNeighbours(bestswap.getvA(), bestswap.getvB())) { //maybe there is something better					
					Vertex v; boolean found = false;					

					int h = 1;	
					
					if (p0.size()>1) {										
						while ( !found && (v = p1.get(h++)).getGain() == bestswap.getvB().getGain() ) {
							if (! g.areNeighbours(v, bestswap.getvB()) ) {
								bestswap = new SwapDescriptor(p0.get(0), v, 0, h-1, g);
								found = true;
							}
						}
					}


					if (!found && p1.size()>1) {
						h = 1;					
						while ( !found && (v = p0.get(h++)).getGain() == bestswap.getvA().getGain() ) {
							if (! g.areNeighbours(v, bestswap.getvA()) ) {
								bestswap = new SwapDescriptor(v, p0.get(1), h-1, 0, g);
								found = true;
							}
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

	private class VertexGainComparator implements Comparator<Vertex> {
		public int compare(Vertex v1, Vertex v2) {
			if (v1.getGain() > v2.getGain()) {
				return -1;
			} else if (v1.getGain() == v2.getGain()) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("G500.005");
		g.initializePartition();

		System.out.println("startig score: "+g.getPartitioning().getScore());
		
		Partition p = (new KernighanLin()).search(g);

		System.out.println("final score with KL:"+p.getScore());
	}
}
