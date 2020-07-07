package gui.components.media_display;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	protected void createCopyItem() {
		copyItem = null;
	}
	
	@Override
	protected void createContextMenuChoices() {
		contextMenu.addChoiceOpenFileLoc();
	}

}
