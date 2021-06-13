package application;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

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

	public ClausesPanel() {
		setBounds(10, 11, 321, 260);
		setLayout(new BorderLayout(0, 0));

		clausesTable = new JTable();
		clausesTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		clausesTable.setEnabled(false);
		clausesTable.setRowSelectionAllowed(false);
		add(clausesTable, BorderLayout.CENTER);
		add(new JScrollPane(clausesTable));
	}


	public String loadClausesSet(File cnf_filePath) {
		clset =  new FileManager().read(cnf_filePath.getAbsolutePath());

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Clause");

		for(int i=0; i<clset.getClauseSize(); i++)
			tableModel.addColumn("Literal "+(i+1));

		String[] tableRow = new String[clset.getClauseSize()+1];

		for(int i=0; i<clset.getN(); i++) {
			tableRow[0] = String.valueOf(i);
			ArrayList<Integer> littetrals = new ArrayList<>(clset.get(i));
			for(int j=1; j<=clset.getClauseSize(); j++)
				tableRow[j] = String.valueOf(littetrals.get(j-1));

			tableModel.addRow(tableRow);
		}

		clausesTable.setModel(tableModel);

		return "SAT instance loaded :  "+clset.getN()+"  clauses,  "+clset.getM()+"  variables,  "+clset.getClauseSize()+"  variables/clause";
	}


	public ClauseList getClausesSet() { return clset; }
}
