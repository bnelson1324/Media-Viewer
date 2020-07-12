package gui.components.media_display;

import java.awt.Dimension;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import media_control.MediaHandler;

public class TextDisplayPanel extends MediaDisplayPanel {	
	
	protected JTextPane textPane;
	
	protected String mediaItemText;
	
	protected JScrollPane scrollPane;
	
	protected TextDisplayPanel(Path mediaItem, boolean addScrollPane) {
		super(mediaItem);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		// adds the mouse listener to open context menu
		textPane.addMouseListener(contextMenu.contextMenuOpener);

		mediaItemText = "";

		try {
			Scanner sc = new Scanner(MediaHandler.getFullRelativePath(mediaItem).toFile());
			while(sc.hasNextLine()) {
				mediaItemText += sc.nextLine();
				if(sc.hasNextLine()) {
					mediaItemText += "\n";
				}
			}
			textPane.setText(mediaItemText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		if(addScrollPane) {
			scrollPane = new JScrollPane(textPane);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(scrollPane);
		} else {
			this.add(textPane);
		}
	}

	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		super.setDisplaySize(width, height, keepAspectRatio);
		if(scrollPane != null) {
			scrollPane.setPreferredSize(new Dimension(width, height));
		} else {
			textPane.setPreferredSize(new Dimension(width, height));
		}
	}
	
	@Override
	protected void createCopyItem() {
		String copyStr = "";
		
		try {
			Scanner sc = new Scanner(MediaHandler.getFullRelativePath(mediaItem).toFile());
			while(sc.hasNextLine()) {
				copyStr += sc.nextLine();
				if(sc.hasNextLine()) {
					copyStr += "\n";
				}
			}
			copyItem = new StringSelection(copyStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void createContextMenuChoices() {
		contextMenu.addChoiceCopy();
		contextMenu.addChoiceOpenFileLoc();
	}

	@Override
	public JComponent getDisplayComponent() {
		return textPane;
	}
}