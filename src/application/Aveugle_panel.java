package application;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class Aveugle_panel extends JPanel{
	private static final long serialVersionUID = 1L;

	public Aveugle_panel() {
		setLayout(null);
		setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
	}

	public int getNumParticles() { return Integer.parseInt(numParticlesSpinner.getValue().toString()); }

	public int getConstant1() { return Integer.parseInt(constant1Spinner.getValue().toString()); }

	public int getConstant2() { return Integer.parseInt(constant2Spinner.getValue().toString()); }

	public int getInWeight() { return Integer.parseInt(inWeightSpinner.getValue().toString()); }

	public int getNumIterPso() { return Integer.parseInt(numIterPsoSpinner.getValue().toString()); }
}
