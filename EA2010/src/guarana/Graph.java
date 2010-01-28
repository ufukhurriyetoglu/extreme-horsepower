package guarana;

import java.util.ArrayList;
import java.util.List;


public class Graph {

	private List<List<Vertex>> edges;
	private List<Vertex> vertices;
	private Partition partitioning;
	private List<Vertex> partitionA, partitionB;
	private int maxDegree;
	private Vertex bestGainVertex;
	
	public Graph() {
		edges = new ArrayList<List<Vertex>>();
		vertices = new ArrayList<Vertex>();
		partitionA = new ArrayList<Vertex>();
		partitionB = new ArrayList<Vertex>();
	}
	
	public int getMaxDegree() {
		return maxDegree;
	}
	
	public void setMaxDegree(int d) {
		maxDegree = d;
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public void addEdges(List<Vertex> l) {
		edges.add(l);
	}
	
	public Partition getPartitioning() {
		return partitioning;
	}
	
	public void setVertices(List<Vertex> l) {
		vertices = l;
	}	
	
	public void initializePartition() {
		this.setPartition(new Partition(vertices.size()));
	}
	
	public void setPartition(Partition p) {
		partitioning = p;
		partitionA.clear(); partitionB.clear();
		bestGainVertex = vertices.get(0);
		for (Vertex v : vertices) {
			//initialize partition lists
			if (partitioning.getPartition(v) == 0)
				partitionA.add(v);
			else
				partitionB.add(v);
			
			//initialize scores
			int i = 0, e = 0;
			for (Vertex neighbour : edges.get(v.getId())) {
				if (partitioning.getPartition(v) == 
					partitioning.getPartition(neighbour)) {
					i++;
				}
				else e++;
			}
			v.update(e, i);	
			if (v.getGain() > bestGainVertex.getGain())
				bestGainVertex = v;
		}
		partitioning.setScore(GraphUtil.score(partitionA));
	}
	

	public List<Vertex> getPartition0() {
		return partitionA;
	}
	
	public List<Vertex> getPartition1() {
		return partitionB;
	}
	
	/** 
	 * Change the current partition for given vertex v
	 * @param v
	 */
	public void switchVertex(Vertex v) {
		//update neighbors of v
		for (Vertex n : getNeighbors(v)) {
			if (partitioning.compare(v, n)) {
				n.neighbourMoved(true);
			}
			else {
				n.neighbourMoved(false);
			}
		}
		//update partition score
		partitioning.setScore(partitioning.getScore() - v.getGain());
		//update v
		v.swap();
		if (partitioning.getPartition(v) == 0) {
			partitionA.remove(v);
			partitionB.add(v);
			partitioning.setPartition(v, 1);
		}
		else {
			partitionB.remove(v);
			partitionA.add(v);
			partitioning.setPartition(v, 0);
		}
		
	}
	
	public boolean areNeighbours(Vertex a, Vertex b) {
		for (Vertex th : getNeighbors(a)) {
			if (b.equals(th)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @param v
	 * @return Return the neighbors of given vertex v as 
	 * 	a list of vertices 
	 * 
	 */
	public List<Vertex> getNeighbors(Vertex v) {
		return edges.get(v.getId());
	}
	
	//this is for debugging
	public static void main(String[] args) {
		Graph g1;
		g1 = Util.makeGraphFromFile("G500.005");
		g1.initializePartition();
		System.out.println("_____________________");
		Graph g2;
		g2 = Util.makeGraphFromFile("G500.005");
		Partition p = g1.getPartitioning();
		g2.setPartition(p);
		//g2.initializePartition();
		
		/*for (Vertex v: g.getPartition0()) {
			System.out.println(v + " - " + g.getNeighbors(v));
		}*/
		
		System.out.println(g1.getPartition1().size() + "/" + g1.getPartition0().size());
		System.out.println("Max Degree = " + g1.getMaxDegree());
		//System.out.println("Score = " + g1.getPartitioning().getScore());
		
	}
}
