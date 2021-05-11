package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {
	public static int SAT = 0;
	public static int NON_SAT = 0;
	public static String benchmarks_sat = "E:\\JAVA JEE\\eclipse-workspace\\Projet_MAE\\benchmarks\\uf75.325.100";
	public static String benchmarks_non_sat = "E:\\JAVA JEE\\eclipse-workspace\\Projet_MAE\\benchmarks\\uuf75.325.100";
	protected int n; // nombre de clauses
	protected int m; // nombre de variables
	
	
	
	public int[][] lire_clauses(int type, int indice) {
		/** type = SAT ou NON_SAT, indice de 1 à 100 (il y a 100 fichiers)*/
		int [][] clauses = null;
		try {
		String ficher_cnf = get_fichier_cnf(type, indice);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ficher_cnf)));
		 // get n and m
		this.get_cnf_file_parameters(reader);
		// allocate clauses matix with n and m
		clauses = init_calauses(n,m);
		String line;
		for (int i = 0; i < n; i++) {
			line = reader.readLine(); if (line == null) break;
			int clause [] = parse_clause_line(line);
			
			if (clause != null)
				for (int j : clause) {
					if (j < 0)
						clauses[i][-j-1] = 0;
					else {
						clauses[i][j-1] = 1;
					}
				}
			else {
				throw new Exception();
			}
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);;
		}
		
		return clauses;
	}
	
	private int[][] init_calauses(int n, int m) {
		int[][] clauses = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) 
				clauses[i][j] = -1;
		return clauses;
	}

	private String get_fichier_cnf(int type, int indice) throws Exception {
		String fichier = "";
		if (type == SAT)
			fichier = benchmarks_sat;
		else if (type == NON_SAT) {
			fichier = benchmarks_non_sat;
		} else {
			throw new Exception("le type doit être soit SAT soit NON_SAT!");
		}
		
		File dir = new File(fichier);
		assert indice > 0 && indice <= 100 : "l'indice doit être entre 1 et 100 (inclus)";
		return fichier + "\\" + dir.list()[indice];

	}

	private int[] parse_clause_line(String line) {
		String[] str_values = line.trim().split(" ");
		int nbr_literaux = str_values.length - 1; // ignores last value — which equals 0
		int [] clause = new int[nbr_literaux];
		for (int i = 0; i < nbr_literaux; i++) { 
			clause[i] = Integer.parseInt(str_values[i]);
		}
		return clause;
	}

	private void get_cnf_file_parameters(BufferedReader reader) throws IOException {
		String line;
		while (true) {
			line = reader.readLine(); if (line == null) break;
			Matcher matcher = match("p\\s+cnf\\s+(\\d+)\\s+(\\d+)", line);
			if (matcher != null) {
				this.m = Integer.parseInt(matcher.group(1));
				this.n = Integer.parseInt(matcher.group(2));
				return;
			}
		}
	}

	public Matcher match(String pattern,String text) {
	    Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(text);
		return matcher.find() ? matcher : null;
	}
}
