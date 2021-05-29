package io_classes;

import Classes.ClauseList;
import partie1.Solveur_constructif;

public class Tester {
	protected String output_file;
	protected String output_folder = "#TEST_RESULTS";
	public int min_n = 8;
	public int min_m = 3;
	public int max_n;
	public int max_m;
	
	public Tester(int max_n, int max_m) {
		this.max_n = max_n;
		this.max_m = max_m;
	}
	
	public void tester(Solveur_constructif solveur) {
		// sat instances
		boolean SAT = true;
		for (int m = min_m; m <= max_m; m++) {
			for (int n = min_n; n <= max_n; n++) {
				ClauseList clauses = new ClauseList(n, m).gen_aleat(SAT);
				long t0 = System.currentTimeMillis();
				solveur.solve(clauses);
				save_data(System.currentTimeMillis() - t0, solveur.getClass().getSimpleName(), n, m, SAT);
			}
		}
		
		// non-SAT instances
		SAT = false;
		for (int m = min_m; m <= max_m; m++) {
			for (int n = min_n; n <= max_n; n++) {
				ClauseList clauses = new ClauseList(n, m).gen_aleat(SAT);
				long t0 = System.currentTimeMillis();
				solveur.solve(clauses);
				save_data(System.currentTimeMillis() - t0, solveur.getClass().getSimpleName(), n, m, SAT);
			}
		}
	}
	
	private void save_data(long duration, String nom_solveur, int n, int m, boolean sat) {
		String type = sat ? "SAT" : "non-SAT";
		System.out.println(String.format("%s(%d,%d,%s):%dms", nom_solveur,n,m,type,duration));
	}
}
