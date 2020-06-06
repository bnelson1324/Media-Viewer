package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	// TODO: separate methods by which pane they are used in
	
	// default values for components in the graphics frame
	private static HashMap<String, String> defaultValues;
	
	// selected media item, shown in view and modify tags
	static MediaItem selectedMediaItem;
	
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
		if(selectedMediaItem != null) {
			defaultValues.put("selectedMediaItemFileLocation", selectedMediaItem.getPath().toString());
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

	// TODO: add event listener to panels to view media item on clicking
	// adds image icons to a grid
	public static ArrayList<MediaItemPanel> textFieldSearch(String query) {
		ArrayList<MediaItem> passingItems = MediaHandler.getMediaItemsByTag(query);
		ArrayList<MediaItemPanel> panelList = new ArrayList<MediaItemPanel>();
		for(MediaItem mi : passingItems) {
			JLabel nameLabel = new JLabel(mi.getPath().getFileName().toString());
			nameLabel.setFont(new Font("Label.font", Font.PLAIN, 16));
			JLabel imgLabel = new JLabel(getMediaItemGridIcon(mi));
			
			MediaItemPanel miPanel = new MediaItemPanel(mi);
			miPanel.setLayout(new BorderLayout());
			miPanel.add(nameLabel, BorderLayout.NORTH);
			miPanel.add(imgLabel, BorderLayout.SOUTH);
			miPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
			panelList.add(miPanel);
		}
		
		return panelList;
		
	}
	
	// TODO: add compatibility with non-image file formats
	public static ImageIcon getMediaItemFullIcon(MediaItem mi) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(SettingsHandler.getSetting("rootStorageFolderLoc") + "\\" + mi.getPath().toString()));
			return new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// TODO: add compatibility with non-image file formats
	public static ImageIcon getMediaItemGridIcon(MediaItem mi) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(SettingsHandler.getSetting("rootStorageFolderLoc") + "\\" + mi.getPath().toString()));
			Image resizedImg = img.getScaledInstance(256, 256, Image.SCALE_FAST);
			
			return new ImageIcon(resizedImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateMediaDisplay(JLabel imgViewMedia) {
		if(selectedMediaItem != null) {
			imgViewMedia.setIcon(GUIHandler.getMediaItemFullIcon(selectedMediaItem));
		}
	}
}
