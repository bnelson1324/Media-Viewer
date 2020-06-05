package media_control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import media.MediaData;
import media.MediaItem;
import settings.SettingsHandler;

public class MediaHandler {

	/* This class handles all media */
	
	// all media items in the storage folder
	private static ArrayList<MediaItem> allMediaItems;
	
	// pairs file path with a media object
	private static HashMap<Path, MediaData> allMediaData;
	
	
	// used to parse searches
	private static ScriptEngineManager sem;
	private static ScriptEngine se;
	
	

	
	

	public static ArrayList<MediaItem> getMediaItemsByTag(String search) {
		// media items that pass the search
		ArrayList<MediaItem> passingMediaItems = new ArrayList<MediaItem>();
		
		ArrayList<MediaItem> allMediaItems = getAllMediaItems();
		
		for(MediaItem mi : allMediaItems) {
			// skips media items w/o media data
			if(getMediaDataByPath(mi.getPath()) == null) {
				continue;
			}
			
			// tags the current media item has
			ArrayList<String> containedTags = getMediaDataByPath(mi.getPath()).getAllTags();
			
			// checks current media item has the desired tag
			
			
			// make a string replacing all the tag names with booleans saying if this MediaItem has said tag
			String toEval = "";
			String nextTag = "";
			for(char ch : search.toCharArray()) {
				if(ch == '&' || ch == '|' || ch == '!' || ch == '(' || ch == ')') {
					// if char isn't part of the tag
					if(!nextTag.trim().equals("") ) {
						// this runs when the full tag name is determined. this determines whether the tag is present in the MediaItem
						boolean hasTag = containedTags.contains(nextTag.trim());
						toEval += hasTag;
						nextTag = "";
					}
					
					toEval += ch;
				} else {
					// if ch is part of the tag
					nextTag += ch;
				}
			}
			// adds final boolean to toEval
			if(!nextTag.trim().equals("") ) {
				// this runs when the full tag name is determined. this determines whether the tag is present in the MediaItem
				boolean hasTag = containedTags.contains(nextTag.trim());
				toEval += hasTag;
				nextTag = "";
			}
			
			// evaluate toEval in JavaScript
			try {
				if((boolean) se.eval(toEval)) {
					passingMediaItems.add(mi);
				}
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		
		return passingMediaItems;
	}
	
	public static ArrayList<MediaItem> getAllMediaItems() {
		return allMediaItems;
	}
	
	public static MediaData getMediaDataByPath(Path path) {
		return allMediaData.get(path);
	}
	
	public static void addMedia(MediaItem mi, MediaData md) {
		// pairs a media item its media data
		addMediaData(mi.getPath(), md);
	}
	
	public static void addMediaData(Path p, MediaData md) {
		allMediaData.put(p, md);
	}
	
	// reloads media items and data in case any changes have been made
	public static void refreshMediaFolder() {
		setUpStorageFolder();
		MediaLoader.init();
		
		allMediaItems = MediaLoader.getMediaItems();
		allMediaData = MediaLoader.getMediaData();
	}
	
	public static HashMap<Path, MediaData> getAllMediaData() {
		return allMediaData;
	}
	
	
	public static void init() {
		MediaLoader.init();
		
		allMediaItems = MediaLoader.getMediaItems();
		allMediaData = MediaLoader.getMediaData();
		
		sem = new ScriptEngineManager();
		se = sem.getEngineByName("JavaScript");

	}
	
	public static void setUpStorageFolder() {
		// creates storage directory if it doesn't exist
		File rootStorageFolder = new File(SettingsHandler.getSetting("rootStorageFolderLoc"));
		if(!rootStorageFolder.exists()) {
			rootStorageFolder.mkdir();
		}
	}
	
	public static void setUpMediaStorageFile() {
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

