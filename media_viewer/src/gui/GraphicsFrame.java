package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsFrame extends JFrame {

	private static HashMap<String, String> defaultValues;
	
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	
	private JPanel panelMediaDisplayGrid;
	
	private JTextField textFieldRootStorageLoc;
	private JTextField textFieldSearch;

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
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDefaultValues();
			}
		});
	}
	
	private void updateDefaultValues() {
		GUIHandler.loadDefaultValues();
		textFieldRootStorageLoc.setText(defaultValues.get("settingsRootStorageLoc"));
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
		
		JLabel lblSearch = new JLabel("Search by tag: ");
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// searches query and displays results
					panelMediaDisplayGrid.removeAll();
					
					for(JLabel label : GUIHandler.textFieldSearch(textFieldSearch.getText())) {
						panelMediaDisplayGrid.add(label);
					}
					
					panelMediaDisplayGrid.revalidate();
					panelMediaDisplayGrid.repaint();
				}
			}
		});
		textFieldSearch.setColumns(10);
		
		JScrollPane scrollPaneMediaDisplay = new JScrollPane();
		scrollPaneMediaDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMediaDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panelSearch = new GroupLayout(panelSearch);
		gl_panelSearch.setHorizontalGroup(
			gl_panelSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSearch.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSearch.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneMediaDisplay, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
						.addGroup(gl_panelSearch.createSequentialGroup()
							.addComponent(lblSearch)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelSearch.setVerticalGroup(
			gl_panelSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSearch.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSearch.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearch)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneMediaDisplay, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panelMediaDisplayGrid = new JPanel();
		panelMediaDisplayGrid.setBackground(Color.PINK);
		scrollPaneMediaDisplay.setViewportView(panelMediaDisplayGrid);
		panelMediaDisplayGrid.setLayout(new GridLayout(1, 0, 0, 0));
		panelSearch.setLayout(gl_panelSearch);
		
		JPanel panelView = new JPanel();
		tabbedPane.addTab("View", null, panelView, "View a media item");
		
		JPanel panelModifyTags = new JPanel();
		tabbedPane.addTab("Modify Tags", null, panelModifyTags, "Change tags the of a media item");
		
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
