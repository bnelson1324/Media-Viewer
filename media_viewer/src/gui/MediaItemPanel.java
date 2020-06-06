package gui;

import javax.swing.JPanel;

import media.MediaItem;

public class MediaItemPanel extends JPanel {

	/* This class is used to display a file name and image in the search grid */
	
	private MediaItem displayedMediaItem;
	
	public MediaItemPanel(MediaItem displayedMediaItem) {
		this.displayedMediaItem = displayedMediaItem;
	}
	
	public MediaItem getDisplayedMediaItem() {
		return displayedMediaItem;
	}
	
}
