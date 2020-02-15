package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class GamePanel extends JPanel{
	
	// Class variables
	private static final long serialVersionUID = 1L;
	final static int xRes = 1600, yRes = 900;
	

	public void paint(Graphics g) {
		// Draws the menu background color
		g.setColor(new Color(0, 1, 41));
		g.fillRect(0, 0, xRes, yRes);
	}
	
	public static void init() {
		JFrame frame = new JFrame("Dinner's On You!");
		JPanel gamePanel = new GamePanel();
		gamePanel.setVisible(true);
		gamePanel.setLayout(null);
		
		// Sets the JFrame's size and makes it visible
		frame.setSize(xRes, yRes);
		
		// Initializes the title screen buttons on the gamePanel
		initTitleScreen(frame);
		
		// Adds a new gamePanel object (JPanel) to the game window to
		// represent the main canvas
		frame.add(gamePanel);
		
		// Sets the default close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sets the JFrame to visible
		frame.setVisible(true);
	}
	
	static void initTitleScreen(JFrame frame) {
		// Defines the fonts used for menu buttons
		Font menuFont = new Font("Courier", Font.PLAIN, 75);
		
		// Defines the colors used for menu buttons
		Color gold = new Color(209, 206, 38);
		
		// Creates a new JLabel for the 'Play' button
		JLabel playButton = new JLabel("Play Asteroids!!");
		
		// Sets the color, enables visibility, and sets font
		playButton.setForeground(gold);
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playButton.setVisible(true);
		playButton.setFont(menuFont);
		playButton.setBounds(100, 100, 800, 200);
		
		// Adds a mouse listener to enable click events for the play button
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameManager gameManager = new GameManager();
				gameManager.start();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setForeground(gold.darker());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setForeground(gold);
			}
		});
		
		// Add the playButton to our JFrame
		frame.add(playButton);
	}
	
}
