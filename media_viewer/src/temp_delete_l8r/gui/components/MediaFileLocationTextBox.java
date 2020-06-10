package temp_delete_l8r.gui.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import media.MediaData;
import media_control.MediaHandler;
import temp_delete_l8r.GUIHandler;

public class MediaFileLocationTextBox extends JTextField {

	// jlabel that contains the image of the current media item
	private MediaItemDisplayLabel managedMediaItemDisplayLabel;
	
	// component that helps determine the size the managedJLabel should be
	private JComponent sizeComponent;
	
	public MediaFileLocationTextBox(MediaItemDisplayLabel managedMediaItemDisplayLabel, JComponent sizeComponent) {
		super();
		this.managedMediaItemDisplayLabel = managedMediaItemDisplayLabel;
		this.sizeComponent = sizeComponent;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					GUIHandler.selectedMediaItem = getMediaItem();
					managedMediaItemDisplayLabel.setMediaItem(getMediaItem());
					GUIHandler.updateSelectedMediaItemImage();
					GUIHandler.updateMediaItemPanel(managedMediaItemDisplayLabel, sizeComponent.getWidth(), sizeComponent.getHeight());
				}
			}
		});
	}
	
	public Path getMediaItem() {
		return Paths.get(this.getText());
	}
	
	public MediaData getMediaData() {
		return MediaHandler.getMediaDataByPath(Paths.get(this.getText()));
	}
}
