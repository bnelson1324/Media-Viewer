package media_viewer;

import java.nio.file.Paths;
import java.util.ArrayList;

import media.MediaItem;

public class MediaHandler {

	/* This class handles all media */
	
	
	public static ArrayList<MediaItem> getMediaByTag(String search) {
		// media items that contain all tags
		ArrayList<MediaItem> passingMediaItems = new ArrayList<MediaItem>();
		
		ArrayList<MediaItem> allMediaItems = MediaItemLoader.getAllMediaItems();
		
		// TODO: add ability to use not operator (!<tagName>) in search
		// TODO: add ability to use other boolean operators in search (&& and ||)
		// TODO: add ability to search non-tag mediadata like name:<name> or dateAdded:<date>
		for(MediaItem mi : allMediaItems) {
			// tags the current media item has
			ArrayList<String> containedTags = MediaDataLoader.getMediaDataByPath(mi.getPath()).getTags();
			
			// checks current media item has the desired tag
			if(containedTags.contains(search)) {
				passingMediaItems.add(mi);
			}
		}
		
		return passingMediaItems;
	}
	
	
	
}

