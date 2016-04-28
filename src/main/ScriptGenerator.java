package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ScriptGenerator {
	
	private BufferedReader br;
	private BufferedWriter bw;
	
	private String template = System.getProperty("user.dir") + "/resources/matlab/decoding_script_to_be_generated.m";
	private String outputScript = System.getProperty("user.dir") + "/output/NDTScript.m";
	
	@SuppressWarnings("unchecked")
	public ScriptGenerator(Map<String, Object> variables) {
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
				System.out.println(variables.size());
				for (Map.Entry<String, Object> me : variables.entrySet()) {
					System.out.println(me.getKey() + ": ");
					System.out.print(me.getValue().toString());
					String replaceString = "replace" + me.getKey();
					if ((line.contains(replaceString) && (me.getValue() != null))) {
						if(me.getValue().getClass().equals(Boolean.class)) {
							Integer i = (boolean) me.getValue() ? 1: 0;
							line = line.replaceAll(replaceString, i.toString()) ;
						} else if (me.getValue().getClass().equals(ArrayList.class)){
							ArrayList<String> alStr = (ArrayList<String>)me.getValue();
							String s = "{";
							for (String str : alStr){
								s.concat("'" + str + "'");
								if (!alStr.get(alStr.size() - 1).equals(str)) {
									s.concat(",");
								}
							}
							s.concat("}");
							line = line.replaceAll(replaceString, s);
						} else {
							line = line.replaceAll(replaceString, me.getValue().toString());
							System.out.println(line);
						}
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
