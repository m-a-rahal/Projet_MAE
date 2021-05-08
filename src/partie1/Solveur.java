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
		int i=0,j=1,k=0;
		int solution[] = {i,j,k};
		System.out.println(String.format("sol = [%d,%d,%d] --> %s", i,j,k, appliquer(solution, clauses)));
	}
	
	public static boolean appliquer(int solution[], int clauses[][]) {
		int i,j;
		for (i = 0; i < nbr_clauses; i++) {
			for (j = 0; j < nbr_variables; j++) {
				if (solution[j] == clauses[i][j])
					break; // si un litteral est satisfait, passer a la prochaine clause
			}
			if (j == nbr_variables) return false; // si aucun litterale n'est satisfait, la clause est non SAT --> les clauses sont non SAT
		}
		return true; // a ce point, tout les clauses sont SAT
	}
	
	public static int[] solution_vide() {
		int solution [] = new int[nbr_variables];
		Arrays.fill(solution, -2);
		return solution;
	}
	
}
