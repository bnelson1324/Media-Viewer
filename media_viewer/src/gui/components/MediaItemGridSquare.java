package gui.components;

import java.nio.file.Path;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIManager;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MediaItemGridSquare extends JPanel {

	/* JPanel representing one grid square in the search tab. Displays a file name and an image */
	
	private JLabel nameLabel;
	private MediaItemDisplayLabel imageLabel;
	
	public MediaItemGridSquare(Path mi) {
		setLayout(new BorderLayout(0, 0));
		
		nameLabel = new JLabel(mi.getFileName().toString());
		add(nameLabel, BorderLayout.NORTH);
		
		imageLabel = new MediaItemDisplayLabel();
		add(imageLabel, BorderLayout.SOUTH);
		
		imageLabel.setMediaItem(mi, GUIManager.getMediaItemImage(mi));
		imageLabel.setImageSize(256, 256, false);
		
		imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		imageLabel.addMouseListener(new MouseAdapter() {

			// detects if should open context menu
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					GUIManager.changeSelectedMediaItem(mi);
					GUIManager.setSelectedTabIndex(1);
				} 
			}
		});
		
	}
	
}
