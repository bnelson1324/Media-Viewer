package gui.components;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.GUIManager;
import gui.components.context_menu.MediaItemContextMenu;
import media_control.MediaHandler;

public class MediaItemDisplayLabel extends JLabel {
	
	/* JLabel containing a media item's image */
	
	private Path mediaItem;
	private BufferedImage mediaItemImage;
	
	private MediaItemContextMenu contextMenu;
	
	public MediaItemDisplayLabel() {
		super();
		
		contextMenu = new MediaItemContextMenu(this);
		this.add(contextMenu);
		
		// context menu listeners
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
	
	
	public void setMediaItem(Path mi, BufferedImage miImg) {
		this.mediaItem = mi;
		this.mediaItemImage = miImg;
		if(mediaItemImage != null) {
			this.setIcon(new ImageIcon(mediaItemImage));
		}
	}
	
	public void setImageSize(int width, int height, boolean keepAspectRatio) {
		if(mediaItemImage != null) {
			if(keepAspectRatio) {
				this.setIcon(GUIManager.scaleKeepingAspectRatio(mediaItemImage, width, height));
			} else {
				this.setIcon(new ImageIcon(mediaItemImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		}
	}
	
	public Path getMediaItem() {
		return mediaItem;
	}


	// TODO: add compatibility for non image file formats
	public static BufferedImage getMediaItemImage(Path mi) {
		try {
			return ImageIO.read(MediaHandler.getFullRelativePath(mi).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
}
