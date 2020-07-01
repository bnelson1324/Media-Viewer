package gui.components.media_display;

import java.nio.file.Path;

public class VideoDisplayPanelPreview extends VideoDisplayPanel {

	protected VideoDisplayPanelPreview(Path mediaItem) {
		super(mediaItem);
		this.setDisplaySize(256, 256, false);
	}

	@Override
	protected void prepareSnapshot() {
		super.prepareSnapshot();
	}
	
}
