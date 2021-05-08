package partie1;

import java.util.Arrays;

public class Solveur {
	public static int nbr_clauses = 3;
	public static int nbr_variables = 3;
	int clauses[][] = new int[nbr_clauses][nbr_variables];
	int solution[];
	
	public static void main(String[] args) {
		int clauses[][] = {{ 0, 1, 1}, // -1 veut dire
		           { 0,-1, 1},
		           {-1, 1,-1}};
		
		System.out.println(BFS.resoudre(clauses));
		System.out.println(DFS.resoudre(clauses));
	}
	
	public static int[] solution_vide() {
		int solution [] = new int[nbr_variables];
		Arrays.fill(solution, -2);
		return solution;
	}
}
