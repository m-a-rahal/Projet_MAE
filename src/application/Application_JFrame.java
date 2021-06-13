package application;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application_JFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_fichier_cnf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application_JFrame frame = new Application_JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application_JFrame() {
		setTitle("Solveur SAT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 683, 430);
		contentPane.add(tabbedPane);
		
		JPanel panel_donnes = new JPanel();
		tabbedPane.addTab("Entrées", null, panel_donnes, null);
		panel_donnes.setLayout(null);
		
		JPanel clausesPanel = new JPanel();
		clausesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		clausesPanel.setBounds(340, 11, 328, 258);
		panel_donnes.add(clausesPanel);
		
		JLabel algo_choix_label = new JLabel("Algorithme de recherche");
		algo_choix_label.setBounds(10, 11, 320, 19);
		panel_donnes.add(algo_choix_label);
	
		
		JPanel options_panel = new JPanel();
		options_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		options_panel.setBounds(10, 74, 320, 195);
		panel_donnes.add(options_panel);
		
		GAPanel gaOption = new GAPanel();
		PSOPanel psoOption = new PSOPanel();
		options_panel.setLayout(new CardLayout(0, 0));
		JPanel defaultOption = new JPanel();

		options_panel.add(defaultOption, "default");
		options_panel.add(gaOption, "ga");
		options_panel.add(psoOption, "pso");

		
		JLabel label_file_cnf = new JLabel("Fichier cnf");
		label_file_cnf.setBounds(340, 280, 54, 19);
		panel_donnes.add(label_file_cnf);
		
		textField_fichier_cnf = new JTextField();
		textField_fichier_cnf.setBounds(395, 279, 190, 20);
		panel_donnes.add(textField_fichier_cnf);
		textField_fichier_cnf.setColumns(10);
		
		JButton btn_choisir = new JButton("choisir");
		btn_choisir.setBounds(595, 278, 73, 23);
		panel_donnes.add(btn_choisir);
		
		JLabel lblNewLabel = new JLabel("temps limite par essais (secondes)");
		lblNewLabel.setBounds(10, 303, 172, 19);
		panel_donnes.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre d'essais");
		lblNewLabel_1.setBounds(10, 280, 172, 19);
		panel_donnes.add(lblNewLabel_1);
		
		JSpinner spinner_nbr_essais = new JSpinner();
		spinner_nbr_essais.setModel(new SpinnerNumberModel(3, 1, null, 1));
		spinner_nbr_essais.setBounds(192, 279, 138, 20);
		panel_donnes.add(spinner_nbr_essais);
		
		JSpinner spinner_temps_max = new JSpinner();
		spinner_temps_max.setModel(new SpinnerNumberModel(2, 1, null, 1));
		spinner_temps_max.setBounds(192, 302, 138, 20);
		panel_donnes.add(spinner_temps_max);
		
		JButton btn_lancer = new JButton("New button");
		btn_lancer.setBounds(10, 333, 145, 29);
		panel_donnes.add(btn_lancer);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) options_panel.getLayout();
				System.out.println(comboBox.getSelectedIndex());
				switch(comboBox.getSelectedIndex()) {
					case 3:
						cardLayout.show(options_panel, "ga");
						break;
					case 4:
						cardLayout.show(options_panel, "pso");
						break;
					default:
						cardLayout.show(options_panel, "default");
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Recherche en largeur d'abord (BFS)", "Recherche en profondeur d'abord (DFS)", "Recherche A*", "Algorithme PSO", "Algorithme génétique"}));
		comboBox.setBounds(10, 41, 320, 22);
		panel_donnes.add(comboBox);
		
		ResultPanel panel_graphe = new ResultPanel();
		tabbedPane.addTab("Résultats", null, panel_graphe, null);
		//tabbedPane.setEnabledAt(1, false); // ########################################## enable this when results come
	}
}
