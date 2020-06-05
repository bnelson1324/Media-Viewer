package media_viewer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import gui.GUIHandler;
import media_control.MediaHandler;
import media_control.MediaLoader;
import settings.*;

public class Main {
	
	/* TODO: when the program starts, detect if any media items are untagged
	 */
	// TODO: replace all n/a tags in the json file w/ blank spaces
	
	public static void main(String[] args) {
	
		init();
		
		// !! test code below
		

		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		CommandConsole.takeInput("");
		while(true) {
			String input = sc.nextLine();
			CommandConsole.takeInput(input);
		}
	}
	
	private static void init() {
		SettingsHandler.init();
		setUpStorageFolder();
		MediaHandler.init();
		GUIHandler.init();
	}
	
	private static void setUpStorageFolder() {
		// creates storage directory if it doesn't exist
		File rootStorageFolder = new File(SettingsHandler.getSetting("rootStorageFolderLoc"));
		if(!rootStorageFolder.exists()) {
			rootStorageFolder.mkdir();
		}
		
		// create a new media storage file by copying the default one
		File mediaStorageFile = new File("mediaDataStorage.json");
		if(!mediaStorageFile.exists()) {
			File defaultMediaStorage = new File("res/DEFAULT_MEDIA_DATA_STORAGE.json");
			try {
				Files.copy(defaultMediaStorage.toPath(), mediaStorageFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
}
