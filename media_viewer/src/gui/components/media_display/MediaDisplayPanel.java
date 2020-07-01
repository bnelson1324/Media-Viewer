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
	
	protected void addContextMenu() {
		createCopyItem();
		contextMenu = new MediaItemContextMenu(mediaItem, copyItem);
		this.add(contextMenu);
		
		// context menu listeners
		if(contextMenu != null) {
			this.addMouseListener(new MouseAdapter() {
	
				// detects if should open context menu
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON3) {
						contextMenu.setVisible(true);
						contextMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
					} 
				}
				
				// detects if should close context menu
				@Override public void mouseEntered(MouseEvent e) {
					if(!contextMenu.contains(e.getPoint())) {
						contextMenu.setVisible(false);
					}
				}
			});
		}
	}
	
	// creates copyItem for the context menu
	protected abstract void createCopyItem();

	public static MediaDisplayPanel makeMediaDisplayPanel(Path mediaItem, boolean preview) {
		String fileType;
		
		try {
			fileType = Files.probeContentType(mediaItem).split("/")[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		switch(fileType) {
			default:
				System.out.println("unknown: " + mediaItem);
				return new UnknownDisplayPanel(mediaItem);
			case "image":
				return new ImageDisplayPanel(mediaItem, true);
			case "video":
				if(!preview) {
					return new VideoDisplayPanel(mediaItem);
				} else {
					return new VideoDisplayPanelPreview(mediaItem);
				}
			case "audio":
				return new AudioDisplayPanel(mediaItem);
			
		}

	}
	
	
}
