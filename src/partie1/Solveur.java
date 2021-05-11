package partie1;

import Classes.FileManager;

public class Solveur {
	

	public static void main(String[] args) {
		int clauses[][] = new FileManager().lire_clauses(FileManager.SAT, 2);
		print(clauses);
		//print(DFS.resoudre(clauses));
		//print(BFS.resoudre(clauses));
		print(new A_star().resoudre(clauses));
	}
	
	public static void print(Object text) {
		System.out.println(text);
	}
	
	public static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			print("");
		}
	}
}
