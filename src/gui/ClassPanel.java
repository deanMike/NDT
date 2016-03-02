package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class ClassPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3724640878004320716L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public ClassPanel() {
		setLayout(null);
		
		JLabel lblSvmcScalar = new JLabel("svm.C: scalar:");
		lblSvmcScalar.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSvmcScalar.setBounds(89, 59, 108, 14);
		add(lblSvmcScalar);
		
		textField = new JTextField();
		textField.setBounds(207, 56, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Linear", "Polynomial", "Gaussian"}));
		comboBox.setBounds(207, 87, 86, 20);
		add(comboBox);
		
		JLabel lblSvmkernel = new JLabel("svm.kernel:");
		lblSvmkernel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSvmkernel.setBounds(111, 90, 86, 14);
		add(lblSvmkernel);
		
		JLabel lblSvmpolydegree = new JLabel("svm.poly_degree:");
		lblSvmpolydegree.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSvmpolydegree.setBounds(100, 139, 97, 14);
		add(lblSvmpolydegree);
		
		JLabel lblSvmpolyoffset = new JLabel("svm.poly_offset:");
		lblSvmpolyoffset.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSvmpolyoffset.setBounds(10, 164, 187, 14);
		add(lblSvmpolyoffset);
		
		JLabel lblSvmgaussiangamma = new JLabel("svm.gaussian_gamma:");
		lblSvmgaussiangamma.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSvmgaussiangamma.setBounds(51, 189, 146, 14);
		add(lblSvmgaussiangamma);
		
		textField_1 = new JTextField();
		textField_1.setBounds(207, 136, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(207, 161, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(207, 186, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);

	}

}
