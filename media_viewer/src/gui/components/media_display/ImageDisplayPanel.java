package gui.components.media_display;

import java.awt.BorderLayout;
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

public class ImageDisplayPanel extends MediaDisplayPanel {
	
	private JLabel imageLabel;
	
	private BufferedImage mediaItemImage;
	
	protected ImageDisplayPanel(Path mi) {
		super(mi, "image");
		
		imageLabel = new JLabel();
		
		try {
			mediaItemImage = ImageIO.read(MediaHandler.getFullRelativePath(mi).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		imageLabel.setIcon(new ImageIcon(mediaItemImage));	
		
		this.add(imageLabel);
	}
	
	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		if(keepAspectRatio) {
			imageLabel.setIcon(GUIManager.scaleKeepingAspectRatio(mediaItemImage, width, height));
		} else {
			imageLabel.setIcon(new ImageIcon(mediaItemImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		}
	}
	
	
	@Override
	public void addContextMenu() {
		contextMenu = new MediaItemContextMenu(mediaItem);
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

	
}
