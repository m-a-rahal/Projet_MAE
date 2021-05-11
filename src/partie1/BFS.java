package partie1;

import java.util.LinkedList;

import Classes.ClauseList;
import Classes.Solution;


public class BFS {
	public static Solution resoudre(ClauseList clauses) {
		int m = clauses.getM();
		LinkedList<Solution> ouvert = new LinkedList<>(); // contient les solutions a explorer
		ouvert.add(new Solution().extend(1));
		ouvert.add(new Solution().extend(-1));
		while(ouvert.size() > 0) {
			Solution solution = ouvert.remove(); // défiler
			if (solution.sat(clauses)) {
				return solution.complete(m); // cette solution marche
			}
			if (solution.size() < m) {
				ouvert.add(solution.extend(1));
				ouvert.add(solution.extend(-1));
			}
		}
		return null;
	}
}
