package guarana;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Util {

	
	public static Graph makeGraphFromFile(String file) {
		Graph g = new Graph();
		int maxDegree = 0;
		//Set<Vertex> vertices = new HashSet<Vertex>();
		List<Vertex> vertices = new ArrayList<Vertex>(50);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String s;
			StringTokenizer st;
			int i = 0;
			while ((s = reader.readLine()) != null) {
				//add vertex to Graph
				Vertex vv = new Vertex(i);
				if (!vertices.contains(vv)) {
					vertices.add(i, vv);
				}
				
				st = new StringTokenizer(s);
				
				//go through garbage
				st.nextToken(); st.nextToken(); 
				//keep an eye on max degree of a vertex
				int degree = Integer.parseInt(st.nextToken());
				if (maxDegree < degree)
					maxDegree = degree;
				
				//add edges of this vertex
				List<Vertex> l = new ArrayList<Vertex>();
				while (st.hasMoreTokens()) {
					int index = Integer.parseInt(st.nextToken()) - 1;
					Vertex v = new Vertex(index);
					if (!vertices.contains(v)) {
						vertices.add(v);
					}
					l.add(vertices.get(vertices.indexOf(v)));
				}
				
				g.addEdges(l);
				i++;
			}
			Collections.sort(vertices);
			//System.out.println(vertices);
			g.setVertices(vertices);
			g.setMaxDegree(maxDegree);
		}
		catch(IOException io) {
			io.printStackTrace();
			System.exit(1);
		}
		
		return g;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return The Hamming Distance between two given partitions
	 */
	public static int hammingDistance(Partition p1, Partition p2) {
		if (p1.getSize() != p2.getSize()) System.exit(-1);
		int d = 0;
		int[] part1 = p1.getPartition();
		int[] part2 = p2.getPartition();
		
		for (int i=0; i < p1.getSize(); i++) {
			if (part1[i] != part2[i]) d++;
		}
		
		if (d > p1.getSize()/2) {
			p1.invert();
			part1 = p1.getPartition();
			d = 0;
			for (int i=0; i < p1.getSize(); i++) {
				if (part1[i] != part2[i]) d++;
			}
		}
		return d;
	}
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		makeGraphFromFile("G500.005");

	}

}
