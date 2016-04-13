package javafx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NDTTab extends Tab {
	// Fields
	private int vGap = 10;
	private int hGap = 10;
	private Insets insets = new Insets(15, 10, 10, 10);
	// Common Properties
	private GridPane gp = new GridPane();
	private Label[] labels;
	private TextField[] textfields;
	
	
	
	public NDTTab(int n) {
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.setPadding(insets);
		
		
	}
	
}
