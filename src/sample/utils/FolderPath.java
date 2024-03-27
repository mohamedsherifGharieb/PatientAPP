package sample.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FolderPath {
	public static int nolines = 0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	try (BufferedReader b = new BufferedReader(new FileReader("startMenuApps.txt"))) {
    		
    		while (b.readLine() != null) {
    			nolines++;
    		}
    		
    	}
    	FileWriter fw = new FileWriter("output.txt");
    	BufferedWriter bw = new BufferedWriter(fw);
    	String line = "";
    	try (BufferedReader br = new BufferedReader(new FileReader("startMenuApps.txt"))) {
    		bw.write("{\"Start Menu Programs\":[\n");
    		bw.newLine();
    		int count = 1;
    		
            int lines = 0;
            String put = "";
            
    		while ((line = br.readLine()) != null) {
    			lines++;
    			String homePath = line.trim();
    			String[] dir= homePath.split(":");
    			int i = 0;

    			while (i <= dir.length-1) {
            		if (lines < nolines){
            			put = dir[i].trim();
            			bw.write("\"" + put + "\":");
            			i++;
            			put = dir[i].trim();
            			bw.write("\"" + put + "\"},\n");
            			bw.newLine();
            			i++;
            			count = 1;
            		}
            		else{
            			put = dir[i].trim();
            			bw.write("\"" + put + "\":");
            			i++;
            			put = dir[i].trim();
            			bw.write("\"" + put + "\"}\n");
            			bw.newLine();
            			i++;
            			count = 1;
            		}
            	}
    	   }
    		bw.write("]}");
        } 
    	bw.close();
    }
}