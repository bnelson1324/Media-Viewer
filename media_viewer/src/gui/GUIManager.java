package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ImageIcon;

import gui.components.MediaItemDisplayLabel;
import gui.components.tabs.Tab;
import media.MediaData;
import media_control.MediaHandler;
import media_control.MediaSaver;
import settings.SettingsHandler;
import settings.SettingsSaver;

public class GUIManager {
	
	// TODO: organize this class, separating methods by which tab they are used in
	
	private static GraphicsFrame appFrame;
	
	// currently selected media item (for view and modify tags tab) (smi)
	private static Path selectedMediaItem;
	
	// TODO: maybe make this a selected MediaItemDisplayGrid to incorporate things other than images
	private static BufferedImage selectedMediaItemImage;
	
	// default values for the selectedMediaItem
	private static HashMap<String, Object> defaultValues;
	
	public static void init() {
		defaultValues = new HashMap<String, Object>();
		appFrame = GraphicsFrame.init(defaultValues);
		updateDefaultValues();
		defaultValues.put("currentSearch", "");
	}
	
	public static void changeSelectedMediaItem(Path mi) {
		selectedMediaItem = mi;
		selectedMediaItemImage = MediaItemDisplayLabel.getMediaItemImage(mi);
		updateDefaultValues();
	}
	
	// updates default values
	private static void updateDefaultValues() {
		if(selectedMediaItem != null) {
			defaultValues.put("smi", selectedMediaItem);
			defaultValues.put("smiImage", selectedMediaItemImage);
			MediaData md = MediaHandler.getMediaDataByPath(selectedMediaItem);
			defaultValues.put("smiName", md.getName());
			defaultValues.put("smiDateCreated", md.getDateCreated());
			defaultValues.put("smiDateAdded", md.getDateAdded());
			defaultValues.put("smiAuthorName", md.getAuthorName());
			defaultValues.put("smiAuthorLinks", md.getAuthorLinks());
			defaultValues.put("smiTags", md.getGenericTags());	
		} else {
			defaultValues.put("smi", "");
			defaultValues.put("smiImage", null);
			ArrayList<String> emptyArr = new ArrayList<String>();
			defaultValues.put("smiName", emptyArr);
			defaultValues.put("smiDateCreated", emptyArr);
			defaultValues.put("smiDateAdded", emptyArr);
			defaultValues.put("smiAuthorName", emptyArr);
			defaultValues.put("smiAuthorLinks", emptyArr);
			defaultValues.put("smiTags", emptyArr);
		}
	}
	
	// scales an image keeping the aspect ratio
	public static ImageIcon scaleKeepingAspectRatio(BufferedImage img, int width, int height) {
		double newWidth, newHeight;

		// scaled to width
		double sWMultiplier = (((double)width)/img.getWidth());

		// scaled to height
		double sHMultiplier = (((double)height)/img.getHeight());
		
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

	public static void updateSelectedTab() {
		 appFrame.getSelectedTab().updateTab();
	}
	
	
	// converts arraylist of tags into a string
	public static String unpackTagList(ArrayList<String> tagList, String regex) {
		if(tagList.size() == 0) {
			// so removing last regex doesn''t result in out of string index bounds exception
			return "";
		}
		
		String tagStr = "";
		for(String tag : tagList) {
			tagStr += tag + regex;
		}
		// removes last regex
		return tagStr.substring(0, tagStr.length() - regex.length());
	}
	
	// converts string into an arraylist of tags
	public static ArrayList<String> packTagList(String tagStr, String regex) {
		String[] tagArr = tagStr.split(regex);
		return new ArrayList<String>(Arrays.asList(tagArr));
	}

	public static void saveTags(String name, String dateCreated, String dateAdded, String authorName, String authorLinks, String tags) {
		if(selectedMediaItem == null) {
			return;
		}
		ArrayList<String> aName = packTagList(name, ", ");
		ArrayList<String> aDateCreated = packTagList(dateCreated, ", ");
		ArrayList<String> aDateAdded = packTagList(dateAdded, ", ");
		ArrayList<String> aAuthorName = packTagList(authorName, ", ");
		ArrayList<String> aAuthorLinks = packTagList(authorLinks, ", ");
		ArrayList<String> aTags = packTagList(tags, ", ");
		System.out.println(aTags);
		
		MediaData md = new MediaData(aName, aDateCreated, aDateAdded, aAuthorName, aAuthorLinks, aTags);
		MediaHandler.pairMediaData(selectedMediaItem, md);
		
		updateDefaultValues();
		
		MediaSaver.saveMediaData();
	}
	
	
	public static void setSelectedTabIndex(int n) {
		appFrame.setSelectedTabIndex(n);
	}
}
