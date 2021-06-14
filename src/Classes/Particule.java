package Classes;

import algorithmes.PSO;

public class Particule  {
	protected PSO swarm;
	protected Solution solution, pBest;
	protected int vitesse;
	protected int v_max;
	
	public Particule(int m) {
		solution = Solution.gen_alea(m);
		pBest = solution;
		vitesse = (int)(m*Math.random());
	}
	public Particule(int m, PSO swarm) {
		this(m);
		this.swarm = swarm;
	}
	
	
	public void updateVelocity() {
		double internalMove = swarm.poids * vitesse;
		double cognitiveMove = swarm.c1 * Math.random() * pBest.diff(this.solution);
		double socialMove = swarm.c2 * Math.random() * swarm.gbest.diff(this.solution);

		vitesse = ((int) (internalMove + cognitiveMove + socialMove));
		vitesse = Math.min(v_max, vitesse);
	}
}
