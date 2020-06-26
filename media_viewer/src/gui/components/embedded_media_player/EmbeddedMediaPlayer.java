package gui.components.embedded_media_player;

import java.io.File;
import java.nio.file.Path;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import media_control.MediaHandler;

public class EmbeddedMediaPlayer extends Group {

	/* This is a video and audio player */
	
	private Media media;
	public MediaPlayer mp;
	public MediaView mv;
	
	public EmbeddedMediaPlayer(Path mi) {
		super();
		
		// Creating media player
		BorderPane bp = new BorderPane();
		this.getChildren().add(bp);
		
		File f = MediaHandler.getFullRelativePath(mi).toFile();
		// test code below for Test.java, replace above
		//File f = new File(mi.toString());
		
		media = new Media(f.toURI().toString());
		mp = new MediaPlayer(media);
		mv = new MediaView(mp);
		
		mp.setVolume(0.5);
		// !!TEMP
		mp.setAutoPlay(true);
		
		
		bp.setCenter(mv);
		bp.setBottom(new ControlBar(mp));
		
		
		mv.setPreserveRatio(true);
	}
	
	public void setSize(int width, int height) {
		// !! not sure if below works
		//mv.setFitWidth(width);
		//mv.setFitHeight(height);
		System.out.println("width: " + width + "height: " + height);
	}
	
}

