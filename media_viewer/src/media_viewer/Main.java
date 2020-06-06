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
	
	/* TODO: when the program starts, detect if any media items are untagged
	 */
	
	public static void main(String[] args) {
	
		init();
		
		// !! test code below
		

		/*
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		CommandConsole.takeInput("");
		while(true) {
			String input = sc.nextLine();
			CommandConsole.takeInput(input);
		}
		*/
	}
	
	private static void init() {
		SettingsHandler.init();
		MediaHandler.setUpStorageFolder();
		MediaHandler.setUpMediaStorageFile();
		MediaHandler.init();
		GUIHandler.init();
	}
	
	
	
}
