package partie1;

import java.util.PriorityQueue;

import Classes.ClauseList;
import Classes.Solution;


public class A_star {
	PriorityQueue<Solution> ouvert = new PriorityQueue<>(); // contient les solutions a explorer
	ClauseList clauses; 
	
	public Solution resoudre(ClauseList clauses) {
		this.clauses = clauses;
		int m = clauses.getM();
		inseret_in_ouvert(new Solution().extend(-1)); // inserer dans la bonne position
		inseret_in_ouvert(new Solution().extend(1)); // inserer dans la bonne position
		while(ouvert.size() > 0) {
			Solution solution = ouvert.remove(); // défiler
			//System.out.println(solution.getF());//####
			if (solution.sat(clauses)) {
				return solution.complete(m); // cette solution marche
			}
			if (solution.size() < m) {
				inseret_in_ouvert(solution.extend(-1)); // inserer dans la bonne position
				inseret_in_ouvert(solution.extend(1)); // inserer dans la bonne position
			}
		}
		return null;
	}
	
	public int h(Solution s) {
		return s.non_sat_count(clauses);
	}
	
	
	public boolean inseret_in_ouvert(Solution s) {
		s.setF(h(s) + s.size()); // f(x) a minimiser = h(x) + g(x), g(x) = cost = taille (solution)
		return ouvert.add(s);
	}
}
