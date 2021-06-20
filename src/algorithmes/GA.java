package algorithmes;

import java.util.ArrayList;
import java.util.Random;

import Classes.Individus;
import Classes.Solution;

public class GA extends Solveur_SAT {
	public int N, max_iter;
	public double taux_crossover, taux_mutation;


	public GA(int N, int taux_crossover, int taux_mutation, int max_iter) {
		this.N = N;
		this.taux_crossover = ((double)taux_crossover)/100;
		this.taux_mutation = ((double)taux_mutation)/100;
		this.max_iter = max_iter;
	}

	@Override
	public Solution solve() {
		return solve(Long.MAX_VALUE);
	}

	@Override
	public Solution solve(long temps_max) {

		ArrayList<Individus> population = new ArrayList<Individus>(); /* List of individuals of the population */
		Individus tempInd = null; /* Temporary Individus (used to create the initial population) */
		int countInd = 0, index = 0; /* Counters (used to create the initial population) */
		int[] choosedInd = new int[2]; /* The two individuals chosen for crossover process */
		Individus[] resCrInd = new Individus[2]; /* Results of crossing between two individuals */
		Random random = new Random(); /* Random Object (for different random values) */
		float mr;

		long startTime = System.currentTimeMillis(); /* Save the start time of the search */

		while(countInd < N) { /* Create the initial population */
			tempInd = new Individus(clauses); /* Create an Individus with a random solution */
			if(!individus_in_population(tempInd, population)) {/* Check if there isn't already an Individus in the population with the same solution */
				population.add(new Individus(tempInd)); /* Add the new Individus "tempInd" to the population */
				countInd++; /* Increase the number of individuals in the population */
			}
		}

		for(int iteration=0; iteration<max_iter; iteration++) {
			if((System.currentTimeMillis() - startTime) >= temps_max)
				break; /* If the search time has reached (or exceeded) the allowed run time, finish the search */

			do { /* Selection process : Choose TWO random individuals from the population */
				choosedInd[0] = random.nextInt(N);
				choosedInd[1] = random.nextInt(N);
			}while(choosedInd[0] == choosedInd[1]); /* We must choose two DIFFERENT individuals (The same ones have no effect in crossover process) */

			if(random.nextFloat() < taux_crossover) { /* If "Rc" allows the crossover process (value "101" used to take the interval [0;100])*/
				resCrInd[0] = croiser(population.get(choosedInd[0]), population.get(choosedInd[1]), random.nextInt(clauses.getM()), true);
				resCrInd[1] = croiser(population.get(choosedInd[0]), population.get(choosedInd[1]), random.nextInt(clauses.getM()), false);

				if((mr = random.nextFloat()) < taux_mutation) { /* If "Rm" allows the mutation process (value "101" used to take the interval [0;100])*/
					ArrayList<Integer> availableLiterals = new ArrayList<Integer>();
					for (int i = 0; i < clauses.getM(); i++) {
						availableLiterals.add(i);
					}
					for (int i = 0; i < (mr*taux_mutation)%clauses.getM(); i++) {
						resCrInd[0].muter(clauses, availableLiterals.remove(random.nextInt(availableLiterals.size())));
					}
				}
				if((mr = random.nextFloat()) < taux_mutation) { /* If "Rm" allows the mutation process (value "101" used to take the interval [0;100])*/
					ArrayList<Integer> availableLiterals = new ArrayList<Integer>();
					for (int i = 0; i < clauses.getM(); i++) {
						availableLiterals.add(i);
					}
					for (int i = 0; i < (mr*taux_mutation)%clauses.getM(); i++) {
						resCrInd[1].muter(clauses, availableLiterals.remove(random.nextInt(availableLiterals.size())));
					}
				}

				for(int i=0; i<population.size(); i++) { /* Update current population with new individuals */
					if((resCrInd[0] != null) && (population.get(i).getF() < resCrInd[0].getF())) {
						population.set(i, new Individus(resCrInd[0])); /* New Individus is better, replace current Individus with the new one */
						resCrInd[0] = null; /* Use first new Individus only once */
					}
					else if((resCrInd[1] != null) && (population.get(i).getF() < resCrInd[1].getF())) {
						population.set(i, new Individus(resCrInd[1])); /* New Individus is better, replace current Individus with the new one */
						resCrInd[1] = null; /* Use second new Individus only once */
					}

					if((resCrInd[0] == null) && (resCrInd[1] == null))
						break; /* The two new individuals already used (replaced an old ones in "population"), exit the loop */
				}
			}
		}

		Solution bestSol = new Solution(clauses.getM()); /* "Search time"/"max_iter" reached, return the best solution found */
		for(Individus ind : population) /* Search for the best Individus (best solution <=> best evaluation) in current population */
			if(bestSol.sat_count(clauses) < ind.sat_count(clauses))
				bestSol = ind; /* Update the best solution */

		return bestSol;
	}

	private boolean individus_in_population(Individus tempInd, ArrayList<Individus> population) {
		for(int i=0; i<population.size(); i++) 
			if(tempInd.egale(population.get(i))) 
				return true;
		return false;
	}

	private Individus croiser(Individus i1, Individus i2, int place, boolean firstCross) {
		Solution resultSol = new Solution().self_gen_alea(clauses.getM());

		if(firstCross) {
			for(int i=0; i<place; i++)
				resultSol.set(i, i1.get(i));

			for(int i=place; i<resultSol.size(); i++)
				resultSol.set(i, i2.get(i));
		}else { /* Second cross */
			for(int i=0; i<place; i++)
				resultSol.set(i, i2.get(i));

			for(int i=place; i<resultSol.size(); i++)
				resultSol.set(i, i1.get(i));
		}
		
		resultSol.setF(resultSol.sat_count(clauses));
		return(new Individus(resultSol));
	}

}
