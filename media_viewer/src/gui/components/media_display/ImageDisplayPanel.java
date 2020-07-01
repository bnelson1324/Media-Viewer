package gui.components.media_display;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import clipboard.ImageSelection;
import gui.GUIManager;
import gui.components.context_menu.MediaItemContextMenu;
import media_control.MediaHandler;

public class ImageDisplayPanel extends MediaDisplayPanel {
	
	protected JLabel imageLabel;
	
	protected BufferedImage mediaItemImage;
	
	// boolean saying if panel is ready to render
	protected boolean readyToRender;
	
	protected ImageDisplayPanel(Path mediaItem, boolean createImage) {
		super(mediaItem);
		readyToRender = false;
		
		imageLabel = new JLabel();
		
		if(createImage) {
			try {
				mediaItemImage = ImageIO.read(MediaHandler.getFullRelativePath(mediaItem).toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			imageLabel.setIcon(new ImageIcon(mediaItemImage));
			readyToRender = true;
		}
		
		this.add(imageLabel);
	}
	
	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		super.setDisplaySize(width, height, keepAspectRatio);
		if(readyToRender) {
			if(keepAspectRatio) {
				imageLabel.setIcon(GUIManager.scaleKeepingAspectRatio(mediaItemImage, width, height));
			} else {
				imageLabel.setIcon(new ImageIcon(mediaItemImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		}
	}
	
	@Override
	protected void addContextMenu() {
		super.addContextMenu();
		
		contextMenu.addChoiceCopy();
		contextMenu.addChoiceOpenFileLoc();
	}

	@Override
	protected void createCopyItem() {
		try {
			copyItem = new ImageSelection(ImageIO.read(new File(MediaHandler.getFullRelativePath(mediaItem).toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
