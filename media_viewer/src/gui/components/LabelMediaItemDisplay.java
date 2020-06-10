package gui.components;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.GUIManager;

public class LabelMediaItemDisplay extends JLabel {
	
	/* JLabel containing a media item */

	
	
	private Path mediaItem;
	
	private BufferedImage mediaItemImage;
	
	// TODO: add context menu
	
	public void setMediaItem(Path mi, BufferedImage miImg) {
		this.mediaItem = mi;
		this.mediaItemImage = miImg;
		if(mediaItemImage != null) {
			this.setIcon(new ImageIcon(mediaItemImage));
		}
		//updateContextMenu();
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
	
}
