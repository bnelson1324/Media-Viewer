package gui.components.context_menu;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;

public class MediaItemContextMenuChoice extends JMenuItem {
	
	MediaItemContextMenu parentMenu;
	
	MediaItemContextMenuChoice(String text, MediaItemContextMenu parentMenu) {
		super(text);
		this.parentMenu = parentMenu;
		
		MediaItemContextMenuChoice choice = this;
		Color originalBGColor = choice.getBackground();
		
		this.addMouseListener(new MouseAdapter() { 
			// highlights mouse when hovering over choice
			
            public void mouseEntered(MouseEvent e) {              
                choice.setBackground(Color.WHITE);
            } 
            
            public void mouseExited(MouseEvent e) {              
            	choice.setBackground(originalBGColor);
            } 
            
            
            // closes menu after selecting a choice
            
            public void mouseClicked(MouseEvent e) {
            	choice.parentMenu.setVisible(false);
            }
         }); 
	}
	
}
