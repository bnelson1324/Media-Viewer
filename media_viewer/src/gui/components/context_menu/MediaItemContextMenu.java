package gui.components.context_menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MediaItemContextMenu extends JPopupMenu {

	/* Context menu when right clicking a media item */
	
	public MediaItemContextMenu() {
		super("Context Menu");
		
		MediaItemContextMenuChoice copy = new MediaItemContextMenuChoice("Copy");
		MediaItemContextMenuChoice temp = new MediaItemContextMenuChoice("Open file location");
		
		// menu choices' listeners
		
		copy.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {    
            	// TODO
                System.out.println("Copied file to clipboard"); 
            }                 
         });  
		
		
		this.add(copy);
		this.add(temp);
	}

}
