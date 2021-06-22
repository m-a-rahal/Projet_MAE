package algorithmes;

import Classes.ClauseList;
import Classes.Solution;
import Classes.Solution.TimedSolution;

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
	
	public TimedSolution resoudre(ClauseList clauseList, long temps_max) { /** cette met*/
		clauses = clauseList;
		long t0 = System.currentTimeMillis();
		TimedSolution solution = new TimedSolution(solve(temps_max));
		solution.time = (System.currentTimeMillis() - t0);
		solution.clauses = clauses;
		return solution;
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
