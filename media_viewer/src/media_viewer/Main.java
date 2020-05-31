package media_viewer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import media.MediaItem;
import media_control.MediaHandler;
import media_control.MediaLoader;

public class Main {
	
	/* TODO: when the program starts, detect if any media items are untagged
	 * (can be done by seeing if their media data is null), and prompt the user to tag them
	 */
	
	public static void main(String[] args) {
	
		init();
		
		// !! test code below
		
		Scanner sc = new Scanner(System.in);
		CommandConsole.takeInput("");
		while(true) {
			String input = sc.nextLine();
			CommandConsole.takeInput(input);
		}
	}
	
	private static void init() {
		SettingsLoader.loadSettings();
		setUpStorageFolder();
		MediaLoader.init();
		MediaHandler.init();
	}
	
	private static void setUpStorageFolder() {
		// creates storage directory if it doesn't exist
		File rootStorageFolder = new File(SettingsLoader.getSetting("rootStorageFolderLoc"));
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
