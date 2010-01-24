package guarana;


public class SwapDescriptor implements Comparable<SwapDescriptor> {
	private Vertex vA, vB;
	private int vAindex, vBindex;
	private int gain;
	private Graph g;

	// boring stuff, i want the search method static so i need the Graph object also here

	public SwapDescriptor(Vertex vA, Vertex vB, int vAindex, int vBindex, Graph g) {
		this.vA = vA;
		this.vB = vB;
		this.vAindex = vAindex;
		this.vBindex = vBindex;
		this.g = g;
		
		computeSwapGain();
	}	
	
	private void computeSwapGain() {
		int tosub = (g.areNeighbours(vA, vB) ? 1 : 0);

		gain = vA.getGain() + vB.getGain() - tosub;				
	}

	public int compareTo(SwapDescriptor o) {
		if (this.gain > o.getGain())
			return 1;
		else if (this.gain == o.getGain())
			return 0;
		else return -1;
	}

	public int getGain() {
		return gain;
	}

	public Vertex getvA() {
		return vA;
	}

	public Vertex getvB() {
		return vB;
	}

	public int getvAindex() {
		return vAindex;
	}

	public int getvBindex() {
		return vBindex;
	}	
}