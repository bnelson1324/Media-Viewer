package media_viewer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import media.MediaItem;

public class MediaItemLoader {
	
	private static ArrayList<MediaItem> allMediaItems;
	
	/* This is what loads media files*/
	
	public static void init() {
		allMediaItems = new ArrayList<MediaItem>();
		
		Path rootStorageFolder = Paths.get(SettingsLoader.getSetting("rootStorageFolderLoc"));
		ArrayList<Path> allFiles = new ArrayList<Path>();
		fetchFiles(rootStorageFolder.toFile(), allFiles);
		
		for(Path f : allFiles) {
			// removes root folder storage location from the path
			String relativePath = f.toString().substring(rootStorageFolder.toString().length() + 1);
			
			MediaItem mi = new MediaItem(Paths.get(relativePath));
			allMediaItems.add(mi);
		}
	}
	
	
	// recursively adds all non-directory files from a directory, including from its subdirectories, into an ArrayList
	public static void fetchFiles(File dir, ArrayList<Path> allNonDirFiles) {
		File[] allFiles = dir.listFiles();
	
		for(File f : allFiles) {
			if(f.isDirectory()) {
				fetchFiles(f, allNonDirFiles);
			} else {
				allNonDirFiles.add(f.toPath());
			}
		}
	}
	
	public static ArrayList<MediaItem> getAllMediaItems() {
		return allMediaItems;
	}
	
	
}
