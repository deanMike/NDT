package main;

import java.util.ArrayList;
import javafx.collections.FXCollections;

public class DataSource extends NDTObject{
	//Enum to define the type of datasource.
	
	//Constructor 
	public DataSource() {
		this("basic_DS");
	}
	public DataSource(String subType) {
		this.setObjType("Data Source");
		this.setSubType(subType);
		this.subTypes = FXCollections.observableArrayList("basic_DS", "generalization_DS");
		
		this.buildProperties();
	}
	
	//Method called by the constructor to create a list of properties based on the type of Datasource
	public void buildProperties() {
		//Basic and Shared Datasource properties.
		properties.clear();
		properties.putIfAbsent("Specific Binned Labels Names", "stimulus_ID");
		properties.putIfAbsent("Num CV Splits", 20);
		properties.putIfAbsent("Create Simulataneously Recorded Populations", false);
		properties.putIfAbsent("Sample Sites with Replacement", false);
		properties.putIfAbsent("Num Times to Repeat each Label per CV Split", 1);
		properties.putIfAbsent("Label Names to Use", "");
		properties.putIfAbsent("Num Resample Sites", -1);
		properties.putIfAbsent("Sites to Use", -1);
		properties.putIfAbsent("Sites to Exclude", "");
		properties.putIfAbsent("Time Periods to Get Data From", "");
		properties.putIfAbsent("Randomly Shuffle Labels Before Running", false);
		//Generalization Datasource properties.
		if (this.getSubType().equals("generalization_DS")){
			properties.putIfAbsent("Use Unique Data in Each Split", false);
		} 		
	}
}
