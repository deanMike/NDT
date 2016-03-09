package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JFileChooser;

import com.opencsv.CSVReader;

import javafx.stage.FileChooser;

public class NDTObject {
	
	private String objectType;
	
	private Dictionary<String, Integer> properties;
	private String subType;
	private String input;
	private String defaultDir = "resources/matlab/output";
	
	private CSVReader reader;
	private JFileChooser fc;
	
	public NDTObject() {
		fc = new JFileChooser(defaultDir);
		fc.showOpenDialog(fc);
		
		properties = new Hashtable<String, Integer>();
		
		this.input = fc.getSelectedFile().getAbsolutePath();
		System.out.println(this.input);
	}
	
	public void readProperties() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(this.input));
		String [] nextLine;
	     while ((nextLine = reader.readNext()) != null) {
	    	 System.out.println(nextLine[0]);
	    	 this.properties.put((nextLine[0]), 0);
	     }
	     reader.close();
	}
	
	public static void main(String[] args) {
		NDTObject ndto = new NDTObject();
		try {
			ndto.readProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
