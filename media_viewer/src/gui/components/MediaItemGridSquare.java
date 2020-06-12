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
	private MediaItemDisplayLabel displayLabel;
	
	public MediaItemGridSquare(Path mi) {
		setLayout(new BorderLayout(0, 0));
		
		nameLabel = new JLabel(mi.getFileName().toString());
		add(nameLabel, BorderLayout.NORTH);
		
		displayLabel = MediaItemDisplayLabel.makeDisplayLabel(mi);
		add(displayLabel, BorderLayout.SOUTH);
		
		displayLabel.setDisplaySize(256, 256, false);
		
		displayLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		displayLabel.addMouseListener(new MouseAdapter() {

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
