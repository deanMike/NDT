package javafx;
import main.ScriptGenerator;


import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;




public class NDTStage extends Application{
	// Declarations
	
	//Create Layout
	
	
	GridPane grid = new GridPane();
	
	
	private int height = 400, width = 600;
	private int numProps = 6;
	
	String output;
	
	Button tbBrowseButton;
	Button rasterBrowseButton;
	Button neuronBrowseButton;
	DirectoryChooser dc;
	FileChooser fc;
	Image icon;
	Stage window;
	
	File dataFolder;
	File neuronFile;
	
	String tbDir, rasterDataPath, neuronName, savePrefix, binLabelName= "Stimulus_ID";
	
	int binWidth = 150, stepSize = 50, numCVSplits = 20, numResampleRuns = 2, labelRepeatsPerSplit = 2;
		
	boolean spikeCounts;
	
	int numFeatUse = 25;
	
	String classType = "max_correlation_coefficient_CL";

	TextField dataTextField;
	
	Scene defaultScene;
	TabPane tabs;
	GridPane mainLayout;


	//VBox DSLayout, CVLayout, FPLayout, CLLayout;
	//HBox[] mainPane, DSPane, CVPane, FPPane, CLPane;
	Label[] labels;
	TextField[] fields;
	
	Tab mainTab, DSTab, CVTab, FPTab, CLTab;
	
	Label tbLabel;
	Label dataLabel;
	Label neuronLabel;
	Label savePrefixLabel;
	Label binWidthLabel;
	Label stepSizeLabel;
	
	ScriptGenerator sg;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		
		
		tabs = new TabPane();
		tbBrowseButton = new Button("Browse...");
		rasterBrowseButton = new Button("Browse...");
		neuronBrowseButton = new Button("Browse...");
		dataTextField = new TextField();
		mainTab = new Tab("Bin Data");
		DSTab = new Tab("Data Source");
		CVTab = new Tab("Cross-Validator");
		FPTab = new Tab("Feature Preprocessor");
		CLTab = new Tab("Classifier");
		tabs.getTabs().addAll(mainTab, DSTab, CVTab, FPTab, CLTab);
		
		tbLabel = new Label("Toolbox Directory");
		dataLabel = new Label("Raster Data Path");
		neuronLabel = new Label("Single Neuron Data File");
		savePrefixLabel = new Label("Save Prefix");
		binWidthLabel = new Label("Bin Width");
		stepSizeLabel = new Label("Step Size");
		
		labels = new Label[] {tbLabel, dataLabel, neuronLabel, savePrefixLabel, binWidthLabel, stepSizeLabel};
		fields = new TextField[numProps];
		
		
		
		
		
//		mainPane[numProps] = new HBox();
		Button BinData = new Button("Generate Script");
	//	mainPane[numProps].getChildren().add(BinData);
		
		for (int i = 0; i < numProps; i++){
//		mainPane[i] = new HBox();
		fields[i] = new TextField();
		
		 
	//	mainPane[i].getChildren().addAll(labels[i], fields[i]);
		
		if (i < 1) {
		//	mainPane[i].getChildren().add(browseButton);
		}
//		mainPane[i].setSpacing(20);
	//	mainPane[i].setPadding(new Insets(10, 10, 10, 10));
		}
		
		
	//	mainLayout.getChildren().addAll(mainPane);
	//	mainLayout.setSpacing(10);
		
		mainLayout = new GridPane();
		mainLayout.setHgap(20);
		mainLayout.setVgap(10);
		mainLayout.setPadding(new Insets(15, 10, 0, 10));
		
		for (int i = 0; i < numProps; i++) {
			mainLayout.add(labels[i], 0, i);
		}
		for (int i = 0; i < numProps; i++) {
			mainLayout.add(fields[i], 1, i);
		}
		
		mainLayout.add(tbBrowseButton, 2, 0);
		mainLayout.add(rasterBrowseButton, 2, 1);
		mainLayout.add(neuronBrowseButton, 2, 2);
		mainLayout.add(BinData, 3, 3);
		mainTab.setContent(mainLayout);

