package gui.components.embedded_media_player;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class ControlBar extends BorderPane {

	/* This is the controls for the EmbeddedMediaPlayer */
	
	// Time slider
	private Slider timeSlider;
	
	// Play button
	private Button playButton;
	
	// Volume slider
	private Slider volumeSlider;
	
	// Resources
	private BufferedImage iconButtonPlay, iconButtonPause;
	private BufferedImage iconVolumeSlider;
	
	private MediaPlayer mp;
	
	public ControlBar(MediaPlayer mp) {
		super();
		this.mp = mp;
		
		loadResources();
		makeTimeSlider();
		makePlayButton();
		makeVolumeSlider();
		
		//this.getChildren().add(timeSlider);
		
		this.setStyle("-fx-background-color: #bfc2c7;");
		
	}
	
	private void loadResources() {
		// TODO
		try {
			iconButtonPlay = ImageIO.read(new File("res/image/media_player/icon_button_play.png"));
			iconButtonPause = ImageIO.read(new File("res/image/media_player/icon_button_pause.png"));
			iconVolumeSlider = ImageIO.read(new File("res/image/media_player/icon_volume_slider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeTimeSlider() {
		// TODO
	}
	
	private void makePlayButton() {
		// TODO
		playButton = new Button("play");
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.setLeft(playButton);
	}
	
	private void makeVolumeSlider() {
		HBox volumeControls = new HBox();
		
		// Volume Icon
		ImageView imageView = new ImageView(SwingFXUtils.toFXImage(iconVolumeSlider, null ));
		imageView.setFitWidth(24);
		imageView.setFitHeight(24);
		
		// Slider
		volumeSlider = new Slider(0, 1, mp.getVolume());
		volumeSlider.valueProperty().addListener( (v, oldValue, newValue) -> mp.setVolume(newValue.doubleValue()));
		
		volumeControls.getChildren().addAll(imageView, volumeSlider);
		this.setRight(volumeControls);
	}
	
}
