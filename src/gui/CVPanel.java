package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CVPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 176260297317013648L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public CVPanel() {
		setLayout(null);
		
		JLabel lblNumresampleruns = new JLabel("num_resample_runs:");
		lblNumresampleruns.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumresampleruns.setBounds(29, 112, 146, 14);
		add(lblNumresampleruns);
		
		textField = new JTextField();
		textField.setBounds(375, 112, 32, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblTestonlyattrainingtimes = new JLabel("test_only_at_training_times:");
		lblTestonlyattrainingtimes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTestonlyattrainingtimes.setBounds(29, 140, 146, 14);
		add(lblTestonlyattrainingtimes);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(375, 140, 32, 20);
		add(textField_1);
		
		JLabel lblStopresamplerunsonlywhenspecficresultshaveconverged = new JLabel("stop_resample_runs_only_when_specfic_results_have_converged:");
		lblStopresamplerunsonlywhenspecficresultshaveconverged.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStopresamplerunsonlywhenspecficresultshaveconverged.setBounds(29, 167, 323, 17);
		add(lblStopresamplerunsonlywhenspecficresultshaveconverged);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(375, 168, 32, 20);
		add(textField_2);

	}

}
