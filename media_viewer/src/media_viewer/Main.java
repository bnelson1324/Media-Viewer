package media_viewer;

public class Main {

	
	public static void main(String[] args) {
		
		init();
		
		
		
		// launch program
		System.out.println(SettingsLoader.getSetting("rootStorageFolderLoc"));
	}
	
	private static void init() {
		// loads settings
		SettingsLoader.loadSettings();
	
		
	}
	
}
