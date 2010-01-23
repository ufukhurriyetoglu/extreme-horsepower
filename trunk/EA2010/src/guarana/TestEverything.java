package guarana;

public class TestEverything {
	public static void main(String args[]) {
		
		Graph g = Util.makeGraphFromFile("G500.005");
		
		SearchResult s = null;;

		System.out.println("GRAPH 1 - G500.005");
//		System.out.println("STEP 1 fixed n LO hamming-------------- ");
//
//		s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
//
//		System.out.println("FM 1000 LO distances ------------------ ");
//		Util.outHammingDistandScores(s);
//
//		s = MultiStartLocalSearch.search_fixedNOptima(new KernighanLin(), 1000, g);
//
//		System.out.println("KL 1000 LO distances ------------------ ");
//		Util.outHammingDistandScores(s);

		// step 2 - run 1000 times MSLS fixed Noptima (1000) with FM and KL output the best scores found
		System.out.println("STEP 2 fixed n LO KL vs FM ------------ ");

//		System.out.println("FM     -------------------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}

		System.out.println("KL     -------------------------------- ");
		for (int k = 0; k < 100; k++) {
			s = MultiStartLocalSearch.search_fixedNOptima(new KernighanLin(), 1000, g);
			System.out.println(s.best.getScore() + ", " + s.elapsed);
		}
		
		System.out.println("STEP 3 fixed time KL vs FM ------------ ");

//		System.out.println("FM     -------------------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = MultiStartLocalSearch.search_fixedTime(new FiducciaMattheyses(), 10000, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}		

		System.out.println("KL     -------------------------------- ");
		for (int k = 0; k < 100; k++) {
			s = MultiStartLocalSearch.search_fixedTime(new KernighanLin(), 10000, g);
			System.out.println(s.best.getScore() + ", " + s.elapsed);
		}
		
//		System.out.println("STEP 4 fixed Noptima ILS -------------- ");
//		System.out.println(" mutation size 10 --------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = IterativeLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, 20, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}
//		System.out.println(" hammings ----------------------------- ");
//		Util.outHammingDistandScores(s);
//		
//		System.out.println(" mutation size 50 --------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = IterativeLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, 50, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}
//		System.out.println(" hammings ----------------------------- ");
//		Util.outHammingDistandScores(s);
//		
//		System.out.println(" mutation size 100 --------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = IterativeLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, 50, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}
//		System.out.println(" hammings ----------------------------- ");
//		Util.outHammingDistandScores(s);
		
		
		
		g = Util.makeGraphFromFile("U500.05");
		System.out.println("GRAPH 2 - U500.05");		
		
//		System.out.println("STEP 1 fixed n LO hamming-------------- ");
//
//		s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
//
//		System.out.println("FM 1000 LO distances ------------------ ");
//		Util.outHammingDistandScores(s);
//
//		s = MultiStartLocalSearch.search_fixedNOptima(new KernighanLin(), 1000, g);
//
//		System.out.println("KL 1000 LO distances ------------------ ");
//		Util.outHammingDistandScores(s);

		// step 2 - run 1000 times MSLS fixed Noptima (1000) with FM and KL output the best scores found
		System.out.println("STEP 2 fixed n LO KL vs FM ------------ ");

//		System.out.println("FM     -------------------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = MultiStartLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}

		System.out.println("KL     -------------------------------- ");
		for (int k = 0; k < 100; k++) {
			s = MultiStartLocalSearch.search_fixedNOptima(new KernighanLin(), 1000, g);
			System.out.println(s.best.getScore() + ", " + s.elapsed);
		}
		
		System.out.println("STEP 3 fixed time KL vs FM ------------ ");

//		System.out.println("FM     -------------------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = MultiStartLocalSearch.search_fixedTime(new FiducciaMattheyses(), 10000, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}		

		System.out.println("KL     -------------------------------- ");
		for (int k = 0; k < 100; k++) {
			s = MultiStartLocalSearch.search_fixedTime(new KernighanLin(), 10000, g);
			System.out.println(s.best.getScore() + ", " + s.elapsed);
		}
		
//		System.out.println("STEP 4 fixed Noptima ILS -------------- ");
//		System.out.println(" mutation size 10 --------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = IterativeLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, 20, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}
//		System.out.println(" hammings ----------------------------- ");
//		Util.outHammingDistandScores(s);
//		
//		System.out.println(" mutation size 50 --------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = IterativeLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, 50, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}
//		System.out.println(" hammings ----------------------------- ");
//		Util.outHammingDistandScores(s);
//		
//		System.out.println(" mutation size 100 --------------------- ");
//		for (int k = 0; k < 100; k++) {
//			s = IterativeLocalSearch.search_fixedNOptima(new FiducciaMattheyses(), 1000, 50, g);
//			System.out.println(s.best.getScore() + ", " + s.elapsed);
//		}
//		System.out.println(" hammings ----------------------------- ");
//		Util.outHammingDistandScores(s);
	}

}
