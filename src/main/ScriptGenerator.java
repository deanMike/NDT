package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class ScriptGenerator {
	
	private BufferedReader br;
	private BufferedWriter bw;
	
	private String template = "/matlab/decoding_script_to_be_generated.m";
	private String outputScript = "/NDTScript.m";
	
	
	
	@SuppressWarnings("unchecked")
	public ScriptGenerator(Map<String, Object> variables, String outputPath) {
		
		try {
			InputStream inputFile = this.getClass().getResourceAsStream(template);
			File outputFile = new File(outputPath + outputScript);
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			br = new BufferedReader(new InputStreamReader(inputFile));
			bw = new BufferedWriter(new FileWriter(outputFile));
			
			String line = br.readLine();
			
			while (line != null){
				System.out.println(variables.size());
				for (Map.Entry<String, Object> me : variables.entrySet()) {
					System.out.print("\n" + me.getKey() + ": ");
					System.out.print(me.getValue().toString());
					String replaceString = "replace" + me.getKey();
					if ((line.contains(replaceString) && (me.getValue() != null))) {
						if(me.getValue().getClass().equals(Boolean.class)) {
							Integer i = (boolean) me.getValue() ? 1: 0;
							line = line.replaceAll(replaceString, i.toString()) ;
						} else if (me.getValue().getClass().equals(ArrayList.class)){
							ArrayList<String> alStr = (ArrayList<String>)me.getValue();
							if (alStr.size() > 0) {
								StringBuilder builder = new StringBuilder();
								builder.append("{");
								for (String str : alStr){
									builder.append("'" + str + "'");
									if (!alStr.get(alStr.size() - 1).equals(str)) {
										builder.append(",");
									}
								}
								builder.append("}");
								line = line.replaceAll(replaceString, builder.toString());
							} else {
								line.replace(replaceString, "");
							}
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
