package gui.components.context_menu;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JPopupMenu;

import clipboard.ImageSelection;
import media_control.MediaHandler;

public class MediaItemContextMenu extends JPopupMenu {

	/* Context menu when right clicking a media item */

	private Path mediaItem;
	private Transferable copyItem;
	
	public MediaItemContextMenu(Path mediaItem, Transferable copyItem) {
		super("Context Menu");
		this.mediaItem = mediaItem;
		this.copyItem = copyItem;
		
		// TODO: add compatibility for more than just images
	
	}
	
	// copy file
	public void addChoiceCopy() {
		MediaItemContextMenuChoice cCopy = new MediaItemContextMenuChoice("Copy", this);
		cCopy.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	Toolkit tk = Toolkit.getDefaultToolkit();
            	Clipboard clipboard = tk.getSystemClipboard();
            	
            	// copies copyItem to clipboard
				clipboard.setContents(copyItem, null);
				System.out.println("Copied file to clipboard");         
            }                 
         });
		this.add(cCopy);
	}
	
	// open file location
	public void addChoiceOpenFileLoc() {
		MediaItemContextMenuChoice cOpenFileLoc = new MediaItemContextMenuChoice("Open file location", this);
		cOpenFileLoc.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	try {
					Desktop.getDesktop().open(new File(MediaHandler.getFullRelativeFileLocation(mediaItem).toString()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }                 
         });
		this.add(cOpenFileLoc);
	}

}
