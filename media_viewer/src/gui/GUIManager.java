package gui;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;

public class GUIManager {
	
	private static GraphicsFrame appFrame;
	
	private static Path selectedMediaItem;
	
	private static BufferedImage selectedMediaItemImage;
	
	// default values for certain fields in the gui
	private static HashMap<String, Object> defaultValues;
	
	public static void init() {
		defaultValues = new HashMap<String, Object>();
		appFrame = GraphicsFrame.init(defaultValues);
		
	}
	
	// updates default values, except for the image
	public static void updateDefaultValues() {
		// TODO
	}

	
	
}
