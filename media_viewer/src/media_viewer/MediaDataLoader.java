package media_viewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import media.MediaData;

public class MediaDataLoader {

	private static Reader myReader;
	private static Gson gson = new Gson();
	
	private static JsonArray mediaDataStorage;
	
	// pairs file paths with a media object
	private static HashMap<File, MediaData> allMediaData;
	
	/* This is what loads media data */
	
	public static void init() {
		// gets mediaStorage json file
		String rootStorageFolderLoc = SettingsLoader.getSetting("rootStorageFolderLoc");
		
		try {
			myReader = new FileReader(new File(rootStorageFolderLoc + "/..mediaDataStorage.json"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		mediaDataStorage = gson.fromJson(myReader, JsonArray.class);
		
		// adds all data from mediaDataStorage to allMediaData
		allMediaData = new HashMap<File, MediaData>();
		
		for(JsonElement mediaDataJsonElement : mediaDataStorage) {
			JsonObject mdJson = (JsonObject) mediaDataJsonElement;
			
			String mdPath = mdJson.get("fileLocation").getAsString();  
			String mdName = mdJson.get("name").getAsString();
			String mdDateCreated = mdJson.get("dateCreated").getAsString();
			String mdDateAdded = mdJson.get("dateAdded").getAsString();
			ArrayList<String> mdAuthorName = gson.fromJson(mdJson.get("authorName"), ArrayList.class);
			ArrayList<String> mdAuthorLink = gson.fromJson(mdJson.get("authorLinks"), ArrayList.class);
			ArrayList<String> mdTags = gson.fromJson(mdJson.get("tags"), ArrayList.class);
			
			MediaData md = new MediaData(mdName, mdDateCreated, mdDateAdded, mdAuthorName, mdAuthorLink, mdTags);
			allMediaData.put(new File(mdPath), md);
		}
		
		
	}
	
	public static MediaData getMediaData(File path) {
		return allMediaData.get(path);
	}
	
	
	
}
