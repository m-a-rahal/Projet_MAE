package algorithmes;

import Classes.ClauseList;
import Classes.Solution;

import java.util.Queue;

public abstract class Solveur_SAT {
	Queue<Solution> ouvert;
	public ClauseList clauses;
	
	
	public Solution resoudre(ClauseList clauseList) { /** cette met*/
		clauses = clauseList;
		long t0 = System.currentTimeMillis();
		Solution solution =  solve();
		System.out.println("\n> "+getClass().getSimpleName() + ", temps exec = "+ (System.currentTimeMillis() - t0)+" ms");
		return solution;
	}
	
	public Solution resoudre(ClauseList clauseList, long temps_max) { /** cette met*/
		clauses = clauseList;
		return solve(temps_max);
	}

	public long temps_execution(ClauseList clauseList) { /** cette met*/
		clauses = clauseList;
		long t0 = System.currentTimeMillis();
		solve();
		//System.out.println("\n> "+getClass().getSimpleName() + ", temps exec = "+ (System.currentTimeMillis() - t0)+" ms");
		return  (System.currentTimeMillis() - t0)/1000;
	}

	public abstract Solution solve();
	public Solution solve(ClauseList clauseList) {
		clauses = clauseList;
		return solve();
	}
	
	protected abstract Solution solve(long temps_max);
}
