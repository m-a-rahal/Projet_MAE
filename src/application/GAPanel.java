package application;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class GAPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JSpinner populationSizeSpinner;
	private JSpinner crossoverRateSpinner;
	private JSpinner mutationRateSpinner;
	private JSpinner numIterGaSpinner;

	public GAPanel() {
		setLayout(null);
		setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
	
		JLabel populationSizeLabel = new JLabel("Taille de la population");
		populationSizeLabel.setBounds(15, 19, 109, 25);
		add(populationSizeLabel);
		
		populationSizeSpinner = new JSpinner(new SpinnerNumberModel(200, 1, null, 1));
		populationSizeSpinner.setBounds(154, 20, 84, 23);
		add(populationSizeSpinner);

		JLabel crossoverRateLabel = new JLabel("Taux de croisement");
		crossoverRateLabel.setBounds(15, 63, 109, 25);
		add(crossoverRateLabel);

		crossoverRateSpinner = new JSpinner(new SpinnerNumberModel(98, 0, 100, 1));
		crossoverRateSpinner.setBounds(154, 64, 84, 23);
		add(crossoverRateSpinner);

		JLabel mutationRateLabel = new JLabel("Taux de mutation");
		mutationRateLabel.setBounds(15, 107, 99, 25);
		add(mutationRateLabel);
		
		mutationRateSpinner = new JSpinner(new SpinnerNumberModel(40, 0, 100, 1));
		mutationRateSpinner.setBounds(154, 108, 84, 23);
		add(mutationRateSpinner);
		
		JLabel percentageMutationLabel = new JLabel("%");
		percentageMutationLabel.setBounds(179, 107, 17, 25);
		add(percentageMutationLabel);
		
		JLabel numIterGaLabel = new JLabel("Nombre d'itérations");
		numIterGaLabel.setBounds(16, 151, 139, 25);
		add(numIterGaLabel);

		numIterGaSpinner = new JSpinner(new SpinnerNumberModel(new Integer(10000), new Integer(1), null, new Integer(1)));
		numIterGaSpinner.setBounds(154, 152, 84, 23);
		add(numIterGaSpinner);
	}
	
	public int getPopulationSize() { return Integer.parseInt(populationSizeSpinner.getValue().toString()); }
	
	public int getCrossoverRate() { return Integer.parseInt(crossoverRateSpinner.getValue().toString()); }
	
	public int getMutationRate() { return Integer.parseInt(mutationRateSpinner.getValue().toString()); }
	
	public int getnumIterGa() { return Integer.parseInt(numIterGaSpinner.getValue().toString()); }
}
