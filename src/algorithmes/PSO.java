package algorithmes;

import java.util.ArrayList;

import Classes.Particule;
import Classes.Solution;

public class PSO extends Solveur_SAT {
	public int N, max_iter;
	public double c1, c2, poids;
	public Solution gbest;

	public PSO(int N, double c1, double c2, double poids, int max_iter) {
		this.N = N;
		this.c1 = c1;
		this.c2 = c2;
		this.poids = poids;
		this.max_iter = max_iter;
	}

	@Override
	public Solution solve() {
		return solve(Long.MAX_VALUE);
	}

	@Override
	public Solution solve(long temps_max) {
		ArrayList<Particule> particles = new ArrayList<Particule>();
		long t0 = System.currentTimeMillis(); /* Save the start time of the search */

		if(N == 0) return null;
		particles.add(new Particule(this));
		gbest = particles.get(0).getSolution();
		for(int i=1; i < N; i++) {
			particles.add(new Particule(this));
			if(gbest.getF() < particles.get(i).getF());
				gbest = particles.get(i).getSolution();
		}
		
		ArrayList<Integer> positions = new ArrayList<Integer>(clauses.getM());
		for(int i=0; i < clauses.getM(); i++)			
			positions.add(i);
		
		for(int i=0; i< max_iter; i++) {
			if((System.currentTimeMillis() - t0) >= temps_max)
				break; /* If the search time has reached (or exceeded) the allowed run time, finish the search */

			for(Particule particle : particles) {
				particle.updateVitesse();
				particle.updatePos(positions);
				particle.updatePbest();
			}

			for(Particule particle : particles)
				if(gbest.getF() < particle.getF())
					gbest = particle.getSolution();

			if(gbest.getF() == clauses.getN())
				break;
		}

		return gbest;
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%f\t%f\t%f", N, c1, c2, poids);
	}

}
