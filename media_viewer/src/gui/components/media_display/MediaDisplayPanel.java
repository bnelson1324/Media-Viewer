package gui.components.media_display;

import java.awt.BorderLayout;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JPanel;

import gui.components.context_menu.MediaItemContextMenu;

public abstract class MediaDisplayPanel extends JPanel {
	
	/* JPanel displaying a media item */
	
	protected Path mediaItem;
	
	// read only values for width and height, and keepAspectRatio
	protected int currentWidth, currentHeight;
	protected boolean currentKeepAspectRatio;
	
	protected MediaItemContextMenu contextMenu;
		
	// what is copied when selecting copy from the context menu
	protected Transferable copyItem;
	
	protected MediaDisplayPanel(Path mediaItem) {
		this.mediaItem = mediaItem;
		this.setLayout(new BorderLayout());
		this.addContextMenu();
	}
	
	public Path getMediaItem() {
		return mediaItem;
	}

	
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		currentWidth = width;
		currentHeight = height;
		currentKeepAspectRatio = keepAspectRatio;
	}
	
	private void addContextMenu() {
		createCopyItem();
		contextMenu = new MediaItemContextMenu(mediaItem, copyItem);
		this.add(contextMenu);
		
		// context menu listeners
		if(contextMenu != null) {
			this.addMouseListener(contextMenu.contextMenuOpener);
		}
		createContextMenuChoices();
	}
	
	// creates copy item
	protected abstract void createCopyItem();
	
	// creates choices for the context menu and copyItem
	protected abstract void createContextMenuChoices();

	public static MediaDisplayPanel makeMediaDisplayPanel(Path mediaItem, boolean preview) {
		String fileType;
		
		try {
			fileType = Files.probeContentType(mediaItem).split("/")[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		MediaDisplayPanel mdpToReturn;
		
		switch(fileType) {
			default:
				System.out.println("unknown: " + mediaItem);
				mdpToReturn = new UnknownDisplayPanel(mediaItem);
				break;
			case "image":
				mdpToReturn = new ImageDisplayPanel(mediaItem, true);
				break;
			case "video":
				if(!preview) {
					mdpToReturn = new VideoDisplayPanel(mediaItem);
				} else {
					mdpToReturn = new VideoDisplayPanelPreview(mediaItem);
				}
				break;
			case "audio":
				mdpToReturn = new AudioDisplayPanel(mediaItem);
				break;
			case "text":
				if(!preview) {
					mdpToReturn = new TextDisplayPanel(mediaItem);
				} else {
					mdpToReturn = new UnknownDisplayPanel(mediaItem); // TODO new TextDisplayPanelPreview(mediaItem);
				}
				break;
			
		}

		return mdpToReturn;
	}	
}
