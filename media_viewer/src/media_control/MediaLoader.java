package media_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import media.MediaData;
import media.MediaItem;
import settings.SettingsHandler;

public class MediaLoader {

	/* This is what loads media items and data */
	
	private static Reader myReader;
	static Gson gson;
	
	static JsonArray mediaDataStorageJson;

	
	// all media items in the storage folder
	private static ArrayList<MediaItem> loaderAllMediaItems;
	
	// pairs file path with a media object
	private static HashMap<Path, MediaData> loaderAllMediaData;
	
	private static void loadMediaItems() {
		// finds every file from the root storage folder
		
		Path rootStorageFolder = Paths.get(SettingsHandler.getSetting("rootStorageFolderLoc"));
		ArrayList<Path> allFiles = new ArrayList<Path>();
		fetchFiles(rootStorageFolder.toFile(), allFiles);
		
		for(Path f : allFiles) {
			// removes root folder storage location from the path
			String relativePath = f.toString().substring(rootStorageFolder.toString().length() + 1);
			
			MediaItem mi = new MediaItem(Paths.get(relativePath));
			loaderAllMediaItems.add(mi);
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadMediaData() {
		gson = new GsonBuilder()
			.setPrettyPrinting()
			.create();
		
		// gets mediaStorage json file
		try {
			myReader = new FileReader(new File("mediaDataStorage.json"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		mediaDataStorageJson = gson.fromJson(myReader, JsonArray.class);
		
		// adds all data from mediaDataStorage to loaderAllMediaData
		for(JsonElement mediaDataJsonElement : mediaDataStorageJson) {
			JsonObject mdJson = (JsonObject) mediaDataJsonElement;
	
			String mdPath = mdJson.get("fileLocation").getAsString();  
			ArrayList<String> mdName = gson.fromJson(mdJson.get("name"), ArrayList.class);
			ArrayList<String> mdDateCreated = gson.fromJson(mdJson.get("dateCreated"), ArrayList.class);
			ArrayList<String> mdDateAdded = gson.fromJson(mdJson.get("dateAdded"), ArrayList.class);
			ArrayList<String> mdAuthorName = gson.fromJson(mdJson.get("authorName"), ArrayList.class);
			ArrayList<String> mdAuthorLink = gson.fromJson(mdJson.get("authorLinks"), ArrayList.class);
			ArrayList<String> mdTags = gson.fromJson(mdJson.get("tags"), ArrayList.class);
			
			MediaData md = new MediaData(mdName, mdDateCreated, mdDateAdded, mdAuthorName, mdAuthorLink, mdTags);
			loaderAllMediaData.put(Paths.get(mdPath), md);
		}
		try {
			myReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// recursively adds all non-directory files from a directory, including from its subdirectories, into an ArrayList
	private static void fetchFiles(File dir, ArrayList<Path> allNonDirFiles) {
		File[] allFiles = dir.listFiles();
	
		for(File f : allFiles) {
			if(f.isDirectory()) {
				fetchFiles(f, allNonDirFiles);
			} else {
				allNonDirFiles.add(f.toPath());
			}
		}
	}

	// adds 'untagged' tag to mediaItems without tag
	private static void addUntaggedTag() {
		ArrayList<MediaItem> untaggedMediaItems = new ArrayList<MediaItem>();
		
		// finds all untagged media items
		for(MediaItem mi : loaderAllMediaItems) {
			if(loaderAllMediaData.get(mi.getPath()) == null) {
				untaggedMediaItems.add(mi);
			}
		}
		
		// adds 'untagged' tag to them
		for(MediaItem mi : untaggedMediaItems) {
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("untagged");
			MediaData md = new MediaData(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), tags);
			loaderAllMediaData.put(mi.getPath(), md);
		}
	}
	
	static ArrayList<MediaItem> getMediaItems() {
		return loaderAllMediaItems;
	}
	
	static HashMap<Path, MediaData> getMediaData() {
		return loaderAllMediaData;
	}
	
	public static void init() {
		loaderAllMediaItems = new ArrayList<MediaItem>();
		loaderAllMediaData = new HashMap<Path, MediaData>();
		loadMediaItems();
		loadMediaData();
		addUntaggedTag();
	}

	
	
	
	
}
