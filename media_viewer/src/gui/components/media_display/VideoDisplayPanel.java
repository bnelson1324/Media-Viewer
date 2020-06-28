package gui.components.media_display;

import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import media_control.MediaHandler;

public class VideoDisplayPanel extends ImageDisplayPanel {

	protected MediaPlayer mp;
	protected MediaView mv;
	
	protected VideoDisplayPanel(Path mediaItem) {
		super(mediaItem, false);
		
		// creates jfxpanel to initialize jfx toolkit
		new JFXPanel();
		
		Platform.runLater( () -> {
			prepareMedia();
			mp.setOnReady( () -> {
				prepareSnapshot();
			});
			
		});
		System.out.println(readyToRender);
		
		
		
		
		
		
	}
	
	private void prepareMedia() {
		Media fxMedia = new Media(MediaHandler.getFullRelativePath(mediaItem).toFile().toURI().toString());
		System.out.println(MediaHandler.getFullRelativePath(mediaItem).toString());
		mp = new MediaPlayer(fxMedia);
		mv = new MediaView(mp);
	}
	
	// prepares thumbnail of video
	private void prepareSnapshot() {
		// sets media player to the middle of the media
		Duration middleTime = mp.getMedia().getDuration().divide(2);
		mp.seek(middleTime);
		
		System.out.println(mp.getMedia().getWidth());
		WritableImage wi = new WritableImage(mp.getMedia().getWidth(), mp.getMedia().getHeight());
		mv.snapshot(new SnapshotParameters(), wi);
		
		
		System.out.println(mediaItemImage);
		mediaItemImage = SwingFXUtils.fromFXImage(wi, null);
		System.out.println(mediaItemImage);
		imageLabel.setIcon(new ImageIcon(mediaItemImage));
		
		SwingUtilities.invokeLater( () -> {
			// temp
			JFrame fr = new JFrame("temp");
			fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fr.setSize(640, 480);
			fr.setVisible(true);
			JLabel lbl = new JLabel(new ImageIcon(SwingFXUtils.fromFXImage(wi, null)));
			fr.add(lbl);
		});
		
		
		readyToRender = true;
		System.out.println(readyToRender);
		revalidate();
		repaint();
	}
	
	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		SwingUtilities.invokeLater( () -> {
			super.setDisplaySize(width, height, keepAspectRatio);
		});
		
	}
	
	@Override
	public void addContextMenu() {
		//TODO
	}

}
