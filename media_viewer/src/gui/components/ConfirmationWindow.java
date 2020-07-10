package gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ConfirmationWindow extends JFrame {

	/* Popup window offering 2 choices: yes or no */
	
	// whether the user chose "yes" or "no", false until a choice is selected
	public boolean choice;
	
	// runnable run when the user chooses "yes" or "no"
	private Runnable onChoice;
	
	// TODO: create an enum allowing confirmation window to initialize with either 1 or 2 choices, allowing for an alert box
	
	public ConfirmationWindow(String title, String description, String yesText, String noText) {
		super(title);
		this.choice = false;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		JLabel lblDesc = new JLabel(description);
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblDesc, BorderLayout.CENTER);
		lblDesc.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		JPanel pnlButtons = new JPanel();
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlYes = new JPanel();
		pnlButtons.add(pnlYes, BorderLayout.WEST);
		pnlYes.setBorder(new EmptyBorder(10, 50, 10, 10));
		
		JPanel pnlNo = new JPanel();
		pnlButtons.add(pnlNo, BorderLayout.EAST);
		pnlNo.setBorder(new EmptyBorder(10, 10, 10, 50));
		
		JButton btnYes = new JButton(yesText);
		pnlYes.add(btnYes, BorderLayout.WEST);
		
		JButton btnNo = new JButton(noText);
		pnlNo.add(btnNo, BorderLayout.EAST);
		

		btnYes.addActionListener((e) -> {
			choice = true;
			onChoice.run();
			ConfirmationWindow.this.setVisible(false);
			ConfirmationWindow.this.dispose();
		});
		
		btnNo.addActionListener((e) -> {
			choice = false;
			onChoice.run();
			ConfirmationWindow.this.setVisible(false);
			ConfirmationWindow.this.dispose();
		});
		
		
		this.pack();
		
		// centers window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) ((screenSize.width-this.getSize().width)*0.5), (int) ((screenSize.height-this.getSize().height)*0.25));
		
		this.setVisible(true);
	}
	
	public ConfirmationWindow(String description) {
		this("Alert", description, "Yes", "No");
	}
	
	public void setOnChoice(Runnable r) {
		this.onChoice = r;
	}
	
}
