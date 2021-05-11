package Classes;

import java.util.ArrayList;
import java.util.Collection;

public class Solution extends ArrayList<Integer> implements Comparable<Solution>{
	private static final long serialVersionUID = 1L;
	private static final int COMPLETITION_VALUE = 0; /** utilisée pour compléter les solutions partielles*/
	protected int f;
	
	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
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

	public Solution extend(int signe) {
		/** retourne une nouvelle solution etendue en rajoutant l'instatioation donnée */
		Solution new_Solution = new Solution(this);
		new_Solution.add(signe*(size() + 1));
		return new_Solution;
	}
	
	public boolean sat(ClauseList clauses) {
		for (int i = 0; i < clauses.size(); i++) {
			if(!this.satisfie_clause(clauses.get(i))) {
				return false; // si une clause n'est pas SAT, le systeme n'est pas SAT aussi
			}
		}
		return true; // a ce point, tout les clauses sont SAT
	}
	
	public int non_sat_count(ClauseList clauses) {
		int count = 0;
		for (int i = 0; i < clauses.size(); i++) {
			if(!this.satisfie_clause(clauses.get(i)))
				count++;
		}
		return count; // a ce point, tout les clauses sont SAT
	}
	
	public boolean satisfie_clause(Clause clause) {
		for (Integer x : this) {
			if (clause.contains(x))
				return true;
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
		return this.f - other.getF();
	}
	
	@Override
	public String toString() {
		StringBuffer text = new StringBuffer();
		for (Integer x : this) {
			text.append(x + " ");
		}
		if (f != 0)
			text.append("(f = "+f+") ");
		return text.toString();
	}
}
