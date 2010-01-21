package guarana;


public class Vertex implements Comparable<Vertex>{

	private int id;
	private int external;
	private int internal;
	
	public Vertex(int i) {
		id = i;
	}
	
	public int getId() {
		return id;
	}

	public int getGain() {
		return (external-internal);
	}
	
	public int getExternal() {
		return external;
	}
	
	public void update(int ex, int in) {
		external = ex;
		internal = in;
	}
	
	public void swap() {
		int temp = external;
		external = internal;
		internal = temp;
	}
	
	/**
	 * Method that updates a vertex when a neighbor is moved
	 * @param where True for same partition, false otherwise.
	 */
	public void neighbourMoved(boolean where) {
		if (where) {
			this.update(external+1, internal-1);
		}
		else {
			this.update(external-1, internal+1);
		}
	}

	public boolean equals(Object v) {
		if (v instanceof Vertex) {
			return (((Vertex) v).getId() == this.getId());
		}
		else return false;
	}
	
	public String toString() {
		return ("Vertex " + id); 
				//+ ",(" + external + "," + internal + ")");
	}



	public int compareTo(Vertex o) {
		if (this.getId() > o.getId())
			return 1;
		else if (this.getId() == o.getId())
			return 0;
		else return -1;
	}
}
