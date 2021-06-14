package application;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import Classes.ClauseList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Aveugle_panel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JSpinner spinner_nbr_variables;
	private JSpinner spinner_nbr_clauses;
	private JComboBox<String> sat_comboBox;
	private JButton btn_gen_alea;
	private Application_JFrame parent;

	public Aveugle_panel(Application_JFrame parent) {
		this.parent = parent;
		setLayout(null);
		setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		
		sat_comboBox = new JComboBox<String>();
		sat_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"générer un système SAT", "générer un système non-SAT"}));
		sat_comboBox.setBounds(10, 78, 235, 22);
		add(sat_comboBox);
		
		spinner_nbr_variables = new JSpinner();
		spinner_nbr_variables.setModel(new SpinnerNumberModel(new Integer(20), new Integer(1), null, new Integer(1)));
		spinner_nbr_variables.setBounds(142, 11, 103, 23);
		add(spinner_nbr_variables);
		
		JLabel lblNewLabel = new JLabel("nombre de variables");
		lblNewLabel.setBounds(10, 11, 122, 23);
		add(lblNewLabel);
		
		JLabel lblNombreDeClauses = new JLabel("nombre de clauses");
		lblNombreDeClauses.setBounds(10, 44, 122, 23);
		add(lblNombreDeClauses);
		
		spinner_nbr_clauses = new JSpinner();
		spinner_nbr_clauses.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
		spinner_nbr_clauses.setBounds(142, 44, 103, 23);
		add(spinner_nbr_clauses);
		
		btn_gen_alea = new JButton("générer des clauses aléatoirement");
		btn_gen_alea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n  = parent.aveuglePanel.getNbrClauses();
		        int m  = parent.aveuglePanel.getNbrVariables();
		        boolean SAT = parent.aveuglePanel.getSAT();
		        ClauseList clauses = new ClauseList(n,m).gen_aleat(SAT);
				parent.clausesPanel.loadClausesSet(clauses);
			}
		});
		btn_gen_alea.setBackground(new Color(124, 252, 0));
		btn_gen_alea.setBounds(10, 111, 235, 23);
		add(btn_gen_alea);
	}

	public int getNbrVariables() { return Integer.parseInt(spinner_nbr_variables.getValue().toString()); }

	public int getNbrClauses() { return Integer.parseInt(spinner_nbr_clauses.getValue().toString()); }

	public boolean getSAT() {  return sat_comboBox.getSelectedIndex() == 0; /* == 0 veut dire sat, 1 veut dire non sat*/}
}
