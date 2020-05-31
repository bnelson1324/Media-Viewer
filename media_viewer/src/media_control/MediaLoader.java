package media_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import media.MediaData;
import media.MediaItem;
import media_viewer.SettingsLoader;

public class MediaLoader {

	/* This is what loads media items and data */
	
	private static Reader myReader;
	private static Gson gson = new Gson();
	
	private static JsonArray mediaDataStorage;
	
	// all media items in the storage folder
	private static ArrayList<MediaItem> allMediaItems;
	
	// pairs file path with a media object
	private static HashMap<Path, MediaData> allMediaData;
	
	
	
	// TODO: add method to save allMediaData to the JSON file
	
	static MediaData getMediaDataByPath(Path path) {
		return allMediaData.get(path);
	}
	
	static void addMediaData(Path p, MediaData md) {
		allMediaData.put(p, md);
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
	
	static ArrayList<MediaItem> getAllMediaItems() {
		return allMediaItems;
	}

	public static void init() {
		loadMediaItems();
		loadMediaData();
		
	}

	private static void loadMediaItems() {
		// finds every file from the root storage folder
		
		allMediaItems = new ArrayList<MediaItem>();
		
		Path rootStorageFolder = Paths.get(SettingsLoader.getSetting("rootStorageFolderLoc"));
		ArrayList<Path> allFiles = new ArrayList<Path>();
		fetchFiles(rootStorageFolder.toFile(), allFiles);
		
		for(Path f : allFiles) {
			// removes root folder storage location from the path
			String relativePath = f.toString().substring(rootStorageFolder.toString().length() + 1);
			
			MediaItem mi = new MediaItem(Paths.get(relativePath));
			allMediaItems.add(mi);
		}
	}

	private static void loadMediaData() {
		// gets mediaStorage json file
		try {
			myReader = new FileReader(new File("mediaDataStorage.json"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		mediaDataStorage = gson.fromJson(myReader, JsonArray.class);
		
		// adds all data from mediaDataStorage to allMediaData
		allMediaData = new HashMap<Path, MediaData>();
		
		for(JsonElement mediaDataJsonElement : mediaDataStorage) {
			JsonObject mdJson = (JsonObject) mediaDataJsonElement;
	
			String mdPath = mdJson.get("fileLocation").getAsString();  
			ArrayList<String> mdName = gson.fromJson(mdJson.get("name"), ArrayList.class);
			ArrayList<String> mdDateCreated = gson.fromJson(mdJson.get("dateCreated"), ArrayList.class);
			ArrayList<String> mdDateAdded = gson.fromJson(mdJson.get("dateAdded"), ArrayList.class);
			ArrayList<String> mdAuthorName = gson.fromJson(mdJson.get("authorName"), ArrayList.class);
			ArrayList<String> mdAuthorLink = gson.fromJson(mdJson.get("authorLinks"), ArrayList.class);
			ArrayList<String> mdTags = gson.fromJson(mdJson.get("tags"), ArrayList.class);
			
			MediaData md = new MediaData(mdName, mdDateCreated, mdDateAdded, mdAuthorName, mdAuthorLink, mdTags);
			allMediaData.put(Paths.get(mdPath), md);
		}
	}
	

	
	
	
	
}
