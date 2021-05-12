package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Clause extends TreeSet<Integer>{
	private static final long serialVersionUID = 1L;

	public Clause completer_aleatoirement(int taille, int nbr_variables) {
		if (taille + this.size() > nbr_variables)
			throw new IndexOutOfBoundsException("ne peut remplir une clause avec une taille = "+(taille + this.size())+" > nbr variables = "+nbr_variables);
		
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
}
