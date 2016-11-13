package javafx;
import javafx.scene.control.*;
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
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.AlertBox;

/***************************************************************************
 * This class is the main class to be executed for the Neural Data Toolbox.*
 * *************************************************************************
 * NDTStage contains all JavaFX required functions and code.
 * NDTStage currently also contains all variables necessary for loading data. (To be moved to separate Class).
 */


public class NDTStage extends Application{
	/************************
	 **Create Layout for GUI*
	 ************************/	
	//JavaFX Stage, the actual GUI window. 
	Stage window;
	//JavaFX Scene, the container for all objects within the stage.
	Scene defaultScene;
	//GridPane creates a grid on which JavaFX Controls can be placed
	GridPane grid = new GridPane();
	//Label and TextField array to store and iterate over labels.
	Label[] labels;
	TextField[] fields;
	TextField dataTextField;
	TabPane tabs;
	GridPane mainLayout;
	Tab mainTab;
	NDTTab dsTab, fpTab, clTab, cvTab;
//	Label tbLabel;
	Label dataLabel;
	Label binnedDataLabel;
	Label savePrefixLabel;
	Label binWidthLabel;
	Label stepSizeLabel;
	//Dimensions of the GUI window.	
	private int height = 650, width = 600;
	
	//Number of properties on the main tab of the GUI.
	private int numProps = 5;
	
	//Buttons and variables to browse for necessary files/directories
	Button rasterBrowseButton;
	Button binnedDataBrowseButton;
	DirectoryChooser dc;
	FileChooser fc;
	
	//Image to be used for icon in top-left corner
	Image icon;
	
	
	//File Objects for locations of Raster Data or Binned Data  
	File dataFolder;
	File binnedDataFile;
	//Data variables, Toolbox directory, Folder with rater data, file with binned data, save prefix for results.
	String tbDir = "toolbox", rasterDataPath = "toolbox/Zhang_Desimone_7objects_raster_data", binnedDataFileName = "toolbox/Binned_Zhang_Desimone_7object_data_150ms_bins_50ms_sampled.mat", savePrefix = "Zhang";
	//Data bin width and step size variables
	int binWidth = 150, stepSize = 50;
	// Datasource Variables
	String binLabelName= "stimulus_ID";
	
	DataSource DS;
	FeaturePreprocessor FP;
	Classifier CL;
	CrossValidator CV;

	ScriptGenerator sg;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		dataTextField = new TextField();
//		loadVariables();
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		
		
		
		tabs = new TabPane();
		rasterBrowseButton = new Button("Browse...");
		binnedDataBrowseButton = new Button("Browse...");
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
		tabs.setMinWidth(tabs.getMaxWidth());
		dataLabel = new Label("Raster Data Path");
		binnedDataLabel = new Label("Binned Data File");
		savePrefixLabel = new Label("Save Prefix");
		binWidthLabel = new Label("Bin Width");
		stepSizeLabel = new Label("Step Size");
		
		labels = new Label[] {dataLabel, binnedDataLabel, savePrefixLabel, binWidthLabel, stepSizeLabel};
		fields = new TextField[numProps];
		
		ArrayList<Label> labelList = new ArrayList<Label>();
		ArrayList<TextField> fieldList = new ArrayList<TextField>();
		
		
		
//		mainPane[numProps] = new HBox();
		Button GenerateScript = new Button("Generate Script");
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
		
		mainLayout.add(rasterBrowseButton, 2, 0);
		mainLayout.add(binnedDataBrowseButton, 2, 1);
		mainLayout.add(GenerateScript, 2, 5);
		mainTab.setContent(mainLayout);

		grid.getChildren().add(tabs);
		grid.autosize();
		
		defaultScene = new Scene(grid, width, height);
		icon = new Image("file:../../images/brain_icon.png");
		window = primaryStage;
		window.setTitle("Neural Decoding Toolbox");
		window.getIcons().add(icon);
		
		dc = new DirectoryChooser();
		fc = new FileChooser();
		
