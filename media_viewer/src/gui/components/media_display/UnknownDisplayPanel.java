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
			mediaItemImage = ImageIO.read(new File("res/image/unknown_display_img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageLabel.setIcon(new ImageIcon());
		readyToRender = true;
	}

	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		// sets max size as 256x256
		int newWidth = Math.min(width, 256);
		int newHeight = Math.min(height, 256);
		
		super.setDisplaySize(newWidth, newHeight, keepAspectRatio);
	}

	@Override
	public void addContextMenu() {
		// TODO Auto-generated method stub
		// have open file location button but not copy button
	}

}