		grid.getChildren().add(tabs);
		
		defaultScene = new Scene(grid, width, height);
		
		icon = new Image("file:../../resources/images/brain_icon.png");
		window = primaryStage;
		window.setTitle("Neural Decoding Toolbox");
		window.getIcons().add(icon);
		
		dc = new DirectoryChooser();
		fc = new FileChooser();
		
		fields[4].setText(Integer.toString(binWidth));
		fields[5].setText(Integer.toString(stepSize));
		
		tbBrowseButton.setText("Browse...");
		tbBrowseButton.setOnAction(e -> {
			dataFolder = dc.showDialog(null);
			tbDir = dataFolder.getAbsolutePath();
			fields[0].setText(tbDir);
		});
		
		rasterBrowseButton.setOnAction(e -> {
			dataFolder = dc.showDialog(null);
			rasterDataPath = dataFolder.getAbsolutePath();
			fields[1].setText(rasterDataPath);
		});
		
		neuronBrowseButton.setOnAction(e -> {
			neuronFile = fc.showOpenDialog(null);
			fc.setSelectedExtensionFilter(new ExtensionFilter("Matlab Object", "mat"));
			neuronName = neuronFile.getAbsolutePath();
			fields[2].setText(neuronName);
		});
		
		
		BinData.setOnAction(e -> {
			sg = new ScriptGenerator(createMap());
		});
		
		
				
