package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ScriptGenerator {
	
	private BufferedReader br;
	private BufferedWriter bw;
	
	private String template = "/resources/matlab/IntroScriptTemplate";
	
	public ScriptGenerator() {
		try {
			br = new BufferedReader(new FileReader(new File(template)));
			bw = new BufferedWriter(new FileWriter(new File("/output/NDTScript.m")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void replaceVariables(Map<String, String> variables) {
	
	}
	
	public static void main(String[] args) {
		ScriptGenerator sg = new ScriptGenerator();
		sg.replaceVariables(null);
	}
	
}
