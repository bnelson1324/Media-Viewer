package gui.components.tabs;

import javax.swing.JPanel;

import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public abstract class Tab extends JPanel {

	/* Abstract class for tags in this app */
	
	// default values for certain fields in the gui
	protected HashMap<String, Object> defaultValues;
	
	public Tab(HashMap<String, Object> defaultValues) {
		this.defaultValues = defaultValues;
	}
	
	// called by a specific tab when it is selected
	public abstract void onSelect();

}
