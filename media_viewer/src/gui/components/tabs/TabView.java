package gui.components.tabs;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import gui.components.LabelMediaItemDisplay;
import gui.components.TextBoxFileLocation;

public class TabView extends Tab {

	// TODO
	
	private TextBoxFileLocation tbFileLocation;
	
	private LabelMediaItemDisplay pnlSelectedMediaDisplay;
	
	public TabView(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		JLabel lblFileLocation = new JLabel("File Location: ");
		
		tbFileLocation = new TextBoxFileLocation();
		
		pnlSelectedMediaDisplay = new LabelMediaItemDisplay();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFileLocation, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFileLocation))
						.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	
	// updates the selected media item's image
	public void updateImage() {
		int imgWidth, imgHeight;
		imgWidth = this.getWidth() - 32;
		imgHeight = this.getHeight() - 64;
		BufferedImage bImg = (BufferedImage) defaultValues.get("smiImage");
		pnlSelectedMediaDisplay.setMediaItem(Paths.get(defaultValues.get("smi").toString()), bImg);
		pnlSelectedMediaDisplay.setImageSize(imgWidth, imgHeight, true);
	}
	
	@Override
	public void updateTab() {
		// updates the text box and image
		tbFileLocation.setText(defaultValues.get("smi").toString());
		updateImage();
	}


	@Override
	public void onResize() {
		// updates image
		updateImage();
	}
}
