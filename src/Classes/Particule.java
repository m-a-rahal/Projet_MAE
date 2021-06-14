package Classes;

import java.util.ArrayList;
import java.util.Collections;

import algorithmes.PSO;

public class Particule  {
	protected PSO swarm;
	protected Solution solution, pBest;
	protected int vitesse;
	
	public Particule(PSO swarm) {
		solution = Solution.gen_alea(swarm.clauses.getM());
		solution.setF(solution.sat_count(swarm.clauses));
		pBest = solution;
		vitesse = (int)(solution.size()*Math.random());
		this.swarm = swarm;
	}
	
	
	public void updateVitesse() {
		double internalMove = swarm.poids * vitesse;
		double cognitiveMove = swarm.c1 * Math.random() * pBest.diff(solution);
		double socialMove = swarm.c2 * Math.random() * swarm.gbest.diff(solution);

		vitesse = ((int) (internalMove + cognitiveMove + socialMove));
		vitesse = Math.min(solution.size(), vitesse); // vitesse max = m
	}
	
	public void updatePos() {
		ArrayList<Integer> ouvert = new ArrayList<Integer>(solution.size());
		for(int i=0; i < solution.size(); i++)			
			ouvert.add(i);

		Collections.shuffle(ouvert);
		for(int i=0; i< vitesse; i++)
			solution.flip(ouvert.remove(ouvert.size()-1));
		
		solution.setF(solution.sat_count(swarm.clauses));
	}
	
	public void updatePbest() {
		if(solution.getF() > pBest.getF())
			pBest = solution;
	}
	
	public int getF() {
		return solution.getF();
	}

	public Solution getSolution() {
		return solution;
	}
}
