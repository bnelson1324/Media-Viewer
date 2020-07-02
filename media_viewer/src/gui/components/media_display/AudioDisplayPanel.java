package gui.components.media_display;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gui.GUIManager;

public class AudioDisplayPanel extends ImageDisplayPanel {

	protected AudioDisplayPanel(Path mediaItem) {
		super(mediaItem, false);
		
		try {
			mediaItemImage = ImageIO.read(new File("res/image/audio_display_img.png"));
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
