package temp_delete_l8r.gui.components;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import temp_delete_l8r.OldGUIHandler;

public class MediaItemSearchPanel extends JPanel {

	/* This class is used to display a file name and image in the search grid */
	
	private JLabel nameLabel;
	private MediaItemDisplayLabel imageLabel;
	
	private Path displayedMediaItemPath;
	
	public MediaItemSearchPanel(Path displayedMediaItemPath) {
		this.displayedMediaItemPath = displayedMediaItemPath;
		
		nameLabel = new JLabel(displayedMediaItemPath.getFileName().toString());
		nameLabel.setFont(new Font("Label.font", Font.PLAIN, 16));
		imageLabel = new MediaItemDisplayLabel(OldGUIHandler.getMediaItemGridIcon(displayedMediaItemPath), displayedMediaItemPath);
		
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
	
	public MediaItemDisplayLabel getImageLabel() {
		return imageLabel;
	}
	
}
