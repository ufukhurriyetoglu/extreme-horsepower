package guarana;
import java.util.List;

public class SearchResult {
	public List<Partition> alloptima;
	public Partition best;
	public long elapsed;
	
	public SearchResult(List<Partition> alloptima, Partition best,	long elapsed) {
		this.alloptima = alloptima;
		this.best = best;
		this.elapsed = elapsed;
	}	
}