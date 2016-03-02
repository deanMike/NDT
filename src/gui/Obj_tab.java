// The Object tab class reads the properties of a matlab object and created a tab in a GUI including fields for each property

package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import com.opencsv.*;

@SuppressWarnings("unused")
public class Obj_tab  {
	
	//String to input csv file for Matlab output
	private String input;
	private String title;
	private List<String> props = new ArrayList<String>();
	
	public String defaultDir = "resources/matlab/output";
	
	private CSVReader reader;
	
	public void setInput(String path) {
		this.input = path;
	}
	
	public List<String> getProperties() {
		return this.props;
	}
	
	
	// Inheritance of properties.
	// Dropdown generic Java GUI file. Overwrite generics.
	
	// Method to create a list of properties from the csv input file.
	@SuppressWarnings("null")
	public void readProperties() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(this.input));
		String [] nextLine;
	     while ((nextLine = reader.readNext()) != null) {
	    	 this.props.add(nextLine[0]);
	     }
	     reader.close();
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//Development work that will be automated based on GUI input/initial setup of tool.
		Obj_tab tab = new Obj_tab();
		final JFileChooser fc = new JFileChooser(tab.defaultDir);
		fc.showOpenDialog(fc);
		File file = fc.getSelectedFile();
		tab.setInput(file.getAbsolutePath());
		System.out.println(tab.input);
		tab.readProperties();
		System.out.println(Arrays.toString(tab.getProperties().toArray()));
		
		//Create GUI components
		JFrame frame = new JFrame("Neural Decoding Toolbox");
		frame.setSize(800, 600);
		

		JLabel propertyLabels[] = new JLabel[tab.getProperties().size()];
		
		for (int i = 0; i < propertyLabels.length; i++) {
			propertyLabels[i] = new JLabel(tab.getProperties().get(i));
			frame.add(propertyLabels[i]);
		}
		frame.setVisible(true);
		
	}
}