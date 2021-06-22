package io_classes;

import java.util.LinkedList;

import Classes.ClauseList;
import Classes.Solution.TimedSolution;
import algorithmes.GA;
import algorithmes.PSO;
import algorithmes.Solveur_SAT;

public class MetaTester {
	protected String output_file;
	protected String output_folder = "#TEST_RESULTS";
	public int first_file = 1;
	public int last_file = 5;
	public Params<GA> params_GA;
	public Params<PSO> params_PSO;
	public Solveur_SAT solveur;
	
	public MetaTester(int min_N, int max_N, int crs_min, int crs_max, int mut_min, int mut_max) {
		solveur = new GA(0, 0, 0, 0);
		params_GA = new Params<GA>();
		for (int n = min_N; n <= max_N; n+=10) {
			for (int mut = mut_min; mut <= mut_max; mut+=10) {
				for (int crs = crs_min; crs <= crs_max; crs+=10) {
					params_GA.add(new GA(n,crs,mut,10000));
				}
			}
		}
	}
	
	public MetaTester(int min_N, int max_N, double min_c1, double max_c1, double min_c2, double max_c2, double min_p, double max_p) {
		solveur = new PSO(0, 0, 0, 0, 0);
		params_PSO = new Params<PSO>();
		for (int n = min_N; n <= max_N; n+=10) {
			for (double c1 = min_c1; c1 <= max_c1; c1+=0.1) {
				for (double c2 = min_c2; c2 <= max_c2; c2+=0.1) {
					for (double p = min_p; p <= max_p; p+=0.1) {
						params_PSO.add(new PSO(n,c1,c2,p,10000));
					}
				}
			}
		}
	}
	
	public void tester(long time_limit) {
		// sat instances
		boolean SAT = true;
		loop(SAT, time_limit);
		
		// non-SAT instances
		SAT = false;
		loop(SAT, time_limit);
	}
	
	public void tester(boolean SAT, long time_limit) {
		loop(SAT, time_limit);
	}
	
	private void loop(boolean SAT, long time_limit) {
		FileManager fileManager = new FileManager(false);
		Params<?> params_list = null;
		if(solveur instanceof GA)
			params_list = params_GA;
		else if (solveur instanceof PSO)
			params_list = params_PSO;
		for(Object param : params_list) {
			TimedSolution avg_Solution = new TimedSolution();
			for (int i = first_file; i <= last_file; i++) {
				ClauseList clauses = fileManager.lire_benchmark(SAT ? FileManager.SAT : FileManager.NON_SAT, i);
				//TimedSolution solution = ((Solveur_SAT) param).resoudre(clauses, time_limit);
				avg_Solution.ajouter(((Solveur_SAT) param).resoudre(clauses, time_limit));
			}
			show_data(avg_Solution.diviser(last_file-first_file+1), (Solveur_SAT) param, SAT);
		}
	}
	
	private void show_data(TimedSolution solution, Solveur_SAT solveur, boolean sat) {
		String type = sat ? "SAT" : "non-SAT";
		System.out.println(String.format("%s %s\t"+solveur.toString()+"\t%f%%\t%d", solveur.getClass().getSimpleName(),type,solution.sat_precent(),solution.time));
	}
	
	public class Params<T> extends LinkedList<T> {
		private static final long serialVersionUID = 1L;
	}
	
}
