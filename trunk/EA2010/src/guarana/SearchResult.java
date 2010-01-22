package guarana;
import java.util.ArrayList;

public class SearchResult {
	public ArrayList<Partition> alloptima;
	public Partition best;
	public long elapsed;
	
	public SearchResult(ArrayList<Partition> alloptima, Partition best,	long elapsed) {
		this.alloptima = alloptima;
		this.best = best;
		this.elapsed = elapsed;
	}	
}