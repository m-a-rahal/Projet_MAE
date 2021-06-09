package partie1;

import Classes.Solution;

import java.util.PriorityQueue;


public class A_star extends Solveur_constructif {
	

	
	@Override
	public Solution solve() {
		int m = clauses.getM();
		ouvert = new PriorityQueue<Solution>(); // contient les solutions a explorer
		inseret_in_ouvert(new Solution());  // inserer solution vide
		while(ouvert.size() > 0) {
			Solution solution = ouvert.remove(); // défiler
			//System.out.println(solution.getF());//####
			if (solution.getF() - solution.size() == 0) { //f = non_sat + size ==> si f - size == 0 alors solution sat all clauses
				return solution.complete(m); // cette solution marche
			}
			if (solution.size() < m) {
				inseret_in_ouvert(solution.extend(-1)); // inserer dans la bonne position
				inseret_in_ouvert(solution.extend(1)); // inserer dans la bonne position
			}
		}
		return null;
	}
	
	public boolean inseret_in_ouvert(Solution s) {
		s.setF(s.non_sat_count(clauses) + s.size()); // f(x)=h(s)+c(s) a minimiser = h(x) + g(x), g(x) = cost = taille (solution)
		return ouvert.add(s);
	}
}
