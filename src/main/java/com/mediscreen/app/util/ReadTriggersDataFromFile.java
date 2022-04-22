package com.mediscreen.app.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTriggersDataFromFile implements ITriggersReader {

	public String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with trigger strings in it, one per line
	 */
	public ReadTriggersDataFromFile (String filepath) {
		this.filepath = filepath;
	}

	@Override
	public ArrayList<String> GetTriggers() {
		ArrayList<String> result = new ArrayList<String>();
		
		if (filepath != "") {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	};


}
