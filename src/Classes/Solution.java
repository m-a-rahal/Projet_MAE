package Classes;

import java.util.ArrayList;
import java.util.Collection;

public class Solution extends ArrayList<Integer> implements Comparable<Solution>{
	private static final long serialVersionUID = 1L;
	private static final Integer COMPLETITION_VALUE = null; /** utilisée pour compléter les solutions partielles, si null on complete la solution avec des x normales (pas des -x)*/
	protected int fitness;
	
	public int getF() {
		return fitness;
	}

	public void setF(int f) {
		this.fitness = f;
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
	
	public int sat_count(ClauseList clauses) {
		int count = 0;
		for (int i = 0; i < clauses.size(); i++) {
			if(this.satisfie_clause(clauses.get(i)))
				count++;
		}
		return count; // a ce point, tout les clauses sont SAT
	}
	
	public boolean satisfie_clause(Clause clause) {
		/*for (Integer x : this) {
			if (clause.contains(x))
				return true;
		}*/ // old and not optimized for 3 sat
		int i;
		for (Integer x : clause) { // optimized for 3 sat
			i = Math.abs(x) - 1;
			if (this.size() >= i+1) {
				if (get(i) == x) {
					return true;
				}
			}
		}
		return false;
	}

	public Solution complete(int m) {
		if (COMPLETITION_VALUE != null)
			for (int i = this.size(); i < m; i++) {
				this.add(COMPLETITION_VALUE);
			} 
		else
			for (int i = this.size(); i < m; i++) {
				this.add(size() + 1);
			} 
		return this;
	}

	@Override
	public int compareTo(Solution other) {
		return this.fitness - other.getF();
	}
	
	@Override
	public String toString() {
		StringBuffer text = new StringBuffer();
		for (Integer x : this) {
			text.append(x + " ");
		}
		/*if (fitness != 0)
			text.append("(f = "+fitness+") ");*/
		return text.toString();
	}

	public static Solution gen_alea(int m) {
		Solution s = new Solution();
		for (int i = 0; i < m; i++) {
			if (Math.random() > 0.5) {
				s.add(i+1);
			} else {
				s.add(-(i+1));
			}
		}
		return s;
	}
	
	public Solution self_gen_alea(int m) {
		for (int i = 0; i < m; i++) {
			if (Math.random() > 0.5) {
				add(i+1);
			} else {
				add(-(i+1));
			}
		}
		return this;
	}

	public int diff(Solution other) {
		assert(this.size() == other.size());
		int diff = 0;
		for (int i = 0; i < size(); i++) {
			if (this.get(i) != other.get(i))
				diff++;
		}
		return diff;
	}

	public void flip(Integer i) {
		set(i, -get(i));
	}

	public boolean egale(Solution other) {
		for (int i = 0; i < size(); i++) {
			if (get(i) != other.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static class TimedSolution extends Solution {
		private static final long serialVersionUID = 1L;

		public TimedSolution(Solution s) {
			super(s);
			fitness = s.getF();
		}
		public TimedSolution() {}

		public long time;
		public ClauseList clauses;

		public double sat_precent() {
			return 100*getF() / (double)clauses.getN();
		}
		
		public TimedSolution ajouter(TimedSolution other) {
			time += other.time;
			fitness = Math.max(fitness, other.fitness);
			clauses = other.clauses;
			return this;
		}
		
		public TimedSolution diviser(int k) {
			time /= k;
			return this;
		}
	}
}
