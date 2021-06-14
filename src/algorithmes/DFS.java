package algorithmes;

import Classes.ClauseList;
import Classes.Solution;

import java.util.Stack;


public class DFS extends Solveur_SAT{
	protected Stack<Solution> ouvert;
	
	@Override
	public Solution solve() {
		int m = clauses.getM();
		ouvert = new Stack<Solution>(); // contient les solutions a explorer
		ouvert.add(new Solution()); // inserer solution vide
		while(ouvert.size() > 0) {
			Solution solution = ouvert.pop(); // dépiler
			if (solution.sat(clauses)) {
				return solution.complete(m); // cette solution marche
			}
			if (solution.size() < m) {
				ouvert.add(solution.extend(-1));
				ouvert.add(solution.extend(1));
			}
		}
		return null;
	}

	@Override
	public Solution solve(long temps_max) {
		int m = clauses.getM();
		ouvert = new Stack<Solution>(); // contient les solutions a explorer
		
		// ### init best solution as empty and add it in ouvert to calculate its f()
		Solution best = new Solution();
		inseret_in_ouvert(best); // inserer solution vide
		
		long t0 = System.currentTimeMillis();
		while(ouvert.size() > 0) {
			// ### if limit time exceeded, return best
			if((System.currentTimeMillis() - t0) >= temps_max)
				return best;
			Solution solution = ouvert.pop(); // dépiler
			// ### choose best solution
			if (best.getF() > solution.getF()) {
				best = solution;
			}
			if (solution.getF() == 0) {// ### f == 0 <---> solution sat all clauses
				return solution.complete(m); // cette solution marche
			}
			if (solution.size() < m) {
				inseret_in_ouvert(solution.extend(-1));
				inseret_in_ouvert(solution.extend(1));
			}
		}
		return null;
	}

	public boolean inseret_in_ouvert(Solution s) {
		s.setF(s.non_sat_count(clauses));
		return ouvert.add(s);
	}	

}
