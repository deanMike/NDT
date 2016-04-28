package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Classifier extends NDTObject {
	
	public Classifier() {
		this("max_correlation_coefficient_CL");
	}
	
	@SuppressWarnings("unchecked")
	public Classifier(String subtype) {
		this.setObjType("Classifier");
		this.setSubType(subtype);
		this.subTypes = FXCollections.observableArrayList("max_correlation_coefficient_CL", "libsvm_CL", "poisson_naive_bayes_CL");
		possValues = (ObservableList<String>[]) (new ObservableList[1]);
		possValues[0] = FXCollections.observableArrayList("Linear", "Polynomial", "Gaussian");

	}
	
	@Override
	public void buildProperties() {
		// TODO Auto-generated method stub
		if (this.getSubType().equals("libsvm_CL")) {
			properties.putIfAbsent("SVM Scalar", 1);
			properties.putIfAbsent("SVM Kernel Type", "Linear");
		}
	}

}
