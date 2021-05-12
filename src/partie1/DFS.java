package partie1;
import java.util.Stack;
import Classes.Solution;


public class DFS extends Solveur_constructif{
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
}
