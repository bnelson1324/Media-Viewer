package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
	public static ArrayList<JPanel> textFieldSearch(String query) {
		ArrayList<MediaItem> passingItems = MediaHandler.getMediaItemsByTag(query);
		ArrayList<JPanel> panelList = new ArrayList<JPanel>();
		for(MediaItem mi : passingItems) {
			JLabel nameLabel = new JLabel(mi.getPath().getFileName().toString());
			nameLabel.setFont(new Font("Label.font", Font.PLAIN, 14));
			JLabel imgLabel = new JLabel(prepareMediaItemForDisplay(mi));
			
			JPanel pnl = new JPanel();
			pnl.setLayout(new BorderLayout());
			pnl.add(nameLabel, BorderLayout.NORTH);
			pnl.add(imgLabel, BorderLayout.SOUTH);
			panelList.add(pnl);
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
