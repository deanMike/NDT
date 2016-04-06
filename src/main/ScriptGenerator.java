package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScriptGenerator {
	
	private BufferedReader br;
	private BufferedWriter bw;
	
	private String template = System.getProperty("user.dir") + "/resources/matlab/IntroScriptTemplate.txt";
	private String outputScript = System.getProperty("user.dir") + "/output/NDTScript.m";
	
	public ScriptGenerator(Map<String, String> variables) {
		try {
			File inputFile = new File(template);
			File outputFile = new File(outputScript);
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			br = new BufferedReader(new FileReader(inputFile));
			bw = new BufferedWriter(new FileWriter(outputFile));
			
			String line = br.readLine();
			
			while (line != null){
			
				for (Map.Entry<String, String> me : variables.entrySet()) {
					if ((line.contains(me.getKey()) && (me.getValue() != null))) {
						line = line.replaceAll(me.getKey(), me.getValue());
					};
				}
			bw.write(line + "\n");
			line = br.readLine();
			}
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
