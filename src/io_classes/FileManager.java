package io_classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classes.Clause;
import Classes.ClauseList;

public class FileManager {
	public static int SAT = 0;
	public static int NON_SAT = 1;
	public static String benchmarks_sat = "E:\\JAVA JEE\\eclipse-workspace\\Projet_MAE\\benchmarks\\uf75.325.100";
	public static String benchmarks_non_sat = "E:\\JAVA JEE\\eclipse-workspace\\Projet_MAE\\benchmarks\\uuf75.325.100";
	protected int n; // nombre de clauses
	protected int m; // nombre de variables
	
	
	
	public ClauseList lire_benchmark(int type, int indice) {
		if (indice > 0) indice--; // les indices sont de 1 a 100
		/** type = SAT ou NON_SAT, indice de 1 à 100 (il y a 100 fichiers)*/
		
		try {
		String ficher_cnf = get_fichier_cnf(type, indice);
		return read(ficher_cnf);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);;
		}
		return null;
	}
	
	public ClauseList read(String ficher_cnf) {
		try {
			ClauseList clauses = null;
			System.out.println("reading from file:\n"+ficher_cnf);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ficher_cnf)));
			 // get n and m
			this.get_cnf_file_parameters(reader);
			// allocate clauses matix with n and m
			clauses = init_calauses(n,m);
			String line;
			for (int i = 0; i < n; i++) {
				line = reader.readLine(); if (line == null) break;
				clauses.add(parse_clause_line(line));
			}
			return clauses;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);;
		}
		return null;
	}
	
	private ClauseList init_calauses(int n, int m) {
		ClauseList clauses = new ClauseList(n,m);
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
		ArrayList<IndexedFile> files = new ArrayList<IndexedFile>();
		for (String path : dir.list()) {
			files.add(new IndexedFile(path));
		}
		Collections.sort(files);
		return fichier + "\\" + files.get(indice);

	}

	private Clause parse_clause_line(String line) {
		String[] str_values = line.trim().split(" ");
		int nbr_literaux = str_values.length - 1; // ignores last value — which equals 0
		Clause clause = new Clause();
		for (int i = 0; i < nbr_literaux; i++) { 
			clause.add(Integer.parseInt(str_values[i]));
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

	public static Matcher match(String pattern,String text) {
	    Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(text);
		return matcher.find() ? matcher : null;
	}
}
