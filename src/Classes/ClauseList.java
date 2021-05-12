package Classes;
import java.util.ArrayList;

public class ClauseList extends ArrayList<Clause> {
	protected int n;
	protected int m;
	private static final long serialVersionUID = 1L;

	public ClauseList(int n, int m) {
		super(n);
		this.n = n;
		this.m = m;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	
	public ClauseList gen_aleat(boolean sat, int taille_clause) {
		if (sat) { // il ya une solution S tq dans chaque clause, on doit avoir au moins un x qui est dans S
			Solution solution = Solution.gen_alea(m);
			for (int i = 0; i < n; i++) {
				Clause clause = new Clause();
				// ajouter un x (aléa) de solution dans clause
				int rand_pos = (int)(Math.random()*m);
				clause.add(solution.get(rand_pos));
				// compléter avec des x aléatoires et ajouter a cette liste
				this.add(clause.completer_aleatoirement(taille_clause-1, m));
			}
		} else { // for now it generates random clauses
			for (int i = 0; i < n; i++) {
				Clause clause = new Clause();
				// compléter avec des x aléatoires et ajouter a cette liste
				this.add(clause.completer_aleatoirement(taille_clause, m));
			}
		}
		return this;
	}
	public ClauseList gen_aleat(boolean sat) {
		return gen_aleat(sat, Math.min(m,3)); // comme dans les becnhmarks, 3 litteraux par clause
	}
}
