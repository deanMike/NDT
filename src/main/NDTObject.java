package main;

import java.util.TreeMap;
import java.util.Map.Entry;

import com.opencsv.CSVReader;

import javafx.collections.ObservableList;

@SuppressWarnings("unused")
public abstract class NDTObject {
	
	private String subType;
	
	public ObservableList<String>[] possValues;
	
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public ObservableList<String> subTypes;
	
	protected TreeMap<String, Object> properties = new TreeMap<String, Object>();
	
	public TreeMap<String, Object> getProperties() {
		return properties;
	}

	private String objType = "Object";
	
	
	public String getObjType() {
		return objType;
	}
	
	public void setObjType(String objType) {
		this.objType = objType;
	}

	private String input;
	
	public NDTObject() {
	}
	public NDTObject(String subType) {
		//fc = new JFileChooser(defaultDir);
		//fc.showOpenDialog(fc);
		
		//this.input = fc.getSelectedFile().getAbsolutePath();
		//System.out.println(this.input);
	}
		
	public abstract void buildProperties();
	
	public void printProperties(){
		for (Entry<String, Object> me : properties.entrySet()) {
			Object output = new String("");
			if (me.getValue() != null)
				output = me.getKey() + ": " + me.getValue();
			System.out.println(output);
		}
	}
	
	public void deleteProperties() {
		properties.clear();
	}
	
	public static void main(String[] args) {
//		try {
//			ndto.readProperties();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		
		
	}
	
	
	
}
