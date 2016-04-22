package main;

import java.util.ArrayList;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource extends NDTObject{
	//Enum to define the type of datasource.
	
	//Constructor 
	public DataSource() {
		this("BasicDS");
	}
	public DataSource(String subType) {
		this.setObjType("Data Source");
		this.setSubType(subType);
		this.subTypes = FXCollections.observableArrayList("BasicDS", "GeneralizationDS");
		
		this.buildProperties();
	}
	
	//Method called by the constructor to create a list of properties based on the type of Datasource
	public void buildProperties() {
		//Basic and Shared Datasource properties.
		properties.clear();
		properties.putIfAbsent("Create Simulataneously Recorded Populations", false);
		properties.putIfAbsent("Sample Sites with Replacement", false);
		properties.putIfAbsent("Num Times to Repeat each Label per CV Split", 1);
		properties.putIfAbsent("Label Names to Use", new ArrayList<String>());
		properties.putIfAbsent("Num Resample Sites", -1);
		properties.putIfAbsent("Sites to Use", -1);
		properties.putIfAbsent("Sites to Exclude", new ArrayList<String>());
		properties.putIfAbsent("Time Periods to Get Data From", new ArrayList<String>());
		properties.putIfAbsent("Randomly Shuffle Labels Before Running", false);
		//Generalization Datasource properties.
		if (this.getSubType().equals("GeneralizationDS")){
			properties.putIfAbsent("Use Unique Data in Each Split", false);
		} 		
	}
}
