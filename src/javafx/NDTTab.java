package javafx;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import main.NDTObject;


public class NDTTab extends Tab {
	
	private NDTObject ndtObj;
	
	// Fields
	
	
	private int vGap = 10;
	private int hGap = 10;
	private Insets insets = new Insets(15, 10, 10, 10);
	// Common Properties
	private GridPane headerPane = new GridPane();
	private GridPane gp = new GridPane();
	private ArrayList<Label> labels;
	private ArrayList<Control> controls;
	
	public Integer i;
	
	
	
	public NDTTab(NDTObject obj) {
		this.setClosable(false);
		this.ndtObj = obj;
		this.setText(this.ndtObj.getObjType());
		
		this.headerPane.setHgap(hGap);
		this.headerPane.setVgap(vGap);
		this.headerPane.setPadding(insets);
		gp.setHgap(hGap);
		gp.setVgap(vGap);
		gp.setPadding(insets);
		
		//Add a combo box if there are multiple subtypes of the object.
		if (ndtObj.subTypes.size() > 1) {
			ComboBox<String> types = new ComboBox<String>();
			types.setItems(ndtObj.subTypes);
			types.setValue(ndtObj.subTypes.get(0));
			types.valueProperty().addListener(e -> {
				ndtObj.setSubType(types.getValue());
				System.out.println(ndtObj.getSubType());
				ndtObj.deleteProperties();
				ndtObj.buildProperties();
				gp.getChildren().clear();
				
				
				
				this.buildControls();
				this.addControls();


			});
			headerPane.add(new Label(ndtObj.getObjType() + " Type"), 0, 0);
			headerPane.add(types, 1, 0);
		}
		
		this.buildControls();
		this.addControls();
		headerPane.add(gp, 0, 1);
		headerPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		gp.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		this.setContent(headerPane);
		
		
	}
	
	private void buildControls() {
		this.labels = new ArrayList<Label>();
		controls = new ArrayList<Control>();
		TreeMap<String, Object> properties = this.ndtObj.getProperties();
		for (Entry<String, Object> me : properties.entrySet()) {
			Label label = new Label(me.getKey());
			labels.add(label);
			if(me.getValue().getClass().equals(Boolean.class)) {
				CheckBox check = new CheckBox();
				check.setSelected((boolean)me.getValue());
				check.selectedProperty().addListener(e -> {
					me.setValue(check.selectedProperty());
					System.out.println(me.getKey()+ ": " + me.getValue().toString());
				});
				controls.add(check);
				//propertyDisplays.put(new Label(me.getKey()),check);
			} else if (me.getKey().contains("Type")) {
				ComboBox<String> box = new ComboBox<String>();
				box.setItems(ndtObj.possValues[0]);
				box.setValue((String) me.getValue());
				box.valueProperty().addListener(e -> {
					me.setValue(box.getValue());
					System.out.println(me.getKey()+ ": " + me.getValue().toString());
				});
				controls.add(box);
			} else {
				TextField text = new TextField();
				text.setText(me.getValue().toString());
				if (me.getValue().getClass().equals(Integer.class)) {
					i = (Integer) me.getValue();
					text.textProperty().addListener(e -> {
						int tempVariable = i;
						if(text.textProperty().getValue().matches("\\d*")) {
							i = Integer.parseInt(text.getText());
							me.setValue(i);
						} else {
							me.setValue(tempVariable);
						}
						System.out.println(me.getKey()+ ": " + me.getValue().toString());

					});
				} else {
					text.textProperty().addListener(e -> {
						me.setValue(text.textProperty());
						System.out.println(me.getKey()+ ": " + me.getValue().toString());
					});
				}
				controls.add(text);
				//propertyDisplays.put(new Label(me.getKey()), text);
			}
			
		}
	}
	private void addControls() {
		System.out.println(this.labels.size());
		
		for (int i = 0; i < this.labels.size(); i++) {
			System.out.println("Label: " + labels.get(i).getText());
			gp.add(labels.get(i), 0, i + 1);
			gp.add(controls.get(i), 1, i + 1);
		}
	}
	
}
