package gui;

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

	// TODO: separate methods w/ comments by which pane they are used in
	
	// default values for components in the graphics frame
	private static HashMap<String, String> defaultValues;
	
	// path to selected media item, shown in view and modify tags
	static Path selectedMediaItemPath;
	
	public static void init() {
		defaultValues = new HashMap<String, String>();
		loadDefaultValues();
		
		GraphicsFrame.runFrame();
		
	}

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
	public static ImageIcon getMediaItemFullIcon(Path path) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(SettingsHandler.getSetting("rootStorageFolderLoc") + "\\" + path.toString()));
			return new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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

	public static void updateMediaDisplay(JLabel imgViewMedia) {
		if(selectedMediaItemPath != null) {
			imgViewMedia.setIcon(GUIHandler.getMediaItemFullIcon(selectedMediaItemPath));
		}
	}
}
