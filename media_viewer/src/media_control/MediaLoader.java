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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import media.MediaData;
import media.MediaItem;
import media_viewer.SettingsLoader;

public class MediaLoader {

	/* This is what loads media items and data */
	
	private static Reader myReader;
	static Gson gson;
	
	static JsonArray mediaDataStorageJson;
	
	
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

	public static void init() {
		loadMediaItems();
		loadMediaData();
		
	}

	static ArrayList<MediaItem> loadMediaItems() {
		// finds every file from the root storage folder
		
		ArrayList<MediaItem> allMediaItems = new ArrayList<MediaItem>();
		
		Path rootStorageFolder = Paths.get(SettingsLoader.getSetting("rootStorageFolderLoc"));
		ArrayList<Path> allFiles = new ArrayList<Path>();
		fetchFiles(rootStorageFolder.toFile(), allFiles);
		
		for(Path f : allFiles) {
			// removes root folder storage location from the path
			String relativePath = f.toString().substring(rootStorageFolder.toString().length() + 1);
			
			MediaItem mi = new MediaItem(Paths.get(relativePath));
			allMediaItems.add(mi);
		}
		
		return allMediaItems;
	}

	@SuppressWarnings("unchecked")
	static HashMap<Path, MediaData> loadMediaData() {
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
		
		// adds all data from mediaDataStorage to allMediaData
		HashMap<Path, MediaData> allMediaData = new HashMap<Path, MediaData>();
		
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
			allMediaData.put(Paths.get(mdPath), md);
		}
		
		return allMediaData;
	}

	
	

	
	
	
	
}
