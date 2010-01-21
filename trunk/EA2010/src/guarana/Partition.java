package guarana;

import java.util.Arrays;


public class Partition implements Comparable<Partition>{

	private int[] partition;
	private int score;
	
	public Partition(int size) {
		partition = GraphUtil.generateRandomPartition(size);
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public int getScore() {
		return score;
	}
	
	public int[] getPartition() {
		return partition;
	}
	
	public int getPartition(Vertex v) {
		return partition[v.getId()];		
	}
	
	public void setPartition(int[] p) {
		partition = p;
	}

	public void setPartition(Vertex v, int p) {
		if (p > 1 || p < 0) System.exit(-1);
		partition[v.getId()] = p;
	}
	
	public int getSize() {
		return partition.length;
	}
	
	/**
	 * inverts the partitioning
	 */
	public void invert() {
		for (int i=0; i<getSize(); i++) {
			if (partition[i] == 0) partition[i] = 1;
			else partition[i] = 0;
		}
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return True if v1,v2 belong to same partition, false otherwise
	 */
	public boolean compare(Vertex v1, Vertex v2) {
		return (partition[v1.getId()] == partition[v2.getId()]);
	}
	
	public Object clone() throws CloneNotSupportedException {
	    Partition c = new Partition(partition.length);
	    c.setPartition(Arrays.copyOf(partition, partition.length));
	    c.setScore(score);
	    return c;
	}
	
	public boolean equals (Object o ) {
		if (o instanceof Partition) {
			return Arrays.equals(this.getPartition(), ((Partition) o).getPartition());
		}
		return false;
	}
	
	public static void main(String[] args) {
		
	}

	/**
	 * Compare the two partitions based on score only
	 */
	public int compareTo(Partition p) {
		if (this.getScore() > p.getScore()) return 1;
		else if (this.getScore() == p.getScore()) return 0;
		else return -1;
	}
}
