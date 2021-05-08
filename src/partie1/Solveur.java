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

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					int solution[] = {i,j,k};
					System.out.println(String.format("sol = [%d,%d,%d] --> %s", i,j,k, appliquer(solution, clauses)));
				}
			}
		}
		System.out.println(satisfies_clause(new int[] {-2,-2,1}, new int[] {0,1,1}));
	}
	
	public static boolean appliquer(int solution[], int clauses[][]) {
		int i,j;
		for (i = 0; i < nbr_clauses; i++) {
			if(!satisfies_clause(solution, clauses[i])) {
				return false; // si une clause n'est pas SAT, le systeme n'est pas SAT aussi
			}
		}
		return true; // a ce point, tout les clauses sont SAT
	}
	
	public static int[] solution_vide() {
		int solution [] = new int[nbr_variables];
		Arrays.fill(solution, -2);
		return solution;
	}
	
	public static boolean satisfies_clause(int solution[], int clause[]) {
		// solution size must to be <= clause size (solution size < clause in case the solution is incomplete for example)
		for (int i = 0; i < solution.length; i++) {
			if (solution[i] == clause[i]) {
				return true;
			}
		}
		return false;
	}
}
