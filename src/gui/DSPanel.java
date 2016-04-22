package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.JTextField;

public class DSPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6245778621101933769L;
	private JTextField txtNumtimestorepeateachlabelpercvsplit;
	private JTextField txtLabelnamestouse;
	private JTextField txtSitestouse;
	private JTextField txtNumresamplesites;
	private JTextField txtTimeperiodstogetdatafrom;
	private JTextField txtSitestoexclude;
	private JTextField txtRandomlyshufflelabelsbeforerunning;
	private JTextField txtUseuniquedataineachcvsplit;

	private boolean isBasic;
	
	public void dsInitialize() {
			txtUseuniquedataineachcvsplit.setEnabled(!isBasic);
			
			
	}
	
	/**
	 * Create the panel.
	 */

	public DSPanel() {
			
		setLayout(null);
		
		JLabel lblCreateSimRecPop = new JLabel("Create Simultaneously Recorded Populations:");
		lblCreateSimRecPop.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCreateSimRecPop.setBounds(54, 49, 276, 14);
		add(lblCreateSimRecPop);
		
		JCheckBox chkCreateSimRecPop = new JCheckBox("");
		chkCreateSimRecPop.setBounds(336, 46, 21, 14);
		add(chkCreateSimRecPop);
	
		JComboBox<String> cmbType = new JComboBox<String>();
		cmbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Basic", "Generalization"}));
		cmbType.setBounds(340, 18, 79, 20);
		add(cmbType);
		cmbType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dsInitialize();
			}
		});
		
		JLabel lblType = new JLabel("Type:");
		lblType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblType.setBounds(144, 24, 186, 14);
		add(lblType);
		
		JLabel lblSampleSitesWithReplacement = new JLabel("Sample Sites With Replacement:");
		lblSampleSitesWithReplacement.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSampleSitesWithReplacement.setBounds(54, 70, 276, 14);
		add(lblSampleSitesWithReplacement);
		
		JCheckBox chkSampleSitesWithReplacement = new JCheckBox("");
		chkSampleSitesWithReplacement.setBounds(336, 67, 21, 14);
		add(chkSampleSitesWithReplacement);
		
		JLabel lblNumresamplesites = new JLabel("num_resample_sites:");
		lblNumresamplesites.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumresamplesites.setBounds(54, 143, 276, 14);
		add(lblNumresamplesites);
		
		JLabel lblLabelnamestouse = new JLabel("label_names_to_use:");
		lblLabelnamestouse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLabelnamestouse.setBounds(54, 118, 276, 14);
		add(lblLabelnamestouse);
		
		JLabel lblSitestoexclude = new JLabel("sites_to_exclude:");
		lblSitestoexclude.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSitestoexclude.setBounds(54, 191, 276, 14);
		add(lblSitestoexclude);
		
		JLabel lblSitestouse = new JLabel("sites_to_use:");
		lblSitestouse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSitestouse.setBounds(54, 166, 276, 14);
		add(lblSitestouse);
		
		JLabel lblRandomlyshufflelabelsbeforerunning = new JLabel("randomly_shuffle_labels_before_running:");
		lblRandomlyshufflelabelsbeforerunning.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRandomlyshufflelabelsbeforerunning.setBounds(54, 241, 276, 14);
		add(lblRandomlyshufflelabelsbeforerunning);
		
		JLabel lblTimeperiodstogetdatafrom = new JLabel("time_periods_to_get_data_from:");
		lblTimeperiodstogetdatafrom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTimeperiodstogetdatafrom.setBounds(54, 216, 276, 14);
		add(lblTimeperiodstogetdatafrom);
		
		JLabel lblNumtimestorepeateachlabelpercvsplit = new JLabel("num_times_to_repeat_each_label_per_cv_split:");
		lblNumtimestorepeateachlabelpercvsplit.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumtimestorepeateachlabelpercvsplit.setBounds(10, 94, 320, 14);
		add(lblNumtimestorepeateachlabelpercvsplit);
		
		txtNumtimestorepeateachlabelpercvsplit = new JTextField();
		txtNumtimestorepeateachlabelpercvsplit.setBounds(336, 88, 86, 20);
		add(txtNumtimestorepeateachlabelpercvsplit);
		txtNumtimestorepeateachlabelpercvsplit.setColumns(10);
		
		txtLabelnamestouse = new JTextField();
		txtLabelnamestouse.setColumns(10);
		txtLabelnamestouse.setBounds(336, 112, 86, 20);
		add(txtLabelnamestouse);
		
		txtSitestouse = new JTextField();
		txtSitestouse.setColumns(10);
		txtSitestouse.setBounds(336, 160, 86, 20);
		add(txtSitestouse);
		
		txtNumresamplesites = new JTextField();
		txtNumresamplesites.setColumns(10);
		txtNumresamplesites.setBounds(336, 137, 86, 20);
		add(txtNumresamplesites);
		
		txtTimeperiodstogetdatafrom = new JTextField();
		txtTimeperiodstogetdatafrom.setColumns(10);
		txtTimeperiodstogetdatafrom.setBounds(336, 210, 86, 20);
		add(txtTimeperiodstogetdatafrom);
		
		txtSitestoexclude = new JTextField();
		txtSitestoexclude.setColumns(10);
		txtSitestoexclude.setBounds(336, 186, 86, 20);
		add(txtSitestoexclude);
		
		txtRandomlyshufflelabelsbeforerunning = new JTextField();
		txtRandomlyshufflelabelsbeforerunning.setColumns(10);
		txtRandomlyshufflelabelsbeforerunning.setBounds(336, 235, 86, 20);
		add(txtRandomlyshufflelabelsbeforerunning);
		
		JLabel lblUseuniquedataineachcvsplit = new JLabel("use_unique_data_in_each_CV_split:");
		lblUseuniquedataineachcvsplit.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUseuniquedataineachcvsplit.setBounds(54, 265, 276, 14);
		add(lblUseuniquedataineachcvsplit);
		
		txtUseuniquedataineachcvsplit = new JTextField();
		txtUseuniquedataineachcvsplit.setColumns(10);
		txtUseuniquedataineachcvsplit.setBounds(336, 259, 86, 20);
		add(txtUseuniquedataineachcvsplit);
		
		
	}

}
