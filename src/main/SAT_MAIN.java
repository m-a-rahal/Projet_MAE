package main;

import Classes.ClauseList;
import io_classes.FileManager;
import io_classes.Tester;
import partie1.A_star;
import partie1.BFS;
import partie1.DFS;

public class SAT_MAIN {
	
	static String ex_sat = "E:/_Mohamed_/USTHB/M1_SII (REDO)/S2 2021/RC/TP/TP1 - Solveur SAT/programme inference/bc_2.cnf";
	public static void main(String[] args) {
		Tester tester = new Tester(300, 47);
		
		/*ClauseList clauseList = new ClauseList(300, 20).gen_aleat(false);
		int i = 0;
		for (Clause clause : clauseList) {
			System.out.println(++i +" - "+clause.toString());
		}
		System.out.println(new A_star().resoudre(clauseList));
		System.exit(0);*/
		
		tester.min_n = 300;
		tester.min_m = 31;
		//ClauseList clauses = new FileManager().lire_benchmark(FileManager.SAT, 10);
		//new A_star().resoudre(clauses);
		//.tester(new BFS(), false);
		//System.out.println("\n\n\n");
		//tester.tester(new DFS());
		//System.out.println("\n\n\n");
		tester.tester(new A_star(), true);
		System.out.println("\n\n\n");
		
		
		String cnf_file = "";
		ClauseList clauses = new FileManager().read(cnf_file);
		long temps_a_star = new A_star().temps_execution(clauses);
		long temps_BFS = new BFS().temps_execution(clauses);
		long temps_DFS = new DFS().temps_execution(clauses);
		
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
