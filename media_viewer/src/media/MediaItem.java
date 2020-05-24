package media;

import java.nio.file.Path;

public class MediaItem {

	/* This is a piece of media, like an image or video */
	
	// media item's file location relative to the root storage folder
	private Path fileLocation;
	
	public MediaItem(Path fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	@Override
	public String toString() {
		return "fileLocation: " + fileLocation;
	}
	
	
}
