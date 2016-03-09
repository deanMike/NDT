package javafx;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




public class NDTStage extends Application{
	// Declarations
	Button browseButton;
	FileChooser fc;
	Image icon;
	Stage window;
	String dataPath;
	Scene defaultScene;
	TabPane tabs;
	Tab dataTab;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TilePane layout = new TilePane();
		tabs = new TabPane();
		browseButton = new Button("Browse...");
		dataTab = new Tab("Data");
		tabs.getTabs().add(dataTab);
		dataTab.setContent(browseButton);
		layout.getChildren().add(tabs);
		
		defaultScene = new Scene(layout, 300, 250);
		
		icon = new Image("file:../../resources/images/brain_icon.png");
		window = primaryStage;
		window.setTitle("Neural Decoding Toolbox");
		window.getIcons().add(icon);
		
		fc = new FileChooser();

		browseButton.setText("Browse...");
		browseButton.setOnAction(e -> fc.showOpenDialog(null));
		
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