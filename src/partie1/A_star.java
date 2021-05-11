package partie1;

import java.util.PriorityQueue;
import Classes.Solution;


public class A_star {
	PriorityQueue<Solution> ouvert = new PriorityQueue<>(); // contient les solutions a explorer
	int clauses[][]; 
	
	public Solution resoudre(int clauses[][]) {
		this.clauses = clauses;
		int m = clauses[0].length;
		inseret_in_ouvert(new Solution().extend(0)); // inserer dans la bonne position
		inseret_in_ouvert(new Solution().extend(1)); // inserer dans la bonne position
		while(ouvert.size() > 0) {
			Solution solution = ouvert.remove(); // défiler
			if (solution.sat(clauses)) {
				return solution.complete(m); // cette solution marche
			}
			if (solution.size() < m) {
				inseret_in_ouvert(solution.extend(0)); // inserer dans la bonne position
				inseret_in_ouvert(solution.extend(1)); // inserer dans la bonne position
			}
		}
		return null;
	}
	
	public int valuation(Solution s) {
		return s.sat_count(clauses);
	}
	
	
	public boolean inseret_in_ouvert(Solution s) {
		s.setCost(valuation(s));
		return ouvert.add(s);
	}
}
