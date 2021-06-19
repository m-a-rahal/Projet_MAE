package application;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Classes.ClauseList;
import algorithmes.A_star;
import algorithmes.BFS;
import algorithmes.DFS;
import algorithmes.GA;
import algorithmes.PSO;
import java.awt.FlowLayout;

public class Application_JFrame extends JFrame {
	public static final long serialVersionUID = 1L;
	/*Recherche en largeur d'abord (BFS)
Recherche en profondeur d'abord (DFS)
Recherche A*
Algorithme PSO
Algorithme génétique
	 * */
	public static final int BFS_INDEX=0, DFS_INDEX=1, A_STAR_INDEX=2, PSO_INDEX=3, GA_INDEX=4;
	public JPanel contentPane;
	public JTextField textField_fichier_cnf;
	public File file = null;
	public JButton btn_lancer;
	public Aveugle_panel aveuglePanel;
	public ClausesPanel clausesPanel;
	public JTextField textField_solution;
	//protected ClauseList clauses;

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
		setBounds(100, 100, 699, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 683, 487);
		contentPane.add(tabbedPane);
		
		JPanel panel_donnes = new JPanel();
		tabbedPane.addTab("Entrées", null, panel_donnes, null);
		panel_donnes.setLayout(null);
		
		clausesPanel = new ClausesPanel(this);
		clausesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		clausesPanel.setBounds(340, 41, 328, 257);
		panel_donnes.add(clausesPanel);
		
		JLabel algo_choix_label = new JLabel("Algorithme de recherche");
		algo_choix_label.setBounds(10, 11, 320, 19);
		panel_donnes.add(algo_choix_label);
	
		
		JPanel options_panel = new JPanel();
		options_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		options_panel.setBounds(10, 74, 320, 224);
		panel_donnes.add(options_panel);
		options_panel.setLayout(new CardLayout(0, 0));
		
		GAPanel gaOption = new GAPanel();
		PSOPanel psoOption = new PSOPanel();
		JPanel defaultOption = new JPanel();
		aveuglePanel = new Aveugle_panel(this);

		options_panel.add(defaultOption, "default");
		options_panel.add(gaOption, "ga");
		options_panel.add(psoOption, "pso");
		options_panel.add(aveuglePanel, "aveugle");

		
		JLabel label_file_cnf = new JLabel("Fichier cnf");
		label_file_cnf.setBounds(340, 309, 54, 19);
		panel_donnes.add(label_file_cnf);
		
		textField_fichier_cnf = new JTextField();
		textField_fichier_cnf.setBounds(395, 308, 190, 20);
		panel_donnes.add(textField_fichier_cnf);
		textField_fichier_cnf.setColumns(10);
		
		JLabel information_label = new JLabel("");
		information_label.setBounds(340, 3, 328, 35);
		panel_donnes.add(information_label);
		
		
		JButton btn_choisir = new JButton("choisir");
		btn_choisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Conjunctive Normal Form (.cnf)", "cnf"));
				fileChooser.showOpenDialog(null);

