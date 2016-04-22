package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Classifier extends NDTObject {
	
	public Classifier() {
		this("Max Correlation Coefficient");
	}
	
	@SuppressWarnings("unchecked")
	public Classifier(String subtype) {
		this.setObjType("Classifier");
		this.setSubType(subtype);
		this.subTypes = FXCollections.observableArrayList("Lib Support Vector Machine", "Max Correlation Coefficient", "Poisson Naive Bayes");
		possValues = (ObservableList<String>[]) (new ObservableList[1]);
		possValues[0] = FXCollections.observableArrayList("Linear", "Polynomial", "Gaussian");

	}
	
	@Override
	public void buildProperties() {
		// TODO Auto-generated method stub
		if (this.getSubType().equals("Lib Support Vector Machine")) {
			properties.putIfAbsent("SVM Scalar", 1);
			properties.putIfAbsent("SVM Kernel Type", "Linear");
		}
	}

}
