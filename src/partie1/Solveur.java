package partie1;
import java.util.Collection;
import Classes.ClauseList;
import Classes.Solution;

public abstract class Solveur {
	Collection<Solution> ouvert;
	ClauseList clauses;
	
	
	public Solution resolve(ClauseList clauseList) {
		return null;
	}
	public abstract Solution resolve();
}
