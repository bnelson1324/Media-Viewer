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
	
	// fileType (image, audio, video, etc)
	protected String fileType;
	
	protected MediaItemContextMenu contextMenu;
		
	
	protected MediaDisplayPanel(Path mi, String fileType) {
		this.mediaItem = mi;
		this.setLayout(new BorderLayout());
		this.addContextMenu();
		

	}
	
	public Path getMediaItem() {
		return mediaItem;
	}

	public abstract void setDisplaySize(int width, int height, boolean keepAspectRatio);
	
	protected abstract void addContextMenu();

	public static MediaDisplayPanel makeMediaDisplayPanel(Path mi, boolean preview) {
		String fileType;
		
		try {
			fileType = Files.probeContentType(mi).split("/")[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		switch(fileType) {
			default:
				System.out.println("unknown type: " + fileType);
				return new UnknownDisplayPanel(mi);
			case "image":
				return new ImageDisplayPanel(mi);
			case "video":
				//TODO
				if(!preview) {
					return new VideoDisplayPanel(mi);
				} else {
					// TODO: temp code below, make a new object for previews
					return new VideoDisplayPanel(mi);
				}
		}
		

	}
	
	
}
