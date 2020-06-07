package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.components.MediaItemSearchPanel;
import media.MediaData;
import media_control.MediaHandler;
import media_control.MediaSaver;
import settings.SettingsHandler;
import settings.SettingsSaver;

public class GUIHandler {

	// default values for components in the graphics frame
	private static HashMap<String, String> defaultValues;
	
	// path to selected media item, shown in view and modify tags
	public static Path selectedMediaItem;
	
	
	/* -- All Panes -- */

	public static HashMap<String, String> getDefaultValues() {
		return defaultValues;
	}

	public static void loadDefaultValues() {
		defaultValues.put("settingsRootStorageLoc", SettingsHandler.getSetting("rootStorageFolderLoc"));
		if(selectedMediaItem != null) {
			MediaData selectedMediaItemData = MediaHandler.getMediaDataByPath(selectedMediaItem);
			defaultValues.put("selectedMediaItemFileLocation", selectedMediaItem.toString());
			defaultValues.put("selectedMediaItemName", tagArrayListAsStr(selectedMediaItemData.getName(), ";"));
			defaultValues.put("selectedMediaItemDateCreated", tagArrayListAsStr(selectedMediaItemData.getDateCreated(), ";"));
			defaultValues.put("selectedMediaItemDateAdded", tagArrayListAsStr(selectedMediaItemData.getDateAdded(), ";"));
			defaultValues.put("selectedMediaItemAuthorName", tagArrayListAsStr(selectedMediaItemData.getAuthorName(), ";"));
			defaultValues.put("selectedMediaItemAuthorLinks", tagArrayListAsStr(selectedMediaItemData.getAuthorLinks(), ";"));
			defaultValues.put("selectedMediaItemTags", tagArrayListAsStr(selectedMediaItemData.getGenericTags(), ";"));
			
		} else {
			defaultValues.put("selectedMediaItemFileLocation", "");
			defaultValues.put("selectedMediaItemName", "");
			defaultValues.put("selectedMediaItemDateCreated", "");
			defaultValues.put("selectedMediaItemDateAdded", "");
			defaultValues.put("selectedMediaItemAuthorName", "");
			defaultValues.put("selectedMediaItemAuthorLinks", "");
			defaultValues.put("selectedMediaItemTags", "");
		}
	}
	
	/* -- Search -- */
	
	// adds image icons to a grid
	public static ArrayList<MediaItemSearchPanel> textFieldSearch(String query) {
		ArrayList<Path> passingItems = MediaHandler.getMediaItemsByTag(query);
		ArrayList<MediaItemSearchPanel> panelList = new ArrayList<MediaItemSearchPanel>();
		for(Path p : passingItems) {
			
			MediaItemSearchPanel miPanel = new MediaItemSearchPanel(p);
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
	
	

	public static void updateMediaItemPanel(JLabel mediaDisplayPanel, int width, int height) {
		if(selectedMediaItem != null) {
			mediaDisplayPanel.setIcon(GUIHandler.getMediaItemFullIcon(selectedMediaItem, new Dimension(width, height)));
		}
	}
	
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
		} else {
			// scale to height
			newWidth = img.getWidth() * sHMultiplier;
			newHeight = img.getHeight() * sHMultiplier;
		}
		
		
		Image resizedImg = img.getScaledInstance((int)newWidth, (int)newHeight, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	
	// converts arraylist to str separated by a regex
	private static String tagArrayListAsStr(ArrayList a, String regex) {
		if(a.size() == 0) {
			return "";
		}
		String str = "";
		for(Object obj : a) {
			str += obj.toString();
			str += regex;
		}
		return str.substring(0, str.length()-regex.length());
	}
	
	// saves text in the modifyTags page as current tags, and saves media data to mediaDataStorage.json
	public static void btnSaveTags(String name, String dateCreated, String dateAdded, String authorName, String authorLinks, String tags) {
		ArrayList<String> aName = new ArrayList<String>(Arrays.asList(name.split(";")));
		ArrayList<String> aDateCreated = new ArrayList<String>(Arrays.asList(dateCreated.split(";")));
		ArrayList<String> aDateAdded = new ArrayList<String>(Arrays.asList(dateAdded.split(";")));
		ArrayList<String> aAuthorName = new ArrayList<String>(Arrays.asList(authorName.split(";")));
		ArrayList<String> aAuthorLinks = new ArrayList<String>(Arrays.asList(authorLinks.split(";")));
		ArrayList<String> aTags = new ArrayList<String>(Arrays.asList(tags.split(";")));
		
		MediaData md = new MediaData(aName, aDateCreated, aDateAdded, aAuthorName, aAuthorLinks, aTags);
		MediaHandler.pairMediaData(selectedMediaItem, md);
		
		MediaSaver.saveMediaData();
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
	
	
	public static void init() {
		defaultValues = new HashMap<String, String>();
		loadDefaultValues();
	
		GraphicsFrame.runFrame();
		
		
	}

	
}
