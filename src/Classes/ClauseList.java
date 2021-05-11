package Classes;
import java.util.ArrayList;

public class ClauseList extends ArrayList<Clause> {
	protected int n;
	protected int m;
	private static final long serialVersionUID = 1L;
	public ClauseList(int n) {
		super(n);
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}

}
