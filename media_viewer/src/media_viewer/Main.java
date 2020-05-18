package media_viewer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		init();
		
		
		
		// launch program
		
		
		// !! test code below
		Scanner sc = new Scanner(System.in);
		while(true) {
			String input = sc.nextLine();
			System.out.println(MediaDataLoader.getMediaData(new File(input)));
			
			
		}
	}
	
	private static void init() {
		SettingsLoader.loadSettings();
		setUpStorageFolder();
		MediaDataLoader.init();
		
		
		
	}
	
	private static void setUpStorageFolder() {
		// creates storage directory if it doesn't exist
		File rootStorageFolder = new File(SettingsLoader.getSetting("rootStorageFolderLoc"));
		if(!rootStorageFolder.exists()) {
			rootStorageFolder.mkdir();
		}
		
		// creates media storage file if it doesn't exist
		File mediaStorageFile = new File(SettingsLoader.getSetting("rootStorageFolderLoc") + "/..mediaDataStorage.json");
		if(!mediaStorageFile.exists()) {
			try {
				mediaStorageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
}
