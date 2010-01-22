package guarana;

public class TestEverything {
	private static void main(String args[]) {
		
		Graph g = Util.makeGraphFromFile("G500.005");

		System.out.println("STEP 1 -------------------------------- ");

		SearchResult s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);

		System.out.println("FM 1000 LO distances ------------------ ");
		Util.outHammingDistandScores(s);

		s = MultiStartLocalSearch.search_fixedNOptima(new KernighanLin(), 1000, g);

		System.out.println("KL 1000 LO distances ------------------ ");
		Util.outHammingDistandScores(s);

		// step 2 - run 1000 times MSLS fixed Noptima (1000) with FM and KL output the best scores found
		System.out.println("STEP 2 -------------------------------- ");

		System.out.println("FM     -------------------------------- ");
		for (int k = 0; k < 100; k++) {
			s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
			System.out.println(s.best.getScore() + ", " + s.elapsed);
		}

		System.out.println("KL     -------------------------------- ");
		for (int k = 0; k < 100; k++) {
			s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
			System.out.println(s.best.getScore() + ", " + s.elapsed);
		}
	}

}
