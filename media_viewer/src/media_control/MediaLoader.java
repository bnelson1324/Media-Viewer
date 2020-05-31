package media_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import media.MediaData;
import media.MediaItem;
import media_viewer.SettingsLoader;

public class MediaLoader {

	/* This is what loads media items and data */
	
	private static Reader myReader;
	private static Gson gson;
	
	private static JsonArray mediaDataStorageJson;
	
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
		
		// adds all data from mediaDataStorage to allMediaData
		allMediaData = new HashMap<Path, MediaData>();
		
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
	}

	// saves media data to mediaDataStorage.json
	public static void saveMediaData() {
		// create array of jsonObjects
		ArrayList<JsonElement> allJsonMediaData = new ArrayList<JsonElement>();
		
		// create a json object representing each media data, add these to the array
		for(Map.Entry<Path, MediaData> entry : allMediaData.entrySet()) {
			JsonObject mdJson = new JsonObject();
					
			// !! not sure if this works
			// adds file location and media data to mdJson
			mdJson.addProperty("fileLocation", entry.getKey().toString());
			mdJson.add("name", gson.toJsonTree(entry.getValue().getName()));
			mdJson.add("dateCreated", gson.toJsonTree(entry.getValue().getDateCreated()));
			mdJson.add("dateAdded", gson.toJsonTree(entry.getValue().getDateAdded()));
			mdJson.add("authorName", gson.toJsonTree(entry.getValue().getAuthorName()));
			mdJson.add("authorLinks", gson.toJsonTree(entry.getValue().getAuthorLinks()));
			mdJson.add("tags", gson.toJsonTree(entry.getValue().getGenericTags()));
			
			allJsonMediaData.add(mdJson);
		}
		
		// overwrite mediaDataStorageJson with the array
		mediaDataStorageJson = (JsonArray) gson.toJsonTree(allJsonMediaData);

		// overwrite the mediaDataStorage file with mediaDataStorageJson
		try {
			FileWriter fw = new FileWriter("mediaDataStorage.json");
			fw.write(gson.toJson(mediaDataStorageJson));
			fw.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	
	
	
}
