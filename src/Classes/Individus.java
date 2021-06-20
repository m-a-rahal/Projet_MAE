package Classes;

public class Individus extends Solution {
	private static final long serialVersionUID = 1L;

	public Individus(ClauseList clauses) {
		self_gen_alea(clauses.getM());
		this.fitness = sat_count(clauses);
	}

	public Individus(Solution individu) {
		super(individu);
		this.fitness = individu.fitness;
	}


	public void muter(ClauseList clauses, Integer remove) {
		// TODO Auto-generated method stub
		
	}
}
