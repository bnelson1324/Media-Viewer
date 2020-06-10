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
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class TabView extends Tab {

	private TextBoxFileLocation tbFileLocation;
	private JPanel pnlSelectedMediaDisplay;
	private LabelMediaItemDisplay lblSelectedMediaDisplay;
	
	public TabView(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		JLabel lblFileLocation = new JLabel("File Location: ");
		
		tbFileLocation = new TextBoxFileLocation();
		
		pnlSelectedMediaDisplay = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFileLocation, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
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
					.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
					.addGap(9))
		);
		pnlSelectedMediaDisplay.setLayout(new BorderLayout(0, 0));
		
		lblSelectedMediaDisplay = new LabelMediaItemDisplay();
		pnlSelectedMediaDisplay.add(lblSelectedMediaDisplay, BorderLayout.NORTH);
		setLayout(groupLayout);

	}
	
	
	// updates the selected media item's image
	private void updateImage() {
		int imgWidth, imgHeight;
		imgWidth = this.getWidth() - 32;
		imgHeight = this.getHeight() - 64;
		BufferedImage bImg = (BufferedImage) defaultValues.get("smiImage");
		lblSelectedMediaDisplay.setMediaItem(Paths.get(defaultValues.get("smi").toString()), bImg);
		lblSelectedMediaDisplay.setImageSize(imgWidth, imgHeight, true);
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
