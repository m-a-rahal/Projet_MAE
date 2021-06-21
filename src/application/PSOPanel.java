package application;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class PSOPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    private JSpinner numParticlesSpinner;
    private JSpinner constant1Spinner;
    private JSpinner constant2Spinner;
    private JSpinner inWeightSpinner;
    private JSpinner numIterPsoSpinner;
    private JLabel lblConstante_4;
    private JLabel lblPoids;
    private JLabel lblNombreDitrations;

    public PSOPanel() {
        setLayout(null);
        setBorder(new LineBorder(new Color(128, 128, 128), 2, true));

        Double step = new Double(0.1);
        Double inital_c = new Double(0.2);
        Double inital_w = new Double(0.4);
        numParticlesSpinner = new JSpinner(new SpinnerNumberModel(new Integer(50), new Integer(1), null, new Integer(1)));
        numParticlesSpinner.setBounds(149, 13, 79, 23);
        add(numParticlesSpinner);

        constant1Spinner = new JSpinner(new SpinnerNumberModel(inital_c, 0.0, null, step));
        constant1Spinner.setBounds(149, 48, 79, 23);
        add(constant1Spinner);

        constant2Spinner = new JSpinner(new SpinnerNumberModel(inital_c, 0.0, null, step));
        constant2Spinner.setBounds(149, 82, 79, 23);
        add(constant2Spinner);

        inWeightSpinner = new JSpinner(new SpinnerNumberModel(inital_w, new Double(0), new Double(1), step));
        inWeightSpinner.setBounds(149, 116, 79, 23);
        add(inWeightSpinner);

        numIterPsoSpinner = new JSpinner(new SpinnerNumberModel(new Integer(10000), new Integer(1), null, new Integer(1)));
        numIterPsoSpinner.setBounds(149, 150, 79, 23);
        add(numIterPsoSpinner);
        
        JLabel lblNewLabel = new JLabel("Nombre de particuls");
        lblNewLabel.setBounds(15, 17, 125, 14);
        add(lblNewLabel);
        
        JLabel lblConstante = new JLabel("Constante 1");
        lblConstante.setBounds(14, 52, 125, 14);
        add(lblConstante);
        
        lblConstante_4 = new JLabel("Constante 2");
        lblConstante_4.setBounds(15, 86, 125, 14);
        add(lblConstante_4);
        
        lblPoids = new JLabel("Poids");
        lblPoids.setBounds(15, 120, 125, 14);
        add(lblPoids);
        
        lblNombreDitrations = new JLabel("Nombre d'itérations");
        lblNombreDitrations.setBounds(14, 154, 125, 14);
        add(lblNombreDitrations);
    }

    public int getNumParticles() { return Integer.parseInt(numParticlesSpinner.getValue().toString()); }

    public double getConstant1() { return Double.parseDouble(constant1Spinner.getValue().toString()); }

    public double getConstant2() { return Double.parseDouble(constant2Spinner.getValue().toString()); }

    public double getInWeight() { return Double.parseDouble(inWeightSpinner.getValue().toString()); }

    public int getNumIterPso() { return Integer.parseInt(numIterPsoSpinner.getValue().toString()); }
}
