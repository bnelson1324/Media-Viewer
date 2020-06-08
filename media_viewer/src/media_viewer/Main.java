package media_viewer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import gui.GUIHandler;
import media_control.MediaHandler;
import media_control.MediaLoader;
import settings.*;

public class Main {
	
	public static void main(String[] args) {
	
		init();
		
	}
	
	private static void init() {
		SettingsHandler.init();
		MediaHandler.setUpStorageFolder();
		MediaHandler.setUpMediaStorageFile();
		MediaHandler.init();
		GUIHandler.init();
	}
	
	
	
}
