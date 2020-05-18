package media_viewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class SettingsLoader {

	private static BufferedReader br;
	
	private static HashMap settingsMap = new HashMap<String, String>();
	
	public static String getSetting(String key) {
		return (String) settingsMap.get(key);
	}
	
	public static void loadSettings() {
		// loads the Media Viewer's settings
		
		File settingsFile = new File("settings/settings.cfg");

		// makes sure settings file exists
		if(!settingsFile.exists()) {
			// create a new settings file by copying the default one
			File defaultSettings = new File("res/DEFAULT_SETTINGS.cfg");
			try {
				Files.copy(defaultSettings.toPath(), settingsFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// reading file
		try {
			 br = new BufferedReader(new FileReader(settingsFile));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line;
		try {
			while ((line = br.readLine()) != null) {
				// puts settings in settingsMap
				String[] currentSetting = line.split("=");
				settingsMap.put(currentSetting[0], currentSetting[1]);
	        }
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
