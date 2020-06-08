package gui.components.context_menu;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;

public class MediaItemContextMenuChoice extends JMenuItem {
	
	MediaItemContextMenuChoice(String text) {
		super(text);
		
		// highlights mouse when hovering over choice
		MediaItemContextMenuChoice choice = this;
		Color originalBGColor = choice.getBackground();
		
		this.addMouseListener(new MouseAdapter() {  
            public void mouseEntered(MouseEvent e) {              
                choice.setBackground(Color.WHITE);
            } 
            
            public void mouseExited(MouseEvent e) {              
            	choice.setBackground(originalBGColor);
            } 
         }); 
	}
	
}
