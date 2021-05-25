package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Clause extends TreeSet<Integer>{
	private static final long serialVersionUID = 1L;

	public Clause(Clause clause) {
		super(clause);
	}
	public Clause() {}

	public Clause completer_aleatoirement(int taille, int nbr_variables) {
		
		/** remplis la clause avec des nombres aléatoires des variables non déja pris  */
		ArrayList<Integer> non_deja_pris = new ArrayList<Integer>();
		for (int i = 1; i <= nbr_variables; i++) {
			if (!this.contains(i) && !this.contains(-i)) {
				non_deja_pris.add(i);
			}
		}
		Collections.shuffle(non_deja_pris);
		for (int i = 0; i < taille; i++) {
			if (Math.random() > 0.5) {
				this.add(non_deja_pris.get(i));
			} else {
				this.add(-non_deja_pris.get(i));
			}
		}
		return this;
	}
	
	public Clause extend(int x) {
		/** retourne une nouvelle solution etendue en rajoutant l'instatioation donnée */
		Clause new_clause = new Clause(this);
		new_clause.add(x);
		return new_clause;
	}
}
