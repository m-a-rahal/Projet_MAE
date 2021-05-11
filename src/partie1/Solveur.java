package partie1;

import Classes.ClauseList;
import io_classes.FileManager;

public class Solveur {
	
	static String ex_sat = "E:/_Mohamed_/USTHB/M1_SII (REDO)/S2 2021/RC/TP/TP1 - Solveur SAT/programme inference/bc_2.cnf";
	public static void main(String[] args) {
		
		ClauseList clauses = new FileManager().lire_benchmark(FileManager.SAT, 1);
		clauses = new FileManager().read(ex_sat);
		//print(clauses);
 		print(new A_star().
 				resoudre(clauses));
		print(DFS.resoudre(clauses));
		print(BFS.resoudre(clauses));
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