				try {
					String file_path = fileChooser.getSelectedFile().getAbsolutePath();	
					textField_fichier_cnf.setText(file_path);
					information_label.setText(clausesPanel.loadClausesSet(file_path));
				} catch (NullPointerException ignore) {}
			}
		});
		btn_choisir.setBounds(595, 307, 73, 23);
		panel_donnes.add(btn_choisir);
		
		JLabel lblNewLabel = new JLabel("temps limite par essais (secondes)");
		lblNewLabel.setBounds(10, 332, 172, 19);
		panel_donnes.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre d'essais");
		lblNewLabel_1.setBounds(10, 309, 172, 19);
		panel_donnes.add(lblNewLabel_1);
		
		JSpinner spinner_nbr_essais = new JSpinner();
		spinner_nbr_essais.setModel(new SpinnerNumberModel(4, 1, null, 1));
		spinner_nbr_essais.setBounds(192, 308, 138, 20);
		panel_donnes.add(spinner_nbr_essais);
		
		JSpinner spinner_temps_max = new JSpinner();
		spinner_temps_max.setModel(new SpinnerNumberModel(new Double(0.5), new Double(0.1), null, new Double(0.1)));
		spinner_temps_max.setBounds(192, 331, 138, 20);
		panel_donnes.add(spinner_temps_max);

		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) options_panel.getLayout();
				switch(comboBox.getSelectedIndex()) {
					case BFS_INDEX:
						cardLayout.show(options_panel, "aveugle");
						break;
					case DFS_INDEX:
						cardLayout.show(options_panel, "aveugle");
						break;
					case A_STAR_INDEX:
						cardLayout.show(options_panel, "aveugle");
						break;
					case PSO_INDEX:
						cardLayout.show(options_panel, "pso");
						break;
					case GA_INDEX:
						cardLayout.show(options_panel, "ga");
						break;
					default:
						cardLayout.show(options_panel, "default");
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Recherche en largeur d'abord (BFS)", "Recherche en profondeur d'abord (DFS)", "Recherche A*", "Algorithme PSO", "Algorithme génétique"}));
		comboBox.setSelectedIndex(3);
		comboBox.setBounds(10, 41, 320, 22);
		panel_donnes.add(comboBox);
		
		JPanel panel_resultats = new JPanel();
		
		tabbedPane.addTab("Résultats", null, panel_resultats, null);
		panel_resultats.setLayout(null);
		
		ResultPanel panel_graphe = new ResultPanel(this);
		panel_graphe.setBounds(0, 0, 678, 421);
		panel_resultats.add(panel_graphe);
		
		JLabel lblNewLabel_2 = new JLabel("Solution");
		lblNewLabel_2.setBounds(10, 432, 46, 14);
		panel_resultats.add(lblNewLabel_2);
		
		textField_solution = new JTextField();
		textField_solution.setBounds(66, 429, 612, 20);
		panel_resultats.add(textField_solution);
		textField_solution.setColumns(10);
		//tabbedPane.setEnabledAt(1, false); // ########################################## enable this when results come
		btn_lancer = new JButton("Lancer le test");
		btn_lancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panel_graphe.clearData();

				long startResolution;
				long temps_max = (long)(Double.parseDouble(spinner_temps_max.getValue().toString())*1000);
				ClauseList clauses = clausesPanel.getClauses();
				String methodName = "";

				panel_graphe.setUpperBound(clauses.getN());

				switch(comboBox.getSelectedIndex()) {
					case DFS_INDEX:
						//informationLabel.setText("SAT instance resolved using \"Depth-First Search (DFS)\"");
						methodName = "DFS";

						for(int i=0; i< Integer.parseInt(spinner_nbr_essais.getValue().toString()); i++) {
							startResolution = System.currentTimeMillis();
							panel_graphe.addData(clauses, new DFS().resoudre(clauses, temps_max),
												System.currentTimeMillis() - startResolution > temps_max ? temps_max : System.currentTimeMillis() - startResolution, i+1);
						}

						break;

					case BFS_INDEX:
						//informationLabel.setText("SAT instance resolved using \"Breadth-First Search (BFS)\"");
						methodName = "BFS";

						for(int i=0; i< Integer.parseInt(spinner_nbr_essais.getValue().toString()); i++) {
							startResolution = System.currentTimeMillis();
							panel_graphe.addData(clauses, new BFS().resoudre(clauses, temps_max),
												System.currentTimeMillis() - startResolution > temps_max ? temps_max : System.currentTimeMillis() - startResolution, i+1);
						}

						break;

					case A_STAR_INDEX:
						/* "heuristicOptions" was disabled until other heuristics will be added */
						// informationLabel.setText("SAT instance resolved using \"Heuristic "+heuristicOption.getSelectedHeuristicRadio()+"\"");
						//informationLabel.setText("SAT instance resolved using \"Heuristic search (A*)\"");
						methodName = "A*";

						for(int i=0; i< Integer.parseInt(spinner_nbr_essais.getValue().toString()); i++) {
							startResolution = System.currentTimeMillis();
							panel_graphe.addData(clauses, new A_star().resoudre(clauses, temps_max),
									System.currentTimeMillis() - startResolution > temps_max ? temps_max : System.currentTimeMillis() - startResolution, i+1);
						}

						break;

					case GA_INDEX:
						//informationLabel.setText("SAT instance resolved using \"Genetic Algorithm (GA)\"");
						methodName = "GA";

						for(int i=0; i< Integer.parseInt(spinner_nbr_essais.getValue().toString()); i++) {
							startResolution = System.currentTimeMillis();
							panel_graphe.addData(clauses, new GA(gaOption.getPopulationSize(), gaOption.getCrossoverRate(), gaOption.getMutationRate(),
									gaOption.getnumIterGa()).resoudre(clauses, temps_max),
												System.currentTimeMillis() - startResolution > temps_max ? temps_max : System.currentTimeMillis() - startResolution, i+1);
						}

						break;

					case PSO_INDEX:
						//informationLabel.setText("SAT instance resolved using \"Particle Swarm Optimization (PSO)\"");
						methodName = "PSO";

						for(int i=0; i< Integer.parseInt(spinner_nbr_essais.getValue().toString()); i++) {
							startResolution = System.currentTimeMillis();
							panel_graphe.addData(clauses, new PSO(psoOption.getNumParticles(), psoOption.getConstant1(),
									psoOption.getConstant2(), psoOption.getInWeight(), psoOption.getNumIterPso()).resoudre(clauses, temps_max),
												System.currentTimeMillis() - startResolution > temps_max ? temps_max : System.currentTimeMillis() - startResolution, i+1);
						}
				}

				panel_graphe.makeTitle(methodName);

				tabbedPane.setEnabledAt(1, true);
			}
		});
		btn_lancer.setBounds(10, 362, 145, 29);
		btn_lancer.setEnabled(false);
		panel_donnes.add(btn_lancer);

	}
}
