package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FPPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1926276960026845858L;

	/**
	 * Create the panel.
	 */
	public FPPanel() {
		
		setLayout(null);
	
		JComboBox<String> cmbFP = new JComboBox<String>();
		cmbFP.setModel(new DefaultComboBoxModel(new String[] {"Z-Score Normalize", "Select P-Value Significant Features", "Select or Exclude Top K Features"}));
		cmbFP.setBounds(215, 112, 134, 20);
		add(cmbFP);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblType.setBounds(69, 115, 134, 14);
		add(lblType);
	}

}
