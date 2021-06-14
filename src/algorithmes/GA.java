package algorithmes;

import Classes.Solution;

public class GA extends Solveur_SAT {
	public int N, taux_crossover, taux_mutation, max_iter;


	public GA(int N, int taux_crossover, int taux_mutation, int max_iter) {
		this.N = N;
		this.taux_crossover = taux_crossover;
		this.taux_mutation = taux_mutation;
		this.max_iter = max_iter;
	}

	@Override
	public Solution solve() {
		return solve(Long.MAX_VALUE);
	}

	@Override
	public Solution solve(long temps_max) {
		// TODO Auto-generated method stub
		return null;
	}

}
