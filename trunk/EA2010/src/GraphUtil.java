
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GraphUtil {


	public static int[] generateRandomPartition(int size) {
		int[] p = new int[size];
		int counter = 0;
		Random rand = new Random();
		while(counter < size/2) {
			int i = rand.nextInt(size);
			if (p[i] == 0) {
				p[i] = 1;
				counter++;
			}
		}
		return p;
	}
	
	public static int score(List<Vertex> list) {
		int sc = 0;
		for (Vertex v : list)
			sc += v.getExternal();
		return sc;
	}
	
	// takes a graph and swaps some vertices
	public static void moveALittleBit(Graph g, int nswaps) {
		int size = g.getVertices().size();		
		ArrayList<Integer> swapped = new ArrayList<Integer>();

		Random r = new Random();

		for (int k = 0; k < nswaps; k++) {
			Vertex a, b;

			do {
				a = g.getPartition0().get( r.nextInt(size/2) );				
			} while (swapped.contains(a.getId()));

			do {
				b = g.getPartition1().get( r.nextInt(size/2) );				
			} while (swapped.contains(b.getId()));

			g.switchVertex(a); g.switchVertex(b);

			swapped.add(a.getId()); swapped.add(b.getId());			
		}		
	}
	
	public static void main (String[] args) {
		int[] i = GraphUtil.generateRandomPartition(500);
		int z=0,o=0;
		for (int k=0; k < i.length; k++) {
			if (i[k] == 0) z++;
			else if (i[k] == 1) o++;
		}
		System.out.println(z + "/" + o);
	}
}
