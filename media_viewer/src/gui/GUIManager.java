package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gui.components.tabs.Tab;
import media.MediaData;
import media_control.MediaHandler;

public class GUIManager {
	
	private static GraphicsFrame appFrame;
	
	// currently selected media item (for view and modify tags tab) (smi)
	private static Path selectedMediaItem;
	
	private static BufferedImage selectedMediaItemImage;
	
	// default values for certain fields in the gui
	private static HashMap<String, Object> defaultValues;
	
	public static void init() {
		defaultValues = new HashMap<String, Object>();
		appFrame = GraphicsFrame.init(defaultValues);
		updateDefaultValues();
	}
	
	public static void changeSelectedMediaItem(Path mi) {
		selectedMediaItem = mi;
		try {
			selectedMediaItemImage = ImageIO.read(MediaHandler.getFullRelativePath(mi).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			defaultValues.put("smiAuthorTags", md.getGenericTags());
		} else {
			defaultValues.put("smi", "");
			defaultValues.put("smiImage", null);
			defaultValues.put("smiName", "");
			defaultValues.put("smiDateCreated", "");
			defaultValues.put("smiDateAdded", "");
			defaultValues.put("smiAuthorName", "");
			defaultValues.put("smiAuthorLinks", "");
			defaultValues.put("smiAuthorTags", "");
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
	
	
}
