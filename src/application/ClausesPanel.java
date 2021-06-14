package application;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.ClauseList;
import io_classes.FileManager;

public class ClausesPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private JTable clausesTable;
	private ClauseList clset;
	public Application_JFrame parent;

	public ClausesPanel(Application_JFrame parent) {
		this.parent = parent;
		setBounds(10, 11, 321, 260);
		setLayout(new BorderLayout(0, 0));

		clausesTable = new JTable();
		clausesTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		clausesTable.setEnabled(false);
		clausesTable.setRowSelectionAllowed(false);
		add(clausesTable, BorderLayout.CENTER);
		add(new JScrollPane(clausesTable));
	}


	public String loadClausesSet(String cnf_filePath) {
		return show_clause_list(new FileManager().read(cnf_filePath), false); // false --> don't sort
	}
	
	public String loadClausesSet(ClauseList clset) {
		return show_clause_list(clset, true); // true --> sort 
	}
	
	private String show_clause_list(ClauseList clset, boolean sort) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("num clause");

		for(int i=0; i<clset.getClauseSize(); i++)
			tableModel.addColumn(""+(i+1));

		String[] tableRow = new String[clset.getClauseSize()+1];

		for(int i=0; i<clset.getN(); i++) {
			tableRow[0] = String.valueOf(i+1);
			ArrayList<Integer> littetrals = new ArrayList<>(clset.get(i));
			if (sort)
				Collections.sort(littetrals, new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return Math.abs(o1) - Math.abs(o2);
					}
				});
			int j = 0;
			for (Integer litterale : littetrals) {
				tableRow[j+1] = String.valueOf(litterale);
				j++;
			}/*
			for(int j=1; j<=clset.getClauseSize(); j++)
				tableRow[j] = String.valueOf(littetrals.get(j-1));*/

			tableModel.addRow(tableRow);
			
			parent.btn_lancer.setEnabled(true);
		}

		clausesTable.setModel(tableModel);

		return "nbr_variables = "+clset.getM()+", nbr_clauses = "+clset.getN();
	}


	public ClauseList getClausesSet() { return clset; }
}
