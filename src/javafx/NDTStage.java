package javafx;
import main.Classifier;
import main.CrossValidator;
import main.DataSource;
import main.FeaturePreprocessor;
import main.ScriptGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;




public class NDTStage extends Application{
	// Declarations
	
	//Create Layout
	
	
	GridPane grid = new GridPane();
	
	
	private int height = 650, width = 600;
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
	// Binning Data Variables
	String tbDir, rasterDataPath, binnedDataFileName, neuronName, savePrefix;
	int binWidth = 150, stepSize = 50;
	// Datasource Variables
	String binLabelName= "stimulus_ID";

	TextField dataTextField;
	
	Scene defaultScene;
	TabPane tabs;
	GridPane mainLayout;


	//VBox DSLayout, CVLayout, FPLayout, CLLayout;
	//HBox[] mainPane, DSPane, CVPane, FPPane, CLPane;
	Label[] labels;
	TextField[] fields;
	
	DataSource DS;
	FeaturePreprocessor FP;
	Classifier CL;
	CrossValidator CV;
	
	Tab mainTab;
	NDTTab dsTab, fpTab, clTab, cvTab;
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
		
		DS = new DataSource();
		FP = new FeaturePreprocessor();
		CL = new Classifier();
		CV = new CrossValidator();
		
		dsTab = new NDTTab(DS);
		fpTab = new NDTTab(FP);
		clTab = new NDTTab(CL);
		cvTab = new NDTTab(CV);
		
		//DSTab = new Tab("Data Source");
		//CVTab = new Tab("Cross-Validator");
		//FPTab = new Tab("Feature Preprocessor");
		//CLTab = new Tab("Classifier");
		tabs.getTabs().addAll(mainTab, dsTab, fpTab, clTab, cvTab);
		tabs.setTabMaxHeight(this.height);
		tabs.setTabMaxWidth(this.width);
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
		
		fields[0].setText(tbDir);
		fields[1].setText(rasterDataPath);
		fields[2].setText(neuronName);
		fields[3].setText(savePrefix);
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
		
		fields[3].textProperty().addListener(e -> {
			savePrefix = fields[3].getText();
		});
		
		fields[4].textProperty().addListener(e -> {
			binWidth = Integer.parseInt(fields[4].getText());
		});
		
		fields[5].textProperty().addListener(e -> {
			stepSize = Integer.parseInt(fields[5].getText());
		});
		
		BinData.setOnAction(e -> {
			new ScriptGenerator(sendProperties());
		});
		
		
				
		dataTextField.setText(rasterDataPath);
		
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		window.setScene(defaultScene);
		window.show();
	}
	
	public void closeProgram() {
		boolean exit = ConfirmBox.display("Exit", "Are you sure you want to exit?");
		if (exit)
			Platform.exit();
	}	
	
	public Map<String, Object> sendProperties() {
		Map<String, Object> map = DS.getProperties();
		map.putAll(FP.getProperties());
		map.putAll(CL.getProperties());
		map.putAll(CV.getProperties());
		return map;
	}
	
	
	public static void main(String[] args) {
		// Required for JavaFX
		launch(args);
		
	}

	
}
//Standard wrapper interface.
//50 minutes significant.
//Which gives the best speed.