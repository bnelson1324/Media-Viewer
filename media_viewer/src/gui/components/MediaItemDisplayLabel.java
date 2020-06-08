package gui.components;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.components.context_menu.MediaItemContextMenu;

public class MediaItemDisplayLabel extends JLabel {

	/* This is a JLabel displaying a media item */
	
	
	public MediaItemDisplayLabel(ImageIcon img) {
		super(img);
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		MediaItemContextMenu contextMenu = new MediaItemContextMenu();
		this.add(contextMenu);
		
		this.addMouseListener(new MouseAdapter() {

			// detects if should open context menu
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					contextMenu.setVisible(true);
					contextMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
				} 
			}
			
			// detects if should close context menu
			@Override public void mouseEntered(MouseEvent e) {
				if(!contextMenu.contains(e.getPoint())) {
					contextMenu.setVisible(false);
				}
			}
			
			
			
		});
	
	}
	
}
