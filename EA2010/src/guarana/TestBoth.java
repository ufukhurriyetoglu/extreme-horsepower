package guarana;

public class TestBoth {
	public static void main(String args[]) {
		
		long start, end;
		
		Graph g = Util.makeGraphFromFile("G500.005");
		g.initializePartition();
		
		Partition oldp = null; 
		
		try {
			oldp = (Partition) g.getPartitioning().clone();
		} catch (CloneNotSupportedException e) {}
		
		System.out.println("startig score: "+g.getPartitioning().getScore());
		System.out.println("maxdegree: "+g.getMaxDegree());
		
		start = System.currentTimeMillis();
		Partition p = (new KernighanLin()).search(g);
		end = System.currentTimeMillis();		
		
		System.out.println("final score with KL:"+p.getScore()+ " elapsed: "+(end-start)+" ms");
		
		g.setPartition(oldp);
		
		start = System.currentTimeMillis();
		p = (new FiducciaMattheyses()).search(g);
		end = System.currentTimeMillis();
		
		System.out.println("final score with FM:"+p.getScore()+ " elapsed: "+(end-start)+" ms");
		
	}

}
