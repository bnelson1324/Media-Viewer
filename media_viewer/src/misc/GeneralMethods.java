package misc;

import java.util.ArrayList;

public class GeneralMethods {

	// removes duplicates from an arraylist
	public ArrayList removeArrayListDuplicates(ArrayList a) {
		ArrayList b = new ArrayList();
		
		for(Object o : a) {
			if(!b.contains(o)) {
				b.add(o);
			}
		}
		
		return b;
	}
	
	
}
