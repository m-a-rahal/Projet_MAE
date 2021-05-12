package partie1;

import java.util.LinkedList;
import Classes.Solution;


public class BFS extends Solveur_constructif {
	@Override
	public Solution solve() {
		int m = clauses.getM();
		ouvert = new LinkedList<Solution>(); // contient les solutions a explorer
		ouvert.add(new Solution());  // inserer solution vide
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
