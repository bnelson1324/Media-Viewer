package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import misc.WrapLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class GraphicsFrame extends JFrame {

	private static HashMap<String, String> defaultValues;
	
	private JPanel contentPane;
	private JPanel panelMediaDisplayGrid;
	
	private JTabbedPane tabbedPane;
	
	private JLabel imgViewMedia;
	private JLabel imgModifyTagsMedia;
	
	private JTextField textFieldRootStorageLoc;
	private JTextField textFieldSearch;
	private JTextField textFieldViewFileLocation;
	private JTextField textFieldModifyTagsFileLocation;

	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		loadFrame();
	}*/
	
	public static void runFrame() {
		defaultValues = GUIHandler.getDefaultValues();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsFrame frame = new GraphicsFrame();
					frame.setVisible(true);
					frame.addFinalListeners();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	// adds some listeners at the end to make sure all components are already created
	private void addFinalListeners() {
		// updates values when tab is changed
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDefaultValues();
			}
		});
		
	}
	
	private void updateDefaultValues() {
		GUIHandler.loadDefaultValues();
		textFieldRootStorageLoc.setText(defaultValues.get("settingsRootStorageLoc"));
		textFieldViewFileLocation.setText(defaultValues.get("selectedMediaItemFileLocation"));
		GUIHandler.updateMediaItemPanel(imgViewMedia);
		GUIHandler.updateMediaItemPanel(imgModifyTagsMedia);
	}

	/**
	 * Create the frame.
	 */
	public GraphicsFrame() {
		setTitle("Media Viewer");
		setMinimumSize(new Dimension(640, 480));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setToolTipText("");
		tabbedPane.addTab("Search", null, panelSearch, "Search for media items by tag");
		panelSearch.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSearchBar = new JPanel();
		panelSearch.add(panelSearchBar, BorderLayout.NORTH);
		
		JLabel lblSearch = new JLabel("Search by tag: ");
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// searches query and displays results
					panelMediaDisplayGrid.removeAll();
					
					for(MediaItemPanel miPanel : GUIHandler.textFieldSearch(textFieldSearch.getText())) {
						panelMediaDisplayGrid.add(miPanel);
						miPanel.getImageLabel().addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent e) {
								GUIHandler.selectedMediaItemPath = miPanel.getDisplayedMediaItemPath();
								tabbedPane.setSelectedIndex(1);
							}

							@Override
							public void mousePressed(MouseEvent e) {
							}

							@Override
							public void mouseReleased(MouseEvent e) {
							}

							@Override
							public void mouseEntered(MouseEvent e) {
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}
							
						});
					}
					
					panelMediaDisplayGrid.revalidate();
					panelMediaDisplayGrid.repaint();
				}
			}
		});
		textFieldSearch.setColumns(10);
		GroupLayout gl_panelSearchBar = new GroupLayout(panelSearchBar);
		gl_panelSearchBar.setHorizontalGroup(
			gl_panelSearchBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSearchBar.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSearch, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelSearchBar.setVerticalGroup(
			gl_panelSearchBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSearchBar.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelSearchBar.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearch, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panelSearchBar.setLayout(gl_panelSearchBar);
		
		JScrollPane scrollPaneMediaDisplay = new JScrollPane();
		scrollPaneMediaDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelSearch.add(scrollPaneMediaDisplay, BorderLayout.CENTER);
		
		panelMediaDisplayGrid = new JPanel();
		scrollPaneMediaDisplay.setViewportView(panelMediaDisplayGrid);
		panelMediaDisplayGrid.setLayout(new WrapLayout(3, 0, 0));
		
		JPanel panelView = new JPanel();
		tabbedPane.addTab("View", null, panelView, "View a media item");
		
		JLabel lblViewFileLocation = new JLabel("File Location: ");
		
		imgViewMedia = new JLabel("");
		textFieldViewFileLocation = new MediaFileLocationTextBox(imgViewMedia);
		textFieldViewFileLocation.setColumns(10);
		
		
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelView.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelView.createParallelGroup(Alignment.LEADING)
						.addComponent(imgViewMedia, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
						.addGroup(gl_panelView.createSequentialGroup()
							.addComponent(lblViewFileLocation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldViewFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panelView.setVerticalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelView.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelView.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblViewFileLocation)
						.addComponent(textFieldViewFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imgViewMedia, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelView.setLayout(gl_panelView);
		
		JPanel panelModifyTags = new JPanel();
		tabbedPane.addTab("Modify Tags", null, panelModifyTags, "Change tags the of a media item");
		
		JLabel lblModifyTagsFileLocation = new JLabel("File Location: ");
		
		imgModifyTagsMedia = new JLabel("");
		textFieldModifyTagsFileLocation = new MediaFileLocationTextBox(imgModifyTagsMedia);
		
		GroupLayout gl_panelModifyTags = new GroupLayout(panelModifyTags);
		gl_panelModifyTags.setHorizontalGroup(
			gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelModifyTags.createSequentialGroup()
					.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblModifyTagsFileLocation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldModifyTagsFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addContainerGap(241, Short.MAX_VALUE)
							.addComponent(imgModifyTagsMedia, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelModifyTags.setVerticalGroup(
			gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelModifyTags.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModifyTagsFileLocation)
						.addComponent(textFieldModifyTagsFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(imgModifyTagsMedia, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelModifyTags.setLayout(gl_panelModifyTags);
		
		JPanel panelSettings = new JPanel();
		tabbedPane.addTab("Settings", null, panelSettings, "Change settings");
		
		JLabel lblRootStorageLoc = new JLabel("Root storage folder location: ");
		
		textFieldRootStorageLoc = new JTextField();
		textFieldRootStorageLoc.setColumns(10);
		
		JButton btnSaveSettings = new JButton("Save settings");
		btnSaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIHandler.btnSaveSettings(textFieldRootStorageLoc.getText());
			}
		});
		
		JButton btnResetSettings = new JButton("Reset Settings");
		btnResetSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIHandler.btnResetSettings();
				updateDefaultValues();
			}
		});
		GroupLayout gl_panelSettings = new GroupLayout(panelSettings);
		gl_panelSettings.setHorizontalGroup(
			gl_panelSettings.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSettings.createSequentialGroup()
					.addGroup(gl_panelSettings.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelSettings.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRootStorageLoc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldRootStorageLoc, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSettings.createSequentialGroup()
							.addGap(26)
							.addComponent(btnSaveSettings, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnResetSettings, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(146, Short.MAX_VALUE))
		);
		gl_panelSettings.setVerticalGroup(
			gl_panelSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSettings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSettings.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveSettings)
						.addComponent(btnResetSettings))
					.addGap(30)
					.addGroup(gl_panelSettings.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRootStorageLoc, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldRootStorageLoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(316, Short.MAX_VALUE))
		);
		panelSettings.setLayout(gl_panelSettings);
	}
}
