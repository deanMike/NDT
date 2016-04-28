package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeaturePreprocessor extends NDTObject {

	
	
	@SuppressWarnings("unchecked")
	public FeaturePreprocessor() {
		this("zscore_normalize_FP");
		possValues = (ObservableList<String>[]) (new ObservableList[1]);
		possValues[0] = FXCollections.observableArrayList("Linear", "Polynomial", "Gaussian");
	}
	
	public FeaturePreprocessor(String subType) {
		this.setObjType("Feature Preprocessor");
		this.setSubType(subType);
		this.subTypes = FXCollections.observableArrayList("zscore_normalize_FP", "select_or_exclude_top_k_features_FP", "select_pvalue_significant_features_FP");
	
		this.buildProperties();
	}
	
	@Override
	//Method called by the constructor to create a list of properties based on the type of Feature Preprocessor
		public void buildProperties() {
			properties.clear();
			//Select or Exclude Top K Features Feature Preprocessor properties.
			if (this.getSubType().equals("select_or_exclude_top_k_features_FP")) {
				properties.putIfAbsent("Num Features to Exclude", 0);
				properties.putIfAbsent("Num Features to Use", -1);
				properties.putIfAbsent("Save Extra Info", false);
			} else if (this.getSubType().equals("select_pvalue_significant_features_FP")) {
				properties.putIfAbsent("Save Extra Info", false);
			}
	}
}
