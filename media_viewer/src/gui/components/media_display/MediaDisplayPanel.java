package gui.components.media_display;

import java.awt.BorderLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JPanel;

import gui.components.context_menu.MediaItemContextMenu;

public abstract class MediaDisplayPanel extends JPanel {
	
	/* JPanel displaying a media item */
	
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

	public static MediaDisplayPanel makeMediaDisplayPanel(Path mi) {
		String fileType;
		
		try {
			fileType = Files.probeContentType(mi).split("/")[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		switch(fileType) {
			default:
				System.out.println("unknown: " + fileType);
				return new UnknownDisplay(mi);
			case "image":
				return new ImageDisplay(mi);
			/*case "video":
				//TODO
				return new VideoDisplay(mi);
			*/
		}

	}
	
	
}
