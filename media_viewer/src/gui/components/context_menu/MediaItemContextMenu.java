package gui.components.context_menu;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JPopupMenu;

import clipboard.ImageSelection;
import gui.components.MediaItemDisplayLabel;
import media_control.MediaHandler;

public class MediaItemContextMenu extends JPopupMenu {

	/* Context menu when right clicking a media item */

	private Path mediaItem;
	
	public MediaItemContextMenu(Path mediaItem) {
		super("Context Menu");
		this.mediaItem = mediaItem;
		
		MediaItemContextMenuChoice cCopy = new MediaItemContextMenuChoice("Copy", this);
		MediaItemContextMenuChoice cOpenFileLoc = new MediaItemContextMenuChoice("Open file location", this);
		
		// menu choices' listeners
		
		// copy file
		// TODO: add compatibility for more than just images
		cCopy.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	Toolkit tk = Toolkit.getDefaultToolkit();
            	Clipboard clipboard = tk.getSystemClipboard();
            	
            	// copies image
				try {
					ImageSelection imgSel = new ImageSelection(ImageIO.read(new File(MediaHandler.getFullRelativePath(mediaItem).toString())));
					clipboard.setContents(imgSel, null);
					System.out.println("Copied file to clipboard"); 
				} catch (IOException e1) {
					e1.printStackTrace();
				}         
            }                 
         });  
		
		// open file location
		cOpenFileLoc.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	try {
					Desktop.getDesktop().open(new File(MediaHandler.getFullRelativeFileLocation(mediaItem).toString()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }                 
         });  
		
		
		this.add(cCopy);
		this.add(cOpenFileLoc);
	}

}
