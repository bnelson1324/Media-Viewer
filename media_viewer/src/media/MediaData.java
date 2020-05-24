package media;

import java.util.ArrayList;

public class MediaData {

	/* This is data describing a media file */
	
	private String name, dateCreated, dateAdded;
	private ArrayList<String> authorName, authorLinks, tags;
	
	public MediaData(String name, String dateCreated, String dateAdded, ArrayList<String> authorName, ArrayList<String> authorLinks, ArrayList<String> tags) {
		this.name = name;
		this.dateCreated = dateCreated;
		this.dateAdded = dateAdded;
		this.authorName = authorName;
		this.authorLinks = authorLinks;
		this.tags = tags;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public ArrayList<String> getAuthorName() {
		return authorName;
	}
	
	public ArrayList<String> getAuthorLinks() {
		return authorLinks;
	}
	
	// gets the descriptive tags (non metadata)
	public ArrayList<String> getDescTags() {
		return tags;
	}
	
	// gets all the tags, including metadata
	public ArrayList<String> getAllTags() {
		
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "Name: '" + name;
		str += "', dateCreated: " + dateCreated;
		str += ", dateAdded: " + dateAdded;
		str += ", authorName: " + authorName;
		str += ", authorLinks: " + authorLinks;
		str += ", tags: " + tags;
		return str;
	}
}
