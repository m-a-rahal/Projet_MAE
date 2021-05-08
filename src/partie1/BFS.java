package partie1;

import java.util.LinkedList;


public class BFS {
	public static Solution resoudre(int clauses[][]) {
		int m = clauses[0].length;
		LinkedList<Solution> ouvert = new LinkedList<>(); // contient les solutions a explorer
		ouvert.add(new Solution().extend(0));
		ouvert.add(new Solution().extend(1));
		while(ouvert.size() > 0) {
			Solution solution = ouvert.remove(); // défiler
			if (solution.size() < m) {
				ouvert.add(solution.extend(0));
				ouvert.add(solution.extend(1));
			} else {
				if (solution.appliquer(clauses)) {
					return solution; // cette solution marche
				}
			}
		}
		return null;
	}
}
