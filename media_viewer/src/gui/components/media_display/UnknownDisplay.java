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

public class UnknownDisplay extends MediaDisplayPanel {

	private JLabel unknownImageLabel;
	
	private BufferedImage unknownImage;
	
	// TODO
	
	protected UnknownDisplay(Path mi) {
		super(mi, "unknown");
		
		unknownImageLabel = new JLabel();
		
		try {
			unknownImage = ImageIO.read(new File("res/unknown_display_img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		unknownImageLabel.setIcon(new ImageIcon());
		
		this.add(unknownImageLabel);
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
			unknownImageLabel.setIcon(GUIManager.scaleKeepingAspectRatio(unknownImage, width, height));
		} else {
			unknownImageLabel.setIcon(new ImageIcon(unknownImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		}
	}

	@Override
	protected void addContextMenu() {
		// TODO Auto-generated method stub
		
	}

}
