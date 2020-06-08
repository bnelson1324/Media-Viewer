package gui.components;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.components.context_menu.MediaItemContextMenu;

public class MediaItemDisplayLabel extends JLabel {

	/* This is a JLabel displaying a media item */
	
	private Path mediaItem;
	
	private MediaItemContextMenu contextMenu;
	
	public MediaItemDisplayLabel(ImageIcon img, Path mediaItem) {
		super(img);
		
		contextMenu = new MediaItemContextMenu(null);
		this.add(contextMenu);
		
		updateContextMenu();
	
	}
	
	public void setMediaItem(Path mi) {
		this.mediaItem = mi;
		updateContextMenu();
	}
	
	private void updateContextMenu() {
		removeExistingContextMenus();
		contextMenu = new MediaItemContextMenu(mediaItem);
		
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
		
		this.add(contextMenu);
	}
	
	private void removeExistingContextMenus() {
		for(Component c : this.getComponents()) {
			if(c instanceof MediaItemContextMenu) {
				this.remove(c);
			}
		}
	}
	
}
