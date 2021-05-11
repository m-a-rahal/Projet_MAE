package Classes;

import java.util.ArrayList;
import java.util.Collection;

public class Solution extends ArrayList<Integer> implements Comparable<Solution>{
	private static final long serialVersionUID = 1L;
	private static final int COMPLETITION_VALUE = -1; /** utilisée pour compléter les solutions partielles*/
	protected int cost;
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

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
	
	public int sat_count(int clauses[][]) {
		int count = 0;
		for (int i = 0; i < clauses.length; i++) {
			if(this.satisfie_clause(clauses[i]))
				count++;
		}
		return count; // a ce point, tout les clauses sont SAT
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

	public Solution complete(int m) {
		for (int i = this.size(); i < m; i++) {
			this.add(COMPLETITION_VALUE);
		}
		return this;
	}

	@Override
	public int compareTo(Solution other) {
		return this.cost - other.getCost();
	}
	
	@Override
	public String toString() {
		StringBuffer text = new StringBuffer();
		for (Integer x : this) {
			text.append(x + " ");
		}
		return text.toString();
	}
}
