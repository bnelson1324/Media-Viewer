package gui.components;

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

public class TextBoxFileLocation extends JTextField {

	public TextBoxFileLocation() {
		super();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// TODO: change selected media item & stuff
				}
			}
		});
	}
	
	public Path getFileLocation() {
		return Paths.get(this.getText());
	}
}
