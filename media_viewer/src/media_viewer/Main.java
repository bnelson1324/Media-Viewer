package media_viewer;

import gui.GUIManager;
import media_control.MediaHandler;
import settings.SettingsHandler;

public class Main {
	
	// TODO: completely redesign gui so that its organized
	
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
