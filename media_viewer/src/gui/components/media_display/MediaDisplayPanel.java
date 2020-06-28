package gui.components.media_display;

import java.awt.BorderLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JPanel;

import gui.components.context_menu.MediaItemContextMenu;

public abstract class MediaDisplayPanel extends JPanel {
	
	/* JPanel displaying a media item */
	
	/* TODO: to implement copying for multiple formants in the context menu, create a variable (of the type transferable maybe) that is what the copy button
	 * will fetch when it is pressed
	*/
	
	
	
	protected Path mediaItem;
	
	protected MediaItemContextMenu contextMenu;
		
	protected MediaDisplayPanel(Path mediaItem) {
		this.mediaItem = mediaItem;
		this.setLayout(new BorderLayout());
		this.addContextMenu();
		

	}
	
	public Path getMediaItem() {
		return mediaItem;
	}

	public abstract void setDisplaySize(int width, int height, boolean keepAspectRatio);
	
	protected abstract void addContextMenu();

	public static MediaDisplayPanel makeMediaDisplayPanel(Path mediaItem) {
		String fileType;
		
		try {
			fileType = Files.probeContentType(mediaItem).split("/")[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		switch(fileType) {
		// TODO: make a preview version of VideoDisplayPanel 
			default:
				System.out.println("unknown: " + mediaItem);
				return new UnknownDisplayPanel(mediaItem);
			case "image":
				return new ImageDisplayPanel(mediaItem, true);
			case "video":
				return new VideoDisplayPanel(mediaItem);
			
		}

	}
	
	
}
