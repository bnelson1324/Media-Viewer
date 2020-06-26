package gui.components.media_display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.nio.file.Path;

import javax.swing.SwingUtilities;

import gui.components.embedded_media_player.EmbeddedMediaPlayer;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

public class VideoDisplayPanel extends MediaDisplayPanel {

	private JFXPanel jfxPanel;
	
	private EmbeddedMediaPlayer emp;
	
	DoubleProperty mvw, mvh;
	
	protected VideoDisplayPanel(Path mi) {
		super(mi, "video");
		jfxPanel = new JFXPanel();
		emp = new EmbeddedMediaPlayer(mi);
		Scene scene = new Scene(emp);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				jfxPanel.setScene(scene);
			}
		});
		
		// !! test file: video/caramelldansen.mp4
		this.add(jfxPanel, BorderLayout.CENTER);
		
		mvw = emp.mv.fitWidthProperty();
		mvh = emp.mv.fitHeightProperty();
	}

	
	
	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		VideoDisplayPanel thisPanel = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				thisPanel.setSize(width-25, height-25);
				jfxPanel.setSize(width-25, height-25);
			}
		});
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mvw.set(width-50);
				mvh.set(height-50);
			}
		});
		
	}

	@Override
	protected void addContextMenu() {
		// TODO Auto-generated method stub
		
	}

	
	
}
