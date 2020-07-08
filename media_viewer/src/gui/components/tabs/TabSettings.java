package gui.components.tabs;
import java.io.File;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import gui.components.ConfirmationWindow;
import media.MediaData;
import media_control.MediaHandler;
import settings.SettingsHandler;
import settings.SettingsSaver;
import javax.swing.JComboBox;

public class TabSettings extends Tab {

	private JTextField tfRootStorageFolderLoc;
	private JTextField tfDateFormat;

	private JButton btnSaveSettings;
	private JButton btnResetSettings;
	
	
	public TabSettings(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		btnSaveSettings = new JButton("Save Settings");
		btnSaveSettings.addActionListener( (e) -> {
			ConfirmationWindow cw = new ConfirmationWindow("Are you sure you would like to save these settings?");
			cw.setOnChoice( () -> {
				if(cw.choice) {
					saveSettings(tfRootStorageFolderLoc.getText(), tfDateFormat.getText());
				}
			});
		});
		
		btnResetSettings = new JButton("Reset Settings");
		btnResetSettings.addActionListener( (e) -> {
			ConfirmationWindow cw = new ConfirmationWindow("Are you sure you would like to reset the settings?");
			cw.setOnChoice( () -> {
				if(cw.choice) {
					resetSettings();
				}
			});
		});
		
		
		JLabel lblRootStorageFolderLoc = new JLabel("Root Storage Folder Location: ");
		
		tfRootStorageFolderLoc = new JTextField();
		tfRootStorageFolderLoc.setColumns(10);
		
		JLabel lblDateFormat = new JLabel("Date Format: ");
		
		String[] cbDateFormatChoices = {"Month first", "Day first"};
		
		tfDateFormat = new JTextField();
		tfDateFormat.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRootStorageFolderLoc, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfRootStorageFolderLoc, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSaveSettings)
							.addGap(178)
							.addComponent(btnResetSettings))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDateFormat, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfDateFormat, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveSettings)
						.addComponent(btnResetSettings))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRootStorageFolderLoc)
						.addComponent(tfRootStorageFolderLoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDateFormat)
						.addComponent(tfDateFormat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(287, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	// resets settings to default values
	private void resetSettings() {
		File settingsFile = new File("settings/settings.cfg");
		SettingsSaver.copyDefaultSettings(settingsFile);
		// reloads settings
		SettingsHandler.init();
		MediaHandler.refreshMediaFolder();
		updateTab();
	}
	
	
	// saves settings based on settings selectors' values
	private void saveSettings(String rootStorageFolderLoc, String dateFormat) {
		SettingsHandler.modifySetting("rootStorageFolderLoc", rootStorageFolderLoc);
		SettingsHandler.modifySetting("dateFormat", dateFormat);
		SettingsSaver.saveSettings();
		System.out.println("Saved settings");
		MediaHandler.refreshMediaFolder();
		MediaData.createDateFormat();
	}
	
	// updates settings selectors' values based on current settings
	@Override
	public void updateTab() {
		tfRootStorageFolderLoc.setText(SettingsHandler.getSetting("rootStorageFolderLoc"));
		tfDateFormat.setText(SettingsHandler.getSetting("dateFormat"));
	}
	
	@Override
	public void onSelect() {
		updateTab();
	}
	
	@Override
	public void onResize() {	
	}
}
