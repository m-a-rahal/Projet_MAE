package partie1;

import java.util.Stack;

import Classes.ClauseList;
import Classes.Solution;


public class DFS {
	public static Solution resoudre(ClauseList clauses) {
		int m = clauses.getM();
		Stack<Solution> ouvert = new Stack<>(); // contient les solutions a explorer
		ouvert.add(new Solution().extend(-1));
		ouvert.add(new Solution().extend(1));
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
}
