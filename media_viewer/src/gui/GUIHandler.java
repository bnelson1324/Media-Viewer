package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import media.MediaItem;
import media_control.MediaHandler;
import settings.SettingsHandler;
import settings.SettingsSaver;

public class GUIHandler {

	// default values for components in the graphics frame
	private static HashMap<String, String> defaultValues;
	
	// path to selected media item, shown in view and modify tags
	static Path selectedMediaItemPath;
	
	/* -- All Panes -- */


	public static HashMap<String, String> getDefaultValues() {
		return defaultValues;
	}

	public static void loadDefaultValues() {
		defaultValues.put("settingsRootStorageLoc", SettingsHandler.getSetting("rootStorageFolderLoc"));
		if(selectedMediaItemPath != null) {
			defaultValues.put("selectedMediaItemFileLocation", selectedMediaItemPath.toString());
		} else {
			defaultValues.put("selectedMediaItemFileLocation", "");
		}
	}
	
	/* -- Search -- */
	
	// adds image icons to a grid
	public static ArrayList<MediaItemPanel> textFieldSearch(String query) {
		ArrayList<MediaItem> passingItems = MediaHandler.getMediaItemsByTag(query);
		ArrayList<MediaItemPanel> panelList = new ArrayList<MediaItemPanel>();
		for(MediaItem mi : passingItems) {
			
			MediaItemPanel miPanel = new MediaItemPanel(mi.getPath());
			panelList.add(miPanel);
		}
		
		return panelList;
		
	}
	
	// TODO: add compatibility with non-image file formats
		public static ImageIcon getMediaItemGridIcon(Path path) {
			BufferedImage img;
			try {
				img = ImageIO.read(new File(SettingsHandler.getSetting("rootStorageFolderLoc") + "\\" + path.toString()));
				Image resizedImg = img.getScaledInstance(256, 256, Image.SCALE_FAST);
				
				return new ImageIcon(resizedImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	/* -- View/Modify Tags -- */
	
	// TODO: add compatibility with non-image file formats
	public static ImageIcon getMediaItemFullIcon(Path path, Dimension constraints) {
		BufferedImage img;
		
		try {
			img = ImageIO.read(new File(SettingsHandler.getSetting("rootStorageFolderLoc") + "\\" + path.toString()));
			
			return scaleKeepingAspectRatio(img, constraints);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public static void updateMediaItemPanel(JLabel mediaDisplayPanel) {
		if(selectedMediaItemPath != null) {
			System.out.println("jlabel size:" + mediaDisplayPanel.getSize());
			mediaDisplayPanel.setIcon(GUIHandler.getMediaItemFullIcon(selectedMediaItemPath, mediaDisplayPanel.getSize()));
			System.out.println();
		}
	}

	/* -- Settings -- */
	
	public static void btnSaveSettings(String rootStorageFolderLoc) {
		SettingsHandler.modifySetting("rootStorageFolderLoc", rootStorageFolderLoc);
		SettingsSaver.saveSettings();
		System.out.println("Saved settings");
		MediaHandler.refreshMediaFolder();
	}

	public static void btnResetSettings() {
		File settingsFile = new File("settings/settings.cfg");
		SettingsSaver.copyDefaultSettings(settingsFile);
		// reloads settings
		SettingsHandler.init();
		
		MediaHandler.refreshMediaFolder();
	}
	
	/* -- Misc -- */
	// TODO: make it so size updates when resizing jframe
	/* TODO: fix bug where image only scales up, never shrinks
	 * 		if trying to scale down, new size is always the same as the image already is. for some reason current image size matters
	 * 
	 */
	public static ImageIcon scaleKeepingAspectRatio(BufferedImage img, Dimension constraints) {
		double newWidth, newHeight;
		
		// scaled to width
		double sWMultiplier = (((double)constraints.getWidth())/img.getWidth());

		// scaled to height
		double sHMultiplier = (((double)constraints.getHeight())/img.getHeight());
		
		// scale
		if(sWMultiplier <= sHMultiplier) {	
			// scale to width
			newWidth = img.getWidth() * sWMultiplier;
			newHeight = img.getHeight() * sWMultiplier;
			//System.out.println("w");
		} else {
			// scale to height
			newWidth = img.getWidth() * sHMultiplier;
			newHeight = img.getHeight() * sHMultiplier;
			//System.out.println("h");
		}
		System.out.println("w: "+(int)newWidth + " h:" + (int)newHeight);
		System.out.println("dw: "+(int)constraints.getWidth() + " dh:" + (int)constraints.getHeight());
		
		
		Image resizedImg = img.getScaledInstance((int)newWidth, (int)newHeight, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	
	
	public static void init() {
		defaultValues = new HashMap<String, String>();
		loadDefaultValues();
		
		GraphicsFrame.runFrame();
		
	}

	
}
