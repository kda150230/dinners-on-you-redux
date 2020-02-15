package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GameManager extends JComponent implements Runnable {
	
	private static final long serialVersionUID = 1L;

	public static boolean gameActive = false;
	long startTime, endTime, framePeriod;

	//Graphics g;
	JComponent game;
	
	PlayerShip myShip;
	
	
	// Called when "Play" button on main menu is pressed
	public void start() {
		/* Other pre-game initialization tasks should go here */
		gameActive = true;
		
		// Creates new JPanel for us to draw on and adds it to the GamePanel frame
		game = new GameManager();
		GamePanel.add(game);
		
		//Starts timer
		startTime = 0;
		endTime = 0;
		
		// Frame period represents time in ms between frame updates
		// Effective target FPS is 1000 / framePeriod
		framePeriod = 16; //16 ms ~ 60 fps
		
		// Creates and starts the main game thread
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	// Initializes all starting entities such as player ships and bases
	public void initEntities() {
		myShip = new PlayerShip(new Vector(300, 300), 1);
	}
	
	// Paint component method of the main game canvas JPanel
	public void paintComponent(Graphics g) {
		System.out.println("Paint called");
		
		super.paintComponent(g);
		
		g.setColor(Color.green);
		g.drawRect(50, 50, 200, 450);
		
		myShip.draw(g); /* This is throwing a null pointer exception for some reason... */
	}
	
	public void run() {
		
		initEntities();
		
		// Main game loop
		while(gameActive) {
			
			// Marks time at start of this game loop
			startTime = System.currentTimeMillis();
			
			/*
			 * 
			 * Update methods go here
			 * 
			 */
			
			repaint();
			
			//System.out.println("Update at " + System.currentTimeMillis());
			
			// Sleep the thread between frames if updates are done
			try{
				// Marks time at end of this game loop
				endTime = System.currentTimeMillis();

				// Avoids sleeping for negative time
				if(framePeriod - (endTime - startTime) > 0) {
					Thread.sleep(framePeriod - (endTime - startTime));
				}
			}catch(InterruptedException e){
			}
			
		} // ends while()	
	} // ends run()
		
}
