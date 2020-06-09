package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;
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

import gui.components.MediaFileLocationTextBox;
import gui.components.MediaItemDisplayLabel;
import gui.components.MediaItemSearchPanel;
import media_control.MediaSaver;
import misc.WrapLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;

public class OldGraphicsFrame extends JFrame {

	private static HashMap<String, String> defaultValues;
	
	private JPanel contentPane;
	private JPanel panelMediaDisplayGrid;
	
	private JTabbedPane tabbedPane;
	
	private MediaItemDisplayLabel imgViewMedia;
	private MediaItemDisplayLabel imgModifyTagsMedia;
	
	private JTextField textFieldRootStorageLoc;
	private JTextField textFieldSearch;
	private JTextField textFieldViewFileLocation;
	private JTextField textFieldModifyTagsFileLocation;
	
	private JScrollPane scrollPaneMediaDisplay;
	private JTextField textFieldModName;
	private JTextField textFieldModDateCreated;
	private JTextField textFieldModDateAdded;
	private JTextField textFieldModAuthorName;
	private JTextField textFieldModAuthorLinks;
	
	private JTextArea textFieldModTags;

	/**
	 * Launch the application.
	 */
	
	public static void runFrame() {
		defaultValues = GUIHandler.getDefaultValues();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldGraphicsFrame frame = new OldGraphicsFrame();
					frame.setVisible(true);
					frame.addFinalListeners();
					
					// displays all images on startup
					frame.addSearchGrid("untagged || !untagged");
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
		// updates values when changing page size
		tabbedPane.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        updateDefaultValues();
		    }
		});
		
	}
	
	private void updateDefaultValues() {
		GUIHandler.loadDefaultValues();
		textFieldRootStorageLoc.setText(defaultValues.get("settingsRootStorageLoc"));
		textFieldViewFileLocation.setText(defaultValues.get("selectedMediaItemFileLocation"));
		textFieldModifyTagsFileLocation.setText(defaultValues.get("selectedMediaItemFileLocation"));
		GUIHandler.updateMediaItemPanel(imgViewMedia, scrollPaneMediaDisplay.getWidth() - 16, scrollPaneMediaDisplay.getHeight() - 16);
		GUIHandler.updateMediaItemPanel(imgModifyTagsMedia, scrollPaneMediaDisplay.getWidth() - 430, scrollPaneMediaDisplay.getHeight() - 180);
		imgViewMedia.setMediaItem(Paths.get(defaultValues.get("selectedMediaItemFileLocation")));
		imgModifyTagsMedia.setMediaItem(Paths.get(defaultValues.get("selectedMediaItemFileLocation")));
		updateTags();
	}
	
	// updates text in the tag text boxes
	private void updateTags() {
		textFieldModName.setText(defaultValues.get("selectedMediaItemName"));
		textFieldModDateCreated.setText(defaultValues.get("selectedMediaItemDateCreated"));
		textFieldModDateAdded.setText(defaultValues.get("selectedMediaItemDateAdded"));
		textFieldModAuthorName.setText(defaultValues.get("selectedMediaItemAuthorName"));
		textFieldModAuthorLinks.setText(defaultValues.get("selectedMediaItemAuthorLinks"));
		textFieldModTags.setText(defaultValues.get("selectedMediaItemTags"));
	}

	/**
	 * Create the frame.
	 */
	public OldGraphicsFrame() {
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
					
					addSearchGrid(textFieldSearch.getText());
					
					
					
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
		
		scrollPaneMediaDisplay = new JScrollPane();
		scrollPaneMediaDisplay.setViewportBorder(null);
		scrollPaneMediaDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneMediaDisplay.getVerticalScrollBar().setUnitIncrement(16);
		panelSearch.add(scrollPaneMediaDisplay, BorderLayout.CENTER);
		
		panelMediaDisplayGrid = new JPanel();
		panelMediaDisplayGrid.setBorder(null);
		scrollPaneMediaDisplay.setViewportView(panelMediaDisplayGrid);
		panelMediaDisplayGrid.setLayout(new WrapLayout(3, 0, 0));
		
		JPanel panelView = new JPanel();
		tabbedPane.addTab("View", null, panelView, "View a media item");
		
		JLabel lblViewFileLocation = new JLabel("File Location: ");
		
		imgViewMedia = new MediaItemDisplayLabel(null, null);
		textFieldViewFileLocation = new MediaFileLocationTextBox(imgViewMedia, scrollPaneMediaDisplay);
		textFieldViewFileLocation.setColumns(10);
		
		JButton btnOpenSelectedFileLocation = new JButton("Open File Location");
		btnOpenSelectedFileLocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIHandler.btnOpenSelectedFileLocation();
			}
		});
		
		
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
							.addComponent(textFieldViewFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(btnOpenSelectedFileLocation, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panelView.setVerticalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelView.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelView.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblViewFileLocation)
						.addComponent(textFieldViewFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpenSelectedFileLocation))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imgViewMedia, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelView.setLayout(gl_panelView);
		
		JPanel panelModifyTags = new JPanel();
		tabbedPane.addTab("Modify Tags", null, panelModifyTags, "Change tags the of a media item");
		
		JLabel lblModifyTagsFileLocation = new JLabel("File Location: ");
		
		imgModifyTagsMedia = new MediaItemDisplayLabel(null, null);
		textFieldModifyTagsFileLocation = new MediaFileLocationTextBox(imgModifyTagsMedia, scrollPaneMediaDisplay);
		textFieldModifyTagsFileLocation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					updateDefaultValues();
				}
			}
		});
		
		JLabel lblModName = new JLabel("Name:");
		
		JLabel lblModDateCreated = new JLabel("Date Created: ");
		
		textFieldModName = new JTextField();
		textFieldModName.setColumns(10);
		
		textFieldModDateCreated = new JTextField();
		textFieldModDateCreated.setColumns(10);
		
		JLabel lblModDateAdded = new JLabel("Date Added: ");
		
		JLabel lblModTags = new JLabel("Tags: ");
		
		JSeparator separator = new JSeparator();
		
		textFieldModDateAdded = new JTextField();
		textFieldModDateAdded.setColumns(10);
		
		textFieldModAuthorName = new JTextField();
		textFieldModAuthorName.setColumns(10);
		
		textFieldModAuthorLinks = new JTextField();
		textFieldModAuthorLinks.setColumns(10);
		
		JLabel lblModAuthorName = new JLabel("Author Name: ");
		
		JLabel lblModAuthorLinks = new JLabel("Author Links: ");
		
		textFieldModTags = new JTextArea();
		
		JButton btnSaveTags = new JButton("Save Tags");
		btnSaveTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIHandler.btnSaveTags(textFieldModName.getText(), textFieldModDateCreated.getText(), textFieldModDateAdded.getText(), textFieldModAuthorName.getText(), textFieldModAuthorLinks.getText(), textFieldModTags.getText());
			}
		});
		
		GroupLayout gl_panelModifyTags = new GroupLayout(panelModifyTags);
		gl_panelModifyTags.setHorizontalGroup(
			gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelModifyTags.createSequentialGroup()
					.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelModifyTags.createSequentialGroup()
									.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblModAuthorName, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
										.addComponent(lblModDateAdded, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblModDateCreated, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblModName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblModAuthorLinks, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblModTags, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldModTags, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(textFieldModName, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(textFieldModDateCreated, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(textFieldModDateAdded, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(textFieldModAuthorName, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
										.addComponent(textFieldModAuthorLinks, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
									.addGap(18))
								.addGroup(gl_panelModifyTags.createSequentialGroup()
									.addComponent(lblModifyTagsFileLocation)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldModifyTagsFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
									.addGap(91))))
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addGap(70)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 378, Short.MAX_VALUE)))
					.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
						.addComponent(imgModifyTagsMedia, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addGap(38)
							.addComponent(btnSaveTags)))
					.addContainerGap())
		);
		gl_panelModifyTags.setVerticalGroup(
			gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelModifyTags.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModifyTagsFileLocation)
								.addComponent(textFieldModifyTagsFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSaveTags))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addComponent(imgModifyTagsMedia, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
							.addGap(184))
						.addGroup(gl_panelModifyTags.createSequentialGroup()
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModName)
								.addComponent(textFieldModName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModDateCreated)
								.addComponent(textFieldModDateCreated, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModDateAdded)
								.addComponent(textFieldModDateAdded, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldModAuthorName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblModAuthorName))
							.addGap(15)
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldModAuthorLinks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblModAuthorLinks))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelModifyTags.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModTags)
								.addComponent(textFieldModTags, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
							.addGap(80))))
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
	
	// adds search grid for a certain query
	private void addSearchGrid(String query) {
		for(MediaItemSearchPanel miPanel : GUIHandler.textFieldSearch(query)) {
			panelMediaDisplayGrid.add(miPanel);
			miPanel.getImageLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			miPanel.getImageLabel().setMediaItem(miPanel.getDisplayedMediaItemPath());
			miPanel.getImageLabel().addMouseListener(new MouseListener() {
				
			
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {
						GUIHandler.selectedMediaItem = miPanel.getDisplayedMediaItemPath();
						GUIHandler.updateSelectedMediaItemImage();
						tabbedPane.setSelectedIndex(1);
					}
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
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
