package main;

import javafx.collections.FXCollections;

public class CrossValidator extends NDTObject {
	
	public CrossValidator() {
		this("Standard Resample");
	}
	public CrossValidator(String subType) {
		this.setObjType("Cross-Validator");
		this.setSubType(subType);
		this.subTypes = FXCollections.observableArrayList("Standard Resample");
		this.buildProperties();
	}
	
	@Override
	public void buildProperties() {
		// TODO Auto-generated method stub
		properties.putIfAbsent("Num Resample Runs", 50);
		properties.putIfAbsent("Text Only at Training Times", false);
		properties.putIfAbsent("Stop Resample Runs Only When Specific Results Have Converged", false);
		properties.putIfAbsent("Save Normalized Rank", true);
		properties.putIfAbsent("Save Decision Values", true);
		properties.putIfAbsent("Save Extended Decision Values", false);
		properties.putIfAbsent("Save ROC Area Under Curve", true);
		properties.putIfAbsent("Save Mutual Information", true);
		properties.putIfAbsent("Create Confusion Matrix", true);
		properties.putIfAbsent("Save Confusion Matrix Only if Training and Testing at Same Time", true);
		properties.putIfAbsent("Create Confusion Matrix Where All Test Points are Separate", false);
		properties.putIfAbsent("Display Resample Run Time", true);
		properties.putIfAbsent("Display Zero One Loss Results", true);
		properties.putIfAbsent("Display Normalized Rank Results", false);
		properties.putIfAbsent("Display Decision Values", false);
		properties.putIfAbsent("Display Separate CV ROC Results", false);
		properties.putIfAbsent("Display Combined CV ROC Results", false);
		properties.putIfAbsent("Training Time to Display Results", -1);
		properties.putIfAbsent("Display Convergence Values", false);

	}

}
