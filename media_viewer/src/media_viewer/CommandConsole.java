package media_viewer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import media.MediaData;
import media.MediaItem;
import media_control.MediaHandler;
import media_control.MediaSaver;

public class CommandConsole {
	
	// this lets the user control the program from the java console, for testing purposes
	// !! code in here is very bad, probably delete this later
	
	private static String mode = "default";
	private static String toPrint;
	
	public static void takeInput(String input) {
		if(input.equals("!back")) {
			// sets mode to default and clears storedData
			mode = "default";
		}
		
		switch(mode) {
			case "default":
				// changes mode or executes a command
				switch(input) {
					default: toPrint = "enter a command";
						break;
					case "search":
						mode = "search";
						toPrint = "input a tag to search for or enter '!back': ";
						break;
					case "tag item":
						mode = "tag item";
						toPrint = "beginning tagging process. enter '!back' to go back, or enter anything else to continue";
						break;
					case "list media items":
						System.out.println(MediaHandler.getAllMediaItems());
						toPrint = "enter a command";
						break;
					case "save media data":
						MediaSaver.saveMediaData();
						System.out.println("saved media data");
						toPrint = "enter a command";
						break;
				}
				break;
			case "search":
				toPrint = MediaHandler.getMediaItemsByTag(input).toString();
				break;
			case "tag item":
				toPrint = tagItem(input) + ", enter anything to continue";
				mode = "default";
				break;
		}
		
		
		System.out.println(toPrint);
		toPrint = "";
	}
	
	private static String tagItem(String input) {

		if(input.equals("!back")) {
			return "tagging process aborted";
		}
		
		String fileLocation;
		ArrayList<String> name, dateCreated, dateAdded, authorName, authorLinks, tags;
		name = new ArrayList<String>();
		dateCreated = new ArrayList<String>();
		dateAdded = new ArrayList<String>();
		authorName = new ArrayList<String>();
		authorLinks = new ArrayList<String>();
		tags = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		
		// tagging process
		System.out.print("enter a file name: ");
		fileLocation = sc.nextLine();
		
		// TODO: make sure the media file exists. can be done by seeing of allMedia contains a media w/ this path
		
		// prevents overwriting any existing media data
		if(MediaHandler.getMediaDataByPath(Paths.get(fileLocation)) != null) {
			return "cannot tag this file, it is already tagged";
		}
		
		System.out.print("enter the name(s) of this item, or enter '!next' to go to next category: ");
		while(true) {
			String toAdd = sc.nextLine();
			if(toAdd.equals("!next")) {
				break;
			}
			name.add(toAdd);
			System.out.println("added: " + toAdd);
		}
		
		System.out.print("enter date(s) this item was created, or enter '!next' to go to next category: ");
		while(true) {
			String toAdd = sc.nextLine();
			if(toAdd.equals("!next")) {
				break;
			}
			dateCreated.add(toAdd);
			System.out.println("added: " + toAdd);
		}
		
		System.out.print("enter date(s) you are adding this item, or enter '!next' to go to next category: ");
		while(true) {
			String toAdd = sc.nextLine();
			if(toAdd.equals("!next")) {
				break;
			}
			dateAdded.add(toAdd);
			System.out.println("added: " + toAdd);
		}
		
		System.out.print("enter author(s) of this item, or enter '!next' to go to next category: ");
		while(true) {
			String toAdd = sc.nextLine();
			if(toAdd.equals("!next")) {
				break;
			}
			authorName.add(toAdd);
			System.out.println("added: " + toAdd);
		}
		
		System.out.print("enter link(s) to the authors of this item, or enter '!next' to go to next category: ");
		while(true) {
			String toAdd = sc.nextLine();
			if(toAdd.equals("!next")) {
				break;
			}
			authorLinks.add(toAdd);
			System.out.println("added: " + toAdd);
		}
		
		System.out.print("enter other tags for this item, or enter '!next' to go to next category: ");
		while(true) {
			String toAdd = sc.nextLine();
			if(toAdd.equals("!next")) {
				break;
			}
			tags.add(toAdd);
			System.out.println("added: " + toAdd);
		}
		
		// creation of media item and data
		MediaItem mi = new MediaItem(Paths.get(fileLocation));
		MediaData md = new MediaData(name, dateCreated, dateAdded, authorName, authorLinks, tags);
		
		MediaHandler.addMedia(mi, md);
		
		return "item added";
	}
	
}