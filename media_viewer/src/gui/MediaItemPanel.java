package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import media.MediaItem;

public class MediaItemPanel extends JPanel {

	/* This class is used to display a file name and image in the search grid */
	
	private JLabel nameLabel;
	private JLabel imageLabel;
	
	private Path displayedMediaItemPath;
	
	MediaItemPanel(Path displayedMediaItemPath) {
		this.displayedMediaItemPath = displayedMediaItemPath;
		
		nameLabel = new JLabel(displayedMediaItemPath.getFileName().toString());
		nameLabel.setFont(new Font("Label.font", Font.PLAIN, 16));
		imageLabel = new JLabel(GUIHandler.getMediaItemGridIcon(displayedMediaItemPath));
		imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.setLayout(new BorderLayout());
		this.add(nameLabel, BorderLayout.NORTH);
		this.add(imageLabel, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		
		
	}
	
	Path getDisplayedMediaItemPath() {
		return displayedMediaItemPath;
	}
	
	JLabel getNameLabel() {
		return nameLabel;
	}
	
	JLabel getImageLabel() {
		return imageLabel;
	}
	
}
