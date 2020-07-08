package media_viewer;

import gui.GUIManager;
import media_control.MediaHandler;
import settings.SettingsHandler;

public class Main {
	
	// TODO: create an error message if user makes a search/inputs a file that results in an error
	
	public static void main(String[] args) {
	
		init();
		
	}
	
	private static void init() {
		SettingsHandler.init();
		MediaHandler.setUpStorageFolder();
		MediaHandler.setUpMediaStorageFile();
		MediaHandler.init();
		GUIManager.init();
	}
	
	
	
	
}
