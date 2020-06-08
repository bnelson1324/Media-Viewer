package gui.components;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIHandler;

public class MediaItemSearchPanel extends JPanel {

	/* This class is used to display a file name and image in the search grid */
	
	private JLabel nameLabel;
	private MediaItemDisplayLabel imageLabel;
	
	private Path displayedMediaItemPath;
	
	public MediaItemSearchPanel(Path displayedMediaItemPath) {
		this.displayedMediaItemPath = displayedMediaItemPath;
		
		nameLabel = new JLabel(displayedMediaItemPath.getFileName().toString());
		nameLabel.setFont(new Font("Label.font", Font.PLAIN, 16));
		imageLabel = new MediaItemDisplayLabel(GUIHandler.getMediaItemGridIcon(displayedMediaItemPath));
		
		this.setLayout(new BorderLayout());
		this.add(nameLabel, BorderLayout.NORTH);
		this.add(imageLabel, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));	
	}
	
	public Path getDisplayedMediaItemPath() {
		return displayedMediaItemPath;
	}
	
	JLabel getNameLabel() {
		return nameLabel;
	}
	
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
}