		File file = new File(System.getProperty("user.dir"));
		
		fc.setInitialDirectory(file);
		dc.setInitialDirectory(file);
		
//		fields[0].setText(tbDir);
		fields[0].setText(rasterDataPath);
		fields[1].setText(binnedDataFileName);
		fields[2].setText(savePrefix);
		fields[3].setText(Integer.toString(binWidth));
		fields[4].setText(Integer.toString(stepSize));
		
		rasterBrowseButton.setOnAction(e -> {
			dataFolder = dc.showDialog(null);
			rasterDataPath = dataFolder.getAbsolutePath();
			fields[0].setText(rasterDataPath);
		});
		
		binnedDataBrowseButton.setOnAction(e -> {
			binnedDataFile = fc.showOpenDialog(null);
			fc.setSelectedExtensionFilter(new ExtensionFilter("Matlab Object", "mat"));
			binnedDataFileName = binnedDataFile.getAbsolutePath();
			fields[1].setText(binnedDataFileName);
		});
		
		fields[2].textProperty().addListener(e -> {
			savePrefix = fields[3].getText();
		});
		
		fields[3].textProperty().addListener(e -> {
			binWidth = Integer.parseInt(fields[4].getText());
		});
		
		fields[4].textProperty().addListener(e -> {
			stepSize = Integer.parseInt(fields[5].getText());
		});
		
		GenerateScript.setOnAction(e -> {
			DirectoryChooser fc1 = new DirectoryChooser();
			fc1.setInitialDirectory(file);
			File outputFile = fc1.showDialog(null);
			new ScriptGenerator(sendProperties(), outputFile.getAbsolutePath());
            AlertBox.display("Path", outputFile.getAbsolutePath());
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
		if (exit) {
//			saveVariables();
			Platform.exit();
		}
	}	
	
	public Map<String, Object> sendProperties() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("tbDir", tbDir.replace("\\", File.separator));
		map.put("rasterDataPath", rasterDataPath.replace("\\", File.separator));
		map.put("binnedDataFileName", binnedDataFileName.replace("\\", File.separator));
		map.put("savePrefix", savePrefix.replace("\\", File.separator));
		map.put("binWidth", binWidth);
		map.put("stepSize", stepSize);
		map.put("Datasource Type", DS.getSubType());
		map.put("Feature Preprocessor Type", FP.getSubType());
		map.put("Classifier Type", CL.getSubType());
		map.put("Cross Validator Type", CV.getSubType());
		map.putAll(DS.getProperties());
		map.putAll(FP.getProperties());
		map.putAll(CL.getProperties());
		map.putAll(CV.getProperties());
		return map;
	}
	
//	public void saveVariables() {
//		//String outputPath = System.getProperty("user.dir") + "/input/data";
//		try {
//			File outputFile = new File(outputPath);
//			if (!outputFile.exists()) {
//				outputFile.createNewFile();
//			}
//			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
//			bw.write(tbDir.replace("\\", File.separator));
//			bw.newLine();
//			bw.write(rasterDataPath.replace("\\", File.separator));
//			bw.newLine();
//			bw.write(binnedDataFileName.replace("\\", File.separator));
//			bw.newLine();
//			bw.write(savePrefix.replace("\\", File.separator));
//
//			bw.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void loadVariables() {
////		String inputPath = System.getProperty("user.dir") + "/input/data";
//		try {
//			File inputFile = new File(inputPath);
//
//			BufferedReader br = new BufferedReader(new FileReader(inputFile));
////			tbDir = br.readLine().replace("\\", "/");
//			rasterDataPath = br.readLine().replace("\\", "/");;
//			binnedDataFileName = br.readLine().replace("\\", "/");;
//			savePrefix = br.readLine().replace("\\", "/");;
//
//			br.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public static void main(String[] args) {
		// Required  JavaFX
		launch(args);
		
	}

	
}
//Standard wrapper interface.
//50 minutes significant.
//Which gives the best speed.