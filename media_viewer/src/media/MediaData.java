package media;

import java.util.ArrayList;


public class MediaData {

	// TODO: make the program automatically add a fileFormat tag like fileFormat:mp3 or fileFormat:wav
	
	/* This is data describing a media file */
	
	private ArrayList<String> name, dateCreated, dateAdded, authorName, authorLinks, tags;
	
	public MediaData(ArrayList<String> name, ArrayList<String> dateCreated, ArrayList<String> dateAdded, ArrayList<String> authorName, ArrayList<String> authorLinks, ArrayList<String> tags) {
		this.name = name;
		this.dateCreated = dateCreated;
		this.dateAdded = dateAdded;
		this.authorName = authorName;
		this.authorLinks = authorLinks;
		this.tags = tags;
	}
	
	public ArrayList<String> getName() {
		return name;
	}
	
	public ArrayList<String> getDateCreated() {
		return dateCreated;
	}
	
	public ArrayList<String> getDateAdded() {
		return dateAdded;
	}
	
	public ArrayList<String> getAuthorName() {
		return authorName;
	}
	
	public ArrayList<String> getAuthorLinks() {
		return authorLinks;
	}
	
	// gets the generic tags (tags ArrayList)
	public ArrayList<String> getGenericTags() {
		return tags;
	}
	
	// gets all the tags, including the ones that aren't misc 
	public ArrayList<String> getAllTags() {
		ArrayList<String> allTags = new ArrayList<String>();
		for(String s : name) {
			allTags.add("name:" + s);
		}
		for(String s : dateCreated) {
			allTags.add("dateCreated:" + s);
		}
		for(String s : dateAdded) {
			allTags.add("dateAdded:" + s);
		}
		for(String s : authorName) {
			allTags.add("authorName:" + s);
		}
		for(String s : authorLinks) {
			allTags.add("authorLinks:" + s);
		}
		for(String s : tags) {
			allTags.add(s);
		}
		return allTags;
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
