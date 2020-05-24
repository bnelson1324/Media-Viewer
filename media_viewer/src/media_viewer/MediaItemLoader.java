package media_viewer;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import media.MediaItem;

public class MediaItemLoader {
	
	private static ArrayList<MediaItem> allMediaItems;
	
	/* This is what loads media files*/
	
	public static void init() {
		allMediaItems = new ArrayList<MediaItem>();
		
		File rootStorageFolder = new File(SettingsLoader.getSetting("rootStorageFolderLoc"));
		ArrayList<File> allFiles = new ArrayList<File>();
		fetchFiles(rootStorageFolder, allFiles);
		
		for(File f : allFiles) {
			String thePath = f.getPath();
			// removes root folder storage location from the path
			String relativePath = thePath.substring(rootStorageFolder.getName().length() + 1);
			
			MediaItem mi = new MediaItem(Paths.get(relativePath));
			allMediaItems.add(mi);
		}
	}
	
	
	// recursively adds all non-directory files from a directory, including from its subdirectories, into an ArrayList
	public static void fetchFiles(File dir, ArrayList<File> allNonDirFiles) {
		// !! not sure if this works
		File[] allFiles = dir.listFiles();
	
		for(File f : allFiles) {
			if(f.isDirectory()) {
				fetchFiles(f, allNonDirFiles);
			} else {
				allNonDirFiles.add(f);
			}
		}
	}
	
	
}
