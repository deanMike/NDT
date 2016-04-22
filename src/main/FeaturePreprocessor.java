package main;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeaturePreprocessor extends NDTObject {

	
	
	@SuppressWarnings("unchecked")
	public FeaturePreprocessor() {
		this("Z-Score Normalize");
		possValues = (ObservableList<String>[]) (new ObservableList[1]);
		possValues[0] = FXCollections.observableArrayList("Linear", "Polynomial", "Gaussian");
	}
	
	public FeaturePreprocessor(String subType) {
		this.setObjType("Feature Preprocessor");
		this.setSubType(subType);
		this.subTypes = FXCollections.observableArrayList("Z-Score Normalize", "Select or Exclude Top K Features", "Select P-Value Significant Feaures");
	
		this.buildProperties();
	}
	
	@Override
	//Method called by the constructor to create a list of properties based on the type of Feature Preprocessor
		public void buildProperties() {
			properties.clear();
			//Select or Exclude Top K Features Feature Preprocessor properties.
			if (this.getSubType().equals("Select or Exclude Top K Features")) {
				properties.putIfAbsent("Num Features to Exclude", 0);
				properties.putIfAbsent("Num Features to Use", -1);
				properties.putIfAbsent("Save Extra Info", false);
			} else if (this.getSubType().equals("Select P-Value Significant Feaures")) {
				properties.putIfAbsent("Save Extra Info", false);
			}
	}
}
