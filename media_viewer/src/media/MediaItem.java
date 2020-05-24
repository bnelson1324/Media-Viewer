package media;

import java.nio.file.Path;

public class MediaItem {

	/* This is a piece of media, like an image or video */
	
	// media item's file location relative to the root storage folder
	private Path path;
	
	public MediaItem(Path path) {
		this.path = path;
	}

	public Path getPath() {
		return path;
	}
	
	@Override
	public String toString() {
		return path.toString();
	}
	
	
}
