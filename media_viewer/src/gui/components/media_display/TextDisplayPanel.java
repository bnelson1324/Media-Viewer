package gui.components.media_display;

import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.JTextPane;

import media_control.MediaHandler;

public class TextDisplayPanel extends MediaDisplayPanel {	
	
	protected JTextPane textPane;
	
	protected String mediaItemText;
	
	protected TextDisplayPanel(Path mediaItem) {
		super(mediaItem);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		// removes all mouse listeners and adds the one to open context menu
		for(MouseListener ml : textPane.getMouseListeners()) {
			textPane.removeMouseListener(ml);
			
		}
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
	
		this.add(textPane);
	}

	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		super.setDisplaySize(width, height, keepAspectRatio);
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
}
