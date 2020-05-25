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
		
		// searches to test
		// only && and || used
		String search1 = "!green && magenta";
		String search2 = "!death grips && magenta";
		String search3 = " death grips ||!magenta";
		String search4 = "2002 Toyota Camry|| magenta ";
		System.out.println("search1: " + MediaHandler.getMediaByTag(search1));
		System.out.println("search2: " + MediaHandler.getMediaByTag(search2));
		System.out.println("search3: " + MediaHandler.getMediaByTag(search3));
		System.out.println("search4: " + MediaHandler.getMediaByTag(search4));
		
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
