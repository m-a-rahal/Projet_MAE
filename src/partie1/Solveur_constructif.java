package partie1;
import java.util.Queue;

import Classes.ClauseList;
import Classes.Solution;

public abstract class Solveur_constructif {
	Queue<Solution> ouvert;
	ClauseList clauses;
	
	
	public Solution resoudre(ClauseList clauseList) { /** cette met*/
		clauses = clauseList;
		long t0 = System.currentTimeMillis();
		Solution solution =  solve();
		System.out.println("\n> "+getClass().getSimpleName() + ", temps exec = "+ (System.currentTimeMillis() - t0)+" ms");
		return solution;
	}
	public abstract Solution solve();
	public Solution solve(ClauseList clauseList) {
		clauses = clauseList;
		return solve();
	}
	
}
