package guarana;
import java.util.ArrayList;

public class SearchResult {
	public ArrayList<Partition> alloptima;
	public Partition best;

	public SearchResult(ArrayList<Partition> alloptima, Partition best) {
		this.alloptima = alloptima;
		this.best = best;
	}		
}