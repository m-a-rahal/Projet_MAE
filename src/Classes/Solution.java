package Classes;

import java.util.ArrayList;
import java.util.Collection;

public class Solution extends ArrayList<Integer>{
	private static final long serialVersionUID = 1L;
	
	public Solution(int size) {
		super(size);
	}
	
	public Solution(Collection<Integer> solution) {
		super(solution);
	}
	
	public Solution() {
		super();
	}

	public Solution extend(int instatiation) {
		/** retourne une nouvelle solution etendue en rajoutant l'instatioation donnée */
		Solution new_Solution = new Solution(this);
		new_Solution.add(instatiation);
		return new_Solution;
	}
	
	public boolean sat(int clauses[][]) {
		for (int i = 0; i < clauses.length; i++) {
			if(!this.satisfie_clause(clauses[i])) {
				return false; // si une clause n'est pas SAT, le systeme n'est pas SAT aussi
			}
		}
		return true; // a ce point, tout les clauses sont SAT
	}
	
	public boolean satisfie_clause(int clause[]) {
		// solution size must to be <= clause size (solution size < clause in case the solution is incomplete for example)
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i) == clause[i]) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuffer text = new StringBuffer();
		for (Integer x : this) {
			text.append(x + " ");
		}
		return text.toString();
	}

	public Solution complete(int m) {
		for (int i = this.size(); i < m; i++) {
			this.add(-1);
		}
		return this;
	}
}
