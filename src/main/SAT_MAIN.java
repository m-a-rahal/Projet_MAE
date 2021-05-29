package main;

import io_classes.Tester;
import partie1.A_star;
import partie1.BFS;
import partie1.DFS;

public class SAT_MAIN {
	
	static String ex_sat = "E:/_Mohamed_/USTHB/M1_SII (REDO)/S2 2021/RC/TP/TP1 - Solveur SAT/programme inference/bc_2.cnf";
	public static void main(String[] args) {
		Tester tester = new Tester(30, 30);
		tester.min_n = 30;
		//tester.min_m = 21;
		//.tester(new BFS(), false);
		//System.out.println("\n\n\n");
		//tester.tester(new DFS());
		//System.out.println("\n\n\n");
		tester.tester(new A_star());
		System.out.println("\n\n\n");
		
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
