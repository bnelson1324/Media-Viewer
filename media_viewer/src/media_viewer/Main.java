package media_viewer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import media.MediaItem;

public class Main {
	
	public static void main(String[] args) {
	
		init();
		
		// !! test code below
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("input a tag to search for: ");
			String input = sc.nextLine();
			System.out.println(MediaHandler.getMediaByTag(input));
			
			
		}
	}
	
	private static void init() {
		SettingsLoader.loadSettings();
		setUpStorageFolder();
		MediaItemLoader.init();
		MediaDataLoader.init();
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
