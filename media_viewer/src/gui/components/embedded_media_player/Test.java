package gui.components.embedded_media_player;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

//!! TEST CLASS BELOW
public class Test {
	
	JFXPanel jfxPanel;
	
	private Test() {
		JFrame frame = new JFrame("Oracle Media Player");
		
        JPanel pnl = new JPanel();
        jfxPanel = new JFXPanel();
        pnl.add(jfxPanel);
		
        EmbeddedMediaPlayer emp = new EmbeddedMediaPlayer(Paths.get("default_storage\\video\\caramelldansen.mp4"));
		Scene s = new Scene(emp, 640, 480);
        
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				
				jfxPanel.setScene(s);
				
			}
		});
		jfxPanel.setBackground(Color.DARK_GRAY);
		frame.add(pnl);
		frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //
        
        frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				int size = (int) (Math.random()*2);
				//emp.mv.setScaleX(size);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        	
       
        
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		
		System.out.println("test object created");
	}
	
}