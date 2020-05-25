package media_viewer;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import media.MediaItem;

public class MediaHandler {

	/* This class handles all media */
	
	// used to parse searches
	private static ScriptEngineManager sem;
	private static ScriptEngine se;
	
	
	public static ArrayList<MediaItem> getMediaByTag(String search) {
		// media items that pass the search
		ArrayList<MediaItem> passingMediaItems = new ArrayList<MediaItem>();
		
		ArrayList<MediaItem> allMediaItems = MediaItemLoader.getAllMediaItems();
		// TODO: add ability to use parentheses ( (<tag> && <tag>) || (<tag> && <tag>) ) in search
		
		
		for(MediaItem mi : allMediaItems) {
			// tags the current media item has
			ArrayList<String> containedTags = MediaDataLoader.getMediaDataByPath(mi.getPath()).getAllTags();
			
			// checks current media item has the desired tag
			
			
			// make a string replacing all the tag names with booleans saying if this MediaItem has said tag
			String toEval = "";
			String nextTag = "";
			for(char ch : search.toCharArray()) {
				if(ch == '&' || ch == '|' || ch == '!') {
					// TODO: add to above when implementing parentheses and not operator
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
	

	private static void searchUpdateToEval(String nextTag, String toEval, ArrayList<String> containedTags) {
		// helper method for getMediaByTag()
		boolean hasTag = containedTags.contains(nextTag.trim());
		toEval += hasTag;
		nextTag = "";
	}
	
	public static void init() {
		sem = new ScriptEngineManager();
		se = sem.getEngineByName("JavaScript");
	}
	
	
}

