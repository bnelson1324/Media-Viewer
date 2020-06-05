package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import media.MediaItem;
import media_control.MediaHandler;
import settings.SettingsHandler;
import settings.SettingsSaver;

public class GUIHandler {

	// default values for components in the graphics frame
	private static HashMap<String, String> defaultValues;
	
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
	
	// TODO
	public static ArrayList<JLabel> textFieldSearch(String query) {
		ArrayList<MediaItem> passingItems = MediaHandler.getMediaItemsByTag(query);
		ArrayList<JLabel> panelList = new ArrayList<JLabel>();
		for(MediaItem mi : passingItems) {
			JLabel label = new JLabel(prepareMediaItemForDisplay(mi));
			
			panelList.add(label);
		}
		
		return panelList;
		
	}
	
	// TODO
	public static ImageIcon prepareMediaItemForDisplay(MediaItem mi) {
		BufferedImage img;
		try {

			img = ImageIO.read(new File(SettingsHandler.getSetting("rootStorageFolderLoc") + "\\" + mi.getPath().toString()));
			Image resizedImg = img.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