		dataTextField.setText(rasterDataPath);
		
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		fillDatasourceTab();
		fillFPTab();
		fillCVTab();
		fillCLTab();
		window.setScene(defaultScene);
		window.show();
	}
	
	public void fillDatasourceTab() {
		GridPane dsPane = new GridPane();
		
		dsPane.setHgap(10);
		dsPane.setVgap(10);
		dsPane.setPadding(new Insets(15, 10, 0, 10));
		
		
		
		Label binnedNamesLabel = new Label("Specific Binned Label Names");
		Label numCVLabel = new Label("Number of CV Splits");
		Label spikeCountsLabel = new Label("Load Data As Spike Counts");
		Label numRepeatsLabel = new Label("Number of Times to Repeat Each Label per CV Split");
		
		Label[] dsLabels = new Label[] {binnedNamesLabel, numCVLabel, spikeCountsLabel, numRepeatsLabel};
		CheckBox spikeCheck = new CheckBox();
		
		TextField[] dsFields = new TextField[dsLabels.length];
		
		for (int i = 0; i < dsFields.length; i++) {
			dsPane.add(dsLabels[i], 0, i);
			if (!dsLabels[i].equals(spikeCountsLabel)) {
				dsFields[i] = new TextField();
				dsPane.add(dsFields[i], 1, i);
			} else {
				dsPane.add(spikeCheck, 1, i);
			}
		}
		
		dsFields[1].setText(Integer.toString(numCVSplits));
		dsFields[3].setText(Integer.toString(labelRepeatsPerSplit));
		dsFields[0].setText(binLabelName);
		
		dsFields[1].textProperty().addListener(e -> {
			numCVSplits = Integer.parseInt(dsFields[1].getText());
			System.out.println(numCVSplits);
		});
		
		dsFields[3].textProperty().addListener(e -> {
			labelRepeatsPerSplit = Integer.parseInt(dsFields[3].getText());
			System.out.println(labelRepeatsPerSplit);
		});
		
		dsFields[0].textProperty().addListener(e -> {
			binLabelName = dsFields[0].getText();
			System.out.println(binLabelName);
		});
		
		spikeCheck.selectedProperty().addListener(e -> {
			spikeCounts = spikeCheck.isSelected();
			System.out.println(spikeCounts);
		});
		

		
		DSTab.setContent(dsPane);
	}
	
	public void fillFPTab() {
		GridPane fpPane = new GridPane();
		
		fpPane.setHgap(10);
		fpPane.setVgap(10);
		fpPane.setPadding(new Insets(15, 10, 0, 10));
		
		
		
		Label featureNumLabel = new Label("Number of Features to Use");
				
		Label[] fpLabels = new Label[] {featureNumLabel};
		
		TextField[] fpFields = new TextField[fpLabels.length];
		
		for (int i = 0; i < fpFields.length; i++) {
			fpPane.add(fpLabels[i], 0, i);
			
			fpFields[i] = new TextField();
			fpPane.add(fpFields[i], 1, i);
		}
		
		fpFields[0].setText(Integer.toString(numFeatUse));
		
		fpFields[0].textProperty().addListener(e -> {
			numFeatUse = Integer.parseInt(fpFields[0].getText());
			System.out.println(numFeatUse);
		});
		
		FPTab.setContent(fpPane);
	}
	
	public void fillCVTab() {
		GridPane cvPane = new GridPane();
		
		cvPane.setHgap(10);
		cvPane.setVgap(10);
		cvPane.setPadding(new Insets(15, 10, 0, 10));
		
		
		
		Label numResampleLabel = new Label("Number of Resample Runs");
				
		Label[] cvLabels = new Label[] {numResampleLabel};
		
		TextField[] cvFields = new TextField[cvLabels.length];
		
		for (int i = 0; i < cvFields.length; i++) {
			cvPane.add(cvLabels[i], 0, i);
			
			cvFields[i] = new TextField();
			cvPane.add(cvFields[i], 1, i);
		}
		
		cvFields[0].setText(Integer.toString(numResampleRuns));
		
		cvFields[0].textProperty().addListener(e -> {
			numResampleRuns = Integer.parseInt(cvFields[0].getText());
			System.out.println(numResampleRuns);
		});
		
		CVTab.setContent(cvPane);
	}
	
	public void fillCLTab() {
		GridPane clPane = new GridPane();
		
		clPane.setHgap(10);
		clPane.setVgap(10);
		clPane.setPadding(new Insets(15, 10, 0, 10));
		
		
		
		Label numResampleLabel = new Label("Number of Resample Runs");
				
		Label[] clLabels = new Label[] {numResampleLabel};
		
		TextField[] clFields = new TextField[clLabels.length];
		
		ComboBox<String> classTypeCombo = new ComboBox<String>() ;
		
		classTypeCombo.getItems().addAll("max_correlation_coefficient_CL", "poisson_naive_bayes_CL", "libsvm_CL");
		
		for (int i = 0; i < clFields.length; i++) {
			clPane.add(clLabels[i], 0, i);
			
			clFields[i] = new TextField();
			clPane.add(classTypeCombo, 1, i);
		}
		
		classTypeCombo.setValue(classType);
		
		classTypeCombo.valueProperty().addListener(e -> {
			classType = classTypeCombo.getValue();
			System.out.println(classType);
		});
				
		CLTab.setContent(clPane);
	}
	
	public void closeProgram() {
		boolean exit = ConfirmBox.display("Exit", "Are you sure you want to exit?");
		if (exit)
			Platform.exit();
	}
	
	public Map<String, String> createMap() {
		Map<String, String> variables = new HashMap<String, String>();
		
		variables.put("tbDir", tbDir);
		variables.put("dataPath", rasterDataPath);
		variables.put("neuronName", neuronName);
		variables.put("savePrefix", savePrefix);
		variables.put("stepSize", Integer.toString(stepSize));
		variables.put("binLabelName", binLabelName);
		variables.put("numCVSplits", Integer.toString(numCVSplits));
//		variables.put("spikeCounts", spikeCounts);
		variables.put("labelRepeatsPerSplit", Integer.toString(labelRepeatsPerSplit));
		variables.put("numFeatUse", Integer.toString(numFeatUse));
		variables.put("classType", classType);
		variables.put("numResampleRuns",Integer.toString(numResampleRuns));
		variables.put("dataPath", rasterDataPath);
		
		
		

		
		return variables;
	}
		
	public static void main(String[] args) {
		// Required for JavaFX
		launch(args);
		
	}

	
}
//Standard wrapper interface.
//50 minutes significant.
//Which gives the best speed.