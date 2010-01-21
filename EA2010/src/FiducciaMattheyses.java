
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FiducciaMattheyses implements LocalSearch {

	public Partition search(Graph g) {
		int maxdegree = g.getMaxDegree();

		boolean improovement; Partition overallbest = null;
		
		do  {			
			improovement = false;

			if (overallbest != null) {
				g.setPartition(overallbest);
			}

			ArrayList<ArrayList<LinkedList<Vertex>>> sets = buildMySet(maxdegree);
			int lastNonEmpty[] = new int[2];

			List<Vertex> vertexes = g.getVertices();

			boolean odd = false;

			//divide the vertices in the various list divided by swapping gain
			for (Vertex th : vertexes) {
				int gain = th.getGain()+g.getMaxDegree(); int part = g.getPartitioning().getPartition(th);

				sets.get(part).get(gain).add(th);
				lastNonEmpty[part] = Math.max(lastNonEmpty[part], gain);
			}		

			Partition bestknown = null;

			int tochoosefrom = 0;

			// for every vertex
			for (int k = 0; k<vertexes.size(); k++) {

				// determine which set the vertex has to be choosen in
				if (odd == false) {
					tochoosefrom = lastNonEmpty[0]>lastNonEmpty[1] ? 0 : 1;

					// legal partitions! check if the current one is better
					if (bestknown == null || g.getPartitioning().getScore() < bestknown.getScore()) {					
						try {
							bestknown = (Partition) g.getPartitioning().clone();
						} catch (CloneNotSupportedException e) { // java rubbish
							e.printStackTrace();
						}
					}			
				}

				// remove the vertex from his list
				ArrayList<LinkedList<Vertex>> curset = sets.get(tochoosefrom);
				Vertex removed = curset.get(lastNonEmpty[tochoosefrom]).removeFirst();

				// get the connected vertices and their gains before the swap
				List<Vertex> connv = g.getNeighbors(removed);
				int oldgains[] = getConnectedVerticesGain(connv); int h = 0;

				// move the vertex so his neighbours get updated
				g.switchVertex(removed);

				// update the position of the neighbours in my lists
				for (Vertex th: connv) {
					int whichlist = oldgains[h++];

					int th_part = g.getPartitioning().getPartition(th);

					LinkedList<Vertex> l = sets.get(th_part).get(whichlist+g.getMaxDegree());

					Iterator<Vertex> it = l.iterator();
					while (it.hasNext()) {
						Vertex thin = it.next();

						if (thin.equals(th)) {
							it.remove();
							sets.get(th_part).get(thin.getGain()+g.getMaxDegree()).add(thin);
						}					
					}				
				}

				// recompute the lastnonempty, maybe could be done faster in the previous step

				for (int i = 0; i < 2; i++) {
					ArrayList<LinkedList<Vertex>> th = sets.get(i); int j;
					for (j = th.size()-1; th.get(j).size() == 0 && j>0; j--) {}
					lastNonEmpty[i]=j;
				}			

				odd = !odd;
				tochoosefrom = 1 - tochoosefrom; // if it becomes odd i have to chose from the other set		
			}

			if (overallbest == null || overallbest.getScore() > bestknown.getScore()) {
				improovement = true;

				try {
					overallbest = (Partition) bestknown.clone();
				} catch (CloneNotSupportedException e) {}
			}

		} while (improovement);

		return overallbest;
	}

	private static ArrayList<ArrayList<LinkedList<Vertex>>> buildMySet(int maxdegree) {
		ArrayList<ArrayList<LinkedList<Vertex>>> ret = new ArrayList<ArrayList<LinkedList<Vertex>>>();

		for (int h = 0; h<2; h++) {
			ArrayList<LinkedList<Vertex>> th = new ArrayList<LinkedList<Vertex>>();
			ret.add(th);

			for (int k = -maxdegree; k <= maxdegree; k++) {
				th.add(new LinkedList<Vertex>());
			}
		}

		return ret;
	}

	private static int[] getConnectedVerticesGain(List<Vertex> l) {
		int gains[] = new int[l.size()];

		int k = 0;
		for (Vertex th : l) {
			gains[k++] = th.getGain();
		}

		return gains;
	}

	public static void main(String args[]) {
		Graph g = Util.makeGraphFromFile("G500.005");
		g.initializePartition();

		System.out.println("startig score: "+g.getPartitioning().getScore());

		Partition p = (new FiducciaMattheyses()).search(g);

		System.out.println("final score:"+p.getScore());
	}
}
