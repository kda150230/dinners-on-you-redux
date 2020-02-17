package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;

	public static boolean gameActive = false;
	long startTime, endTime, framePeriod;

	//Graphics g;
	
	final static int xRes = 1600, yRes = 900;
	
	JFrame frame;
	JPanel game;
	
	static PlayerShip myShip;
	
	Thread gameThread;
	
	// Called when "Play" button on main menu is pressed
	public void start() {
		
		/* Other pre-game initialization tasks should go here */
		
		myShip = new PlayerShip(new Vector(5, 5), 0);
		
		gameActive = true;
		
		// Inits JFrame/JPanel for drawing
		frame = new JFrame("Dinner's on You!");
		game = new GameManager();
		game.setVisible(true);
		game.setLayout(null);
		
		frame.setSize(xRes, yRes);
		frame.add(game);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		//Starts timer
		startTime = 0;
		endTime = 0;
		
		// Frame period represents time in ms between frame updates
		// Effective target FPS is 1000 / framePeriod
		framePeriod = 16; //16 ms ~ 60 fps
		
		//repaint();
		
		// Creates and starts the main game thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/* Initializes all starting entities such as player ships and bases
	public void initEntities() {
		myShip = new PlayerShip(new Vector(500, 500), 1);
	}*/
	
	// Paint component method of the main game canvas JPanel
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.setColor(Color.red);
		g.drawRect(0, 0, 300, 300);
		
		g.setColor(Color.green);
		g.drawRect(0, 0, 500, 500);
		
		if(myShip != null) {
			myShip.draw(g);
		}
	}
	
	public void run() {
		
		//initEntities();
		
		// Main game loop
		while(gameActive) {
			
			// Marks time at start of this game loop
			startTime = System.currentTimeMillis();
			
			/*
			 * 
			 * Update methods go here
			 * 
			 */
			
			//frame.repaint();
			game.repaint();
			repaint();
			
			//myShip.setAngle(.0001);
			myShip.moveTest();
			
			System.out.println("x: "+myShip.loc.x);
			
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
