package media_viewer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import media.MediaItem;

public class Main {
// TODO: make it so you can search for images by tag
	
	public static void main(String[] args) {
		
		init();
		
		
		
		// launch program
		
		
		// !! test code below
		
		for(MediaItem mi : MediaItemLoader.getAllMediaItems()) {
			// prints all media data for any existing file automatically 
			System.out.println(MediaDataLoader.getMediaData(mi.getFileLocation()));
		}
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			String input = sc.nextLine();
			System.out.println(MediaDataLoader.getMediaData(Paths.get(input)));
			
			
		}
	}
	
	private static void init() {
		SettingsLoader.loadSettings();
		setUpStorageFolder();
		MediaItemLoader.init();
		MediaDataLoader.init();
		
		
		
	}
	
	private static void setUpStorageFolder() {
		// creates storage directory if it doesn't exist
		File rootStorageFolder = new File(SettingsLoader.getSetting("rootStorageFolderLoc"));
		if(!rootStorageFolder.exists()) {
			rootStorageFolder.mkdir();
		}
		
		// creates media storage file if it doesn't exist
		File mediaStorageFile = new File("mediaDataStorage.json");
		if(!mediaStorageFile.exists()) {
			try {
				mediaStorageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
}
