package gui.components.embedded_media_player;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ControlBar extends BorderPane {

	/* This is the controls for the EmbeddedMediaPlayer */
	
	// Time slider
	private Slider timeSlider;
	
	// Play button
	private Button playButton;
	
	// Volume slider
	private Slider volumeSlider;
	
	// Resources
	private ImageView ivButtonPlay, ivButtonPause, ivVolumeSlider;
	
	private MediaPlayer mp;
	
	public ControlBar(MediaPlayer mp) {
		super();
		this.mp = mp;
		
		mp.setOnReady( () -> {
			loadResources();
			makeTimeSlider();
			makePlayButton();
			makeVolumeSlider();
		});
		
		this.setStyle("-fx-background-color: #bfc2c7;");
		
	}
	
	private void loadResources() {
		BufferedImage iconButtonPlay, iconButtonPause, iconVolumeSlider;
		try {
			iconButtonPlay = ImageIO.read(new File("res/image/media_player/icon_button_play.png"));
			iconButtonPause = ImageIO.read(new File("res/image/media_player/icon_button_pause.png"));
			iconVolumeSlider = ImageIO.read(new File("res/image/media_player/icon_volume_slider.png"));
			
			ivButtonPlay = new ImageView(SwingFXUtils.toFXImage(iconButtonPlay, null));
			ivButtonPlay.setFitWidth(24);
			ivButtonPlay.setFitHeight(24);
			
			ivButtonPause = new ImageView(SwingFXUtils.toFXImage(iconButtonPause, null));
			ivButtonPause.setFitWidth(24);
			ivButtonPause.setFitHeight(24);
			
			ivVolumeSlider = new ImageView(SwingFXUtils.toFXImage(iconVolumeSlider, null ));
			ivVolumeSlider.setFitWidth(24);
			ivVolumeSlider.setFitHeight(24);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeTimeSlider() {
		// TODO
		// !! dont forget to update timeText in the timeSlider action listener
		HBox timeControls = new HBox();
		
		// Text saying "Time:"
		Text timeText = new Text("Time: ");

		// Slider
		Duration mediaDuration = mp.getMedia().getDuration();
		timeSlider = new Slider(0, mediaDuration.toSeconds(), 0);
		//timeSlider.valueProperty().addListener( (v, oldValue, newValue) -> TODO));
		
		// Text displaying position in the media
		Text positionText = new Text();
		
		timeControls.getChildren().addAll(timeText, timeSlider, positionText);
		this.setCenter(timeControls);
	}
	
	private void makePlayButton() {
		playButton = new Button();
		playButton.setGraphic(ivButtonPlay);
		playButton.setOnAction( (e) -> {
			if(mp.getStatus() == Status.PAUSED || mp.getStatus() == Status.STOPPED || mp.getStatus() == Status.READY) {
				// plays media
				mp.play();
				playButton.setGraphic(ivButtonPause);
			} else if(mp.getStatus() == Status.PLAYING) {
				// pauses media
				mp.pause();
				playButton.setGraphic(ivButtonPlay);
			}
		});
		
		this.setLeft(playButton);
	}
	
	private void makeVolumeSlider() {
		HBox volumeControls = new HBox();
		
		// Slider
		volumeSlider = new Slider(0, 1, mp.getVolume());
		volumeSlider.valueProperty().addListener( (v, oldValue, newValue) -> mp.setVolume(newValue.doubleValue()));
		
		volumeControls.getChildren().addAll(ivVolumeSlider, volumeSlider);
		this.setRight(volumeControls);
	}
	
}
