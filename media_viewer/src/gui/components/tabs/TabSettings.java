package gui.components.tabs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import gui.GUIManager;
import media_control.MediaHandler;
import settings.SettingsHandler;
import settings.SettingsSaver;

public class TabSettings extends Tab {
	// TODO
	
	
	private JTextField tfRootStorageFolderLoc;

	private JButton btnSaveSettings;
	private JButton btnResetSettings;
	
	public TabSettings(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		btnSaveSettings = new JButton("Save Settings");
		btnSaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(tfRootStorageFolderLoc.getText());
			}
		});
		
		btnResetSettings = new JButton("Reset Settings");
		btnResetSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetSettings();
			}
		});
		
		JLabel lblRootStorageFolderLoc = new JLabel("Root Storage Folder Location: ");
		
		tfRootStorageFolderLoc = new JTextField();
		tfRootStorageFolderLoc.setColumns(10);
		
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
							.addComponent(btnResetSettings)))
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
					.addContainerGap(321, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	private void saveSettings(String rootStorageFolderLoc) {
		SettingsHandler.modifySetting("rootStorageFolderLoc", rootStorageFolderLoc);
		SettingsSaver.saveSettings();
		System.out.println("Saved settings");
		MediaHandler.refreshMediaFolder();
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
	
	@Override
	public void updateTab() {
		tfRootStorageFolderLoc.setText(SettingsHandler.getSetting("rootStorageFolderLoc"));
	}
	
	@Override
	public void onSelect() {
		updateTab();
	}
	
	@Override
	public void onResize() {	
	}
}
