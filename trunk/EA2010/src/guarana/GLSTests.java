package guarana;

public class GLSTests {

	public static void main(String[] args) {
		int population, times;
		Graph g;
		SearchResult sr;
		long time = System.currentTimeMillis();
		
		System.out.println("GRAPH 1 - G500.005 - POP=25");
		g = Util.makeGraphFromFile("G500.005");
		population  = 25;		
		
		System.out.println("\n-- RANDOM SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.RANDOM, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TOURNAMENT SELECTION");
		times = 500;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TOURNAMENT, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TRUNCATION SELECTION");
		times = 500;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TRUNCATION, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		System.out.println("_____________________________");
		
		System.out.println("GRAPH 1 - G500.005 - POP=50");
		population  = 50;		
		
		System.out.println("\n-- RANDOM SELECTION");
		times = 1500;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.RANDOM, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TOURNAMENT SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TOURNAMENT, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TRUNCATION SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TRUNCATION, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		System.out.println("_____________________________");
		
		System.out.println("GRAPH 2 - U500.05 - POP=25");
		g = Util.makeGraphFromFile("U500.05");
		population  = 25;		
		
		System.out.println("\n-- RANDOM SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.RANDOM, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TOURNAMENT SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TOURNAMENT, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TRUNCATION SELECTION");
		times = 500;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TRUNCATION, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		System.out.println("_____________________________");
		
		System.out.println("GRAPH 2 - U500.05 - POP=50");
		population  = 50;		
		
		System.out.println("\n-- RANDOM SELECTION");
		times = 1500;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.RANDOM, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TOURNAMENT SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TOURNAMENT, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		
		System.out.println("\n-- TRUNCATION SELECTION");
		times = 1000;
		for (int i=0; i<100; i++) {
			sr = GeneticLocalSearch.search_fixedPopulation(new FiducciaMattheyses(), population,
					times, GeneticLocalSearch.TRUNCATION, g);
			System.out.println(sr.best.getScore() + ", " + sr.elapsed);
		}
		System.out.println("_____________________________");
		time = System.currentTimeMillis() - time;
		System.out.println("Total time: " + time);
	}
}
