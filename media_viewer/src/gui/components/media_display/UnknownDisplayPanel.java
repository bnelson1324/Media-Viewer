package gui.components.media_display;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.GUIManager;

public class UnknownDisplayPanel extends ImageDisplayPanel {
	
	protected UnknownDisplayPanel(Path mediaItem) {
		super(mediaItem, false);
		
		try {
			mediaItemImage = ImageIO.read(new File("res/unknown_display_img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageLabel.setIcon(new ImageIcon());
	}

	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		// sets max size as 256x256
		if(width > 256) {
			width = 256;
		}
		if(height > 256) {
			height = 256;
		}
		
		if(keepAspectRatio) {
			imageLabel.setIcon(GUIManager.scaleKeepingAspectRatio(mediaItemImage, width, height));
		} else {
			imageLabel.setIcon(new ImageIcon(mediaItemImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		}
	}

	@Override
	public void addContextMenu() {
		// TODO Auto-generated method stub
		// have open file location button but not copy button
	}

}
