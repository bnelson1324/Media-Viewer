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
	// TODO: remove mediaitem class, replace w/ just paths
	// TODO: add button to view menu to open file location of selected media item
	// TODO: make it the gui doesnt reload every image whenever changing tabs
	// TODO: organize gui tabs into separate classes
	
	
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
