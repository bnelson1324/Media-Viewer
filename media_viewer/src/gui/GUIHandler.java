package gui;

import java.io.File;
import java.util.HashMap;

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
	}

	public static void btnResetSettings() {
		File settingsFile = new File("settings/settings.cfg");
		SettingsSaver.copyDefaultSettings(settingsFile);
		// reloads settings
		SettingsHandler.init();
	}
}
