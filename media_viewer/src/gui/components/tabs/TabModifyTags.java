package gui.components.tabs;

import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import gui.components.LabelMediaItemDisplay;
import gui.components.TextBoxFileLocation;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class TabModifyTags extends Tab {

	private TextBoxFileLocation tbFileLocation;
	private JPanel pnlSelectedMediaDisplay;
	private LabelMediaItemDisplay lblSelectedMediaDisplay;
	
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_4;
	private JLabel lblNewLabel_5;
	private JTextArea textField_5;
	private JButton btnSave;
	private JLabel lblNewLabel_6;
	
	public TabModifyTags(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		JLabel lblFileLocation = new JLabel("File Location: ");
		
		tbFileLocation = new TextBoxFileLocation();
		
		pnlSelectedMediaDisplay = new JPanel();
		pnlSelectedMediaDisplay.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Name: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Date Created: ");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Date Added: ");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Author Name: ");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Author Links: ");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Tags: ");
		
		textField_5 = new JTextArea();
		textField_5.setRows(8);
		textField_5.setColumns(10);
		textField_5.setLineWrap(true);
		textField_5.setWrapStyleWord(true);
		
		btnSave = new JButton("Save Tags");
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(btnSave)
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblFileLocation))
								.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(btnSave)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel))
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_1))
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_2))
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_3))
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_4))
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_5))
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(93))
		);
		
		lblSelectedMediaDisplay = new LabelMediaItemDisplay();
		pnlSelectedMediaDisplay.add(lblSelectedMediaDisplay, BorderLayout.NORTH);
		setLayout(groupLayout);

	}

	private void updateImage() {
		// TODO: update size changes
		int imgWidth, imgHeight;
		imgWidth = this.getWidth() - 390;
		imgHeight = this.getHeight() - 80;
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
