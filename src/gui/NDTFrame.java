package gui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import matlab.MatlabHelper;
import matlabcontrol.MatlabInvocationException;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NDTFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5793192711535285378L;
	private JPanel contentPane;
	private JTextField datasetPath;
	private JTextField binValue;
	private JTextField stepValue;
	
	private int binWidth;
	private int stepSize;
	private String dataFolderName;
	
	private MatlabHelper matlab;

	/**
	 * Launch the application.
	 */
	
	public void setBinWidth(String bw) throws NumberFormatException {
		this.binWidth = Integer.parseInt(bw);
	}
	
	public void setStepSize(String ss) throws NumberFormatException {
		this.stepSize = Integer.parseInt(ss);
	}
	
	public void setDataFolderName(String s) {
		this.dataFolderName = s;
	}
	
	public int getBinWidth() {
		return this.binWidth;
	}
	
	public int getStepSize() {
		return this.stepSize;
	}
	
	public String getDataFolderName() {
		return this.dataFolderName;
	}
	
	private void binDataButton() {
		setBinWidth(binValue.getText());
		setStepSize(stepValue.getText());
		setDataFolderName(datasetPath.getText());
		try {
			System.out.println("TRY");
			matlab.BinData(getDataFolderName(), "BinnedData", getBinWidth(), getStepSize());
		} catch (MatlabInvocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NDTFrame frame = new NDTFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws MatlabInvocationException 
	 */
	public NDTFrame() throws MatlabInvocationException, IOException { 
		
		matlab = new MatlabHelper();
		//Swing code to create frame and layout
		setResizable(false);
		setTitle("Neural Data Toolbox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(125, 125, 525, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(5, 5, 509, 336);
		contentPane.add(tabbedPane);
		
		JPanel inputPanel = new JPanel();
		JPanel dsPanel = new DSPanel();
		JPanel classifierPanel = new CVPanel();
		JPanel crossValidatorPanel = new ClassPanel();
		
		tabbedPane.addTab("Inputs", null, inputPanel, null );
		inputPanel.setLayout(null);
		
		JPanel inputParamPanel = new JPanel();
		inputParamPanel.setBounds(0, 145, 0, 0);
		inputPanel.add(inputParamPanel);
		inputParamPanel.setLayout(null);
		
		JPanel datasetPanel = new JPanel();
		datasetPanel.setBounds(0, 0, 504, 308);
		datasetPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		inputPanel.add(datasetPanel);
		datasetPanel.setLayout(null);
		
		JLabel datasetLabel = new JLabel("Data Directory:");
		datasetLabel.setBounds(10, 28, 74, 14);
		datasetPanel.add(datasetLabel);
		datasetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		datasetLabel.setVerticalAlignment(SwingConstants.TOP);
		
		datasetPath = new JTextField();
		datasetPath.setBounds(94, 24, 296, 23);
		datasetPath.setHorizontalAlignment(SwingConstants.CENTER);
		datasetPanel.add(datasetPath);
		datasetPath.setColumns(28);
		
		JButton datasetBrowseButton = new JButton("Browse...");
		datasetBrowseButton.setBounds(400, 24, 94, 23);
		datasetBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setDialogTitle("Select the folder that contains your data");
				fc.showOpenDialog(fc);
				datasetPath.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});
		datasetPanel.add(datasetBrowseButton);
		
		JLabel lblNewLabel = new JLabel("Input Parameters");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(195, 79, 118, 23);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		datasetPanel.add(lblNewLabel);
		
		JLabel lblBinWidth = new JLabel("Bin Width:");
		lblBinWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBinWidth.setBounds(160, 143, 59, 14);
		datasetPanel.add(lblBinWidth);
		
		
		binValue = new JTextField();
		binValue.setBounds(248, 140, 86, 20);
		datasetPanel.add(binValue);
		binValue.setColumns(10);
		
		JLabel lblStepSize = new JLabel("Step Size:");
		lblStepSize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStepSize.setBounds(160, 182, 59, 14);
		datasetPanel.add(lblStepSize);
		
		stepValue = new JTextField();
		stepValue.setColumns(10);
		stepValue.setBounds(248, 179, 86, 20);
		datasetPanel.add(stepValue);
		
		
		//Bin Data Button actions.
		JButton btnNewButton = new JButton("Bin Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				binDataButton();
			}
			
		});
		btnNewButton.setBounds(209, 242, 89, 23);
		datasetPanel.add(btnNewButton);
		tabbedPane.addTab("Datasource", null, dsPanel, null);
		tabbedPane.addTab("Classifier", null, classifierPanel, null);
		classifierPanel.setLayout(null);
		tabbedPane.addTab("Cross-Validator", null, crossValidatorPanel, null);
		crossValidatorPanel.setLayout(null);
		JPanel featPreprocessorPanel = new FPPanel();
		//		dsPanel.setLayout(null);
		//		
		//		JLabel lblCreateSimRecPop = new JLabel("Create Simultaneously Recorded Populations:");
		//		lblCreateSimRecPop.setHorizontalAlignment(SwingConstants.TRAILING);
		//		lblCreateSimRecPop.setBounds(10, 45, 224, 14);
		//		dsPanel.add(lblCreateSimRecPop);
		//		
		//		JCheckBox chkCreateSimRecPop = new JCheckBox("");
		//		chkCreateSimRecPop.setBounds(248, 45, 21, 14);
		//		dsPanel.add(chkCreateSimRecPop);
		//		
		//		JComboBox cmbDS = new JComboBox();
		//		cmbDS.setModel(new DefaultComboBoxModel(new String[] {"Basic", "Generalization"}));
		//		cmbDS.setBounds(246, 11, 79, 20);
		//		dsPanel.add(cmbDS);
		//		
		//		JLabel lblType = new JLabel("Type:");
		//		lblType.setHorizontalAlignment(SwingConstants.TRAILING);
		//		lblType.setBounds(100, 14, 134, 14);
		//		dsPanel.add(lblType);
				tabbedPane.addTab("Feature Preprocessor", null, featPreprocessorPanel, null);
				featPreprocessorPanel.setLayout(null);
	}
}
