package Classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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
		if (taille_clause > m)
			throw new IndexOutOfBoundsException("ne peut remplir une clause avec une taille = "+ taille_clause+" > nbr variables = "+ m);
		else if (taille_clause <= 0) {
			return null;
		}
		
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
			// choose rand X_ subset of X of size k, where X = {1,2,3,...,m} the set of all variables
			if (n < Math.pow(2, taille_clause)) {
				System.out.println("WARNING: can't generate non SAT with nbr_clauses = "+n+" < 2^clause_size = 2^"+taille_clause+". returnung random ClauseList instead");
				return random_ClauseList(taille_clause);
			}
			int [] X_ = var_subset(taille_clause);
			Stack<Clause> ouvert = new Stack<>();
			ouvert.add(new Clause()); // inserer une clause vide 
			for (int x : X_) {
				Stack<Clause> new_ouvert = new Stack<>();
				while (ouvert.size() > 0) {
					Clause c = ouvert.pop();
					new_ouvert.add(c.extend(x));
					new_ouvert.add(c.extend(-x));
				}
				ouvert = new_ouvert;
			}
			this.addAll(ouvert);
		}
		return this;
	}
	
	private ClauseList random_ClauseList(int taille_clause) {
		for (int i = 0; i < n; i++) {
			Clause clause = new Clause();
			// compléter avec des x aléatoires et ajouter a cette liste
			this.add(clause.completer_aleatoirement(taille_clause, m));
		}
		return this;
	}

	public ClauseList gen_aleat(boolean sat) {
		return gen_aleat(sat, Math.min(m,3)); // comme dans les becnhmarks, 3 litteraux par clause
	}

	private int[] var_subset(int subset_size) {
		ArrayList<Integer> X =  new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			X.add(i);
		}
		Collections.shuffle(X);
		int [] X_ = new int[subset_size];
		for (int i = 0; i < subset_size; i++) {
			X_[i] = X.get(i);
		}
		return X_;
	}

	
}
