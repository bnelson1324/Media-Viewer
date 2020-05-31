package media_control;

import java.nio.file.Path;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import media.MediaData;
import media.MediaItem;

public class MediaHandler {
	// TODO: make it so this class doesnt just call upon mediaLoader's getter methods, but instead acts upon the allMediaItems and allMediaData objects themselves
	
	/* This class handles all media */
	
	// used to parse searches
	private static ScriptEngineManager sem;
	private static ScriptEngine se;
	

	public static ArrayList<MediaItem> getMediaItemsByTag(String search) {
		// media items that pass the search
		ArrayList<MediaItem> passingMediaItems = new ArrayList<MediaItem>();
		
		ArrayList<MediaItem> allMediaItems = MediaLoader.getAllMediaItems();
		
		for(MediaItem mi : allMediaItems) {
			// skips media items w/o media data
			if(MediaLoader.getMediaDataByPath(mi.getPath()) == null) {
				continue;
			}
			
			// tags the current media item has
			ArrayList<String> containedTags = MediaLoader.getMediaDataByPath(mi.getPath()).getAllTags();
			
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
		return MediaLoader.getAllMediaItems();
	}
	
	public static MediaData getMediaDataByPath(Path path) {
		return MediaLoader.getMediaDataByPath(path);
	}
	
	public static void addMedia(MediaItem mi, MediaData md) {
		// pairs a media item its media data
		MediaLoader.addMediaData(mi.getPath(), md);
	}
	
	public static void init() {
		sem = new ScriptEngineManager();
		se = sem.getEngineByName("JavaScript");
	}
	
	
}

