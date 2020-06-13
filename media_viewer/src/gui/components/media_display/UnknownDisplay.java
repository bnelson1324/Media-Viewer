package gui.components.media_display;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UnknownDisplay extends MediaDisplayPanel {

	private JLabel unknownImageLabel;
	
	// TODO: 
	
	protected UnknownDisplay(Path mi) {
		super(mi, "unknown");
		
		unknownImageLabel = new JLabel();
		
		try {
			File unknownImageFile = new File("res/unknown_display_img.png");
			unknownImageLabel.setIcon(new ImageIcon(ImageIO.read(unknownImageFile)));
			this.add(unknownImageLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addContextMenu() {
		// TODO Auto-generated method stub
		
	}

}
