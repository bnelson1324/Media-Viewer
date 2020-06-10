package gui.components.tabs;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class TabSettings extends Tab {

	// TODO
	
	public TabSettings(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 640, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 480, Short.MAX_VALUE)
		);
		setLayout(groupLayout);

	}

	@Override
	public void onSelect() {
		// TODO Auto-generated method stub
		System.out.println("d");
	}
}
