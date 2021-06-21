package algorithmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Classes.Individus;
import Classes.Parents;
import Classes.Population;
import Classes.Solution;

public class GA extends Solveur_SAT {
	public int N, max_iter;
	public double taux_crossover, taux_mutation;
	private ArrayList<Integer> indices;
	private Random random;


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

		Population population = new Population(); /* List of individuals of the population */
		Individus x, child1, child2; /* Temporary Individus (used to create the initial population) */
		ArrayList<Parents> parents = new ArrayList<>(); /* The two individuals chosen for crossover process */
		Population children = new Population(); /* Results of crossing between two individuals */
		random = new Random(System.currentTimeMillis());
		indices = new ArrayList<>(clauses.getM());
		for (int i = 0; i < clauses.getM(); i++) {
			indices.add(i);
		}

		long startTime = System.currentTimeMillis(); /* Save the start time of the search */

		int countInd = 0;
		while(countInd < N) { /* Create the initial population */
			x = new Individus(clauses); 
			if(!population.contains(x)) {
				population.add(x); 
				countInd++; 
			}
		}

		for(int iteration=0; iteration<max_iter; iteration++) {
			parents.clear();
			children.clear();
			if((System.currentTimeMillis() - startTime) >= temps_max)
				break; /* If the search time has reached (or exceeded) the allowed run time, finish the search */

			ArrayList<Individus> pop_tmp = new ArrayList<>(population);
			Collections.shuffle(pop_tmp);
			for (int i = 0; i < pop_tmp.size()/2; i++) {
				parents.add(new Parents(pop_tmp.get(2*i), pop_tmp.get(2*i+1)));
			}
			for (Parents pair : parents) {
				//int endroit = random.nextInt(clauses.getM());
				child1 = croiser(pair.p1, pair.p2);
				child2 = croiser(pair.p2, pair.p1);
				children.add(muter(child1));
				children.add(muter(child2));
				if (child1.getF() == clauses.getN()) {
					return child1;
				}
				if (child2.getF() == clauses.getN()) {
					return child2;
				}
			}
			for (Individus child : children) {
				population.add(child); // add new child
				population.remove(); // remove worst individual in population
			}
		}

		Solution bestSol = new Solution(clauses.getM()); /* "Search time"/"max_iter" reached, return the best solution found */
		for(Individus ind : population) /* Search for the best Individus (best solution <=> best evaluation) in current population */
			if(bestSol.sat_count(clauses) < ind.sat_count(clauses))
				bestSol = ind; /* Update the best solution */

		return bestSol;
	}

	private Individus muter(Individus ind) {
		float r = random.nextFloat();
		if(r < taux_mutation) { 
			Collections.shuffle(indices);
			for (int i = 0; i < (int)((r*taux_mutation)*clauses.getM()); i++) {
				ind.muter(clauses, indices.get(i));
			}
		}
		return ind;
	}

	private Individus croiser(Individus x, Individus y, int endroit) { // one point crossover
		Individus res = new Individus(x);
		if(random.nextFloat() < taux_crossover) {
			for(int i=0; i<endroit; i++)
				res.set(i, x.get(i));

			for(int i=endroit; i<res.size(); i++)
				res.set(i, y.get(i));
			
			res.setF(res.sat_count(clauses));
		}
		return res;
	}
	
	private Individus croiser(Individus x, Individus y) { // croisement uniforme
		Individus res = new Individus(x);
		if(random.nextFloat() < taux_crossover) {
			for(int i=0; i< res.size(); i++)
				if (random.nextBoolean()) {
					res.set(i, x.get(i));
				} else {
					res.set(i, y.get(i));
				}
			res.setF(res.sat_count(clauses));
		}
		return res;
	}

}
