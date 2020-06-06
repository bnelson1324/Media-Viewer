package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import media.MediaData;
import media.MediaItem;
import media_control.MediaHandler;

public class MediaFileLocationTextBox extends JTextField {

	// jlabel that contains the image of the current media item
	private JLabel managedJLabel;
	
	// component that helps determine the size the managedJLabel should be
	private JComponent sizeComponent;
	
	public MediaFileLocationTextBox(JLabel managedJLabel, JComponent sizeComponent) {
		super();
		this.managedJLabel = managedJLabel;
		this.sizeComponent = sizeComponent;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO: stop image from cropping
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					GUIHandler.selectedMediaItemPath = getMediaItem().getPath();
					GUIHandler.updateMediaItemPanel(managedJLabel, sizeComponent.getSize());
					
				}
			}
		});
	}
	
	public MediaItem getMediaItem() {
		return MediaHandler.getMediaItemByPath(Paths.get(this.getText()));
	}
	
	public MediaData getMediaData() {
		return MediaHandler.getMediaDataByPath(Paths.get(this.getText()));
	}
}
