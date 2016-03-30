package javafx;



import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;




public class NDTStage extends Application{
	// Declarations
	
	private int height = 300, width = 500;
	private int numProps = 4;
	
	String output;
	
	Button browseButton;
	DirectoryChooser dc;
	Image icon;
	Stage window;
	
	File dataFolder;
	
	String dataPath, savePrefix, binLabelName;
	int binWidth = 150, stepSize = 50, numCVSplits = 20, numResampleRuns = 2;
	
	TextField dataTextField;
	
	Scene defaultScene;
	TabPane tabs;
	VBox mainLayout, DSLayout, CVLayout, FPLayout, CLLayout;
	HBox[] mainPane, DSPane, CVPane, FPPane, CLPane;
	Label[] labels;
	TextField[] fields;
	
	Tab mainTab, DSTab, CVTab, FPTab, CLTab;
	
	
	Label dataLabel;
	Label savePrefixLabel;
	Label binWidthLabel;
	Label stepSizeLabel;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TilePane layout = new TilePane();
		tabs = new TabPane();
		browseButton = new Button("Browse...");
		dataTextField = new TextField();
		mainTab = new Tab("Home");
		DSTab = new Tab("Data Source");
		CVTab = new Tab("Cross-Validator");
		FPTab = new Tab("Feature Preprocessor");
		CLTab = new Tab("Classifier");
		tabs.getTabs().addAll(mainTab, DSTab, CVTab, FPTab, CLTab);
		
		dataLabel = new Label("Raster Data Path");
		savePrefixLabel = new Label("Save Prefix");
		binWidthLabel = new Label("Bin Width");
		stepSizeLabel = new Label("Step Size");
		
		labels = new Label[] {dataLabel, savePrefixLabel, binWidthLabel, stepSizeLabel};
		fields = new TextField[numProps];
		
		mainLayout = new VBox();
		
		mainPane = new HBox[numProps + 1];
		
		
		mainPane[numProps] = new HBox();
		Button BinData = new Button("Bin Data");
		mainPane[numProps].getChildren().add(BinData);
		
		for (int i = 0; i < numProps; i++){
		mainPane[i] = new HBox();
		fields[i] = new TextField();
		
		 
		mainPane[i].getChildren().addAll(labels[i], fields[i]);
		
		if (i < 1) {
			mainPane[i].getChildren().add(browseButton);
		}
		mainPane[i].setSpacing(20);
		mainPane[i].setPadding(new Insets(10, 10, 10, 10));
		}
		
		
		mainLayout.getChildren().addAll(mainPane);
		mainLayout.setSpacing(10);
		mainTab.setContent(mainLayout);
		
		layout.getChildren().add(tabs);
		
		defaultScene = new Scene(layout, width, height);
		
		icon = new Image("file:../../resources/images/brain_icon.png");
		window = primaryStage;
		window.setTitle("Neural Decoding Toolbox");
		window.getIcons().add(icon);
		
		dc = new DirectoryChooser();

		browseButton.setText("Browse...");
		browseButton.setOnAction(e -> {
			dataFolder = dc.showDialog(null);
			dataPath = dataFolder.getAbsolutePath();
			fields[0].setText(dataPath);
		});
		
		
		
		BinData.setOnAction(e -> {
			savePrefix = fields[1].getText();
			binWidth = Integer.parseInt(fields[2].getText());
			stepSize = Integer.parseInt(fields[3].getText());
			binLabelName = "Stimulus_ID";
			output = "introduction_tutorial("
					+ "'" + dataPath + "', "
					+ "'" + savePrefix + "', "
					+ binWidth + ", "
					+ stepSize + ", "
					//+ "'" + binLabelName + "', "
					+ numCVSplits +", "
					+ numResampleRuns
					+ ")";
			System.out.println(output);
		});
		
		
				
		dataTextField.setText(dataPath);
		
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
		
	public static void main(String[] args) {
		// Required for JavaFX
		launch(args);
		
	}

	
}
//Standard wrapper interface.
//50 minutes significant.
//Which gives the best speed.