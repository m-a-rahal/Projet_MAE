package Classes;

import java.util.PriorityQueue;

public class Population extends PriorityQueue<Individus>{
	private static final long serialVersionUID = 1L;
	@Override
	public boolean contains(Object o) {
		Individus ind = (Individus) o;
		for(Individus x : this) 
			if(ind.egale(x)) 
				return true;
		return false;
	}
}
