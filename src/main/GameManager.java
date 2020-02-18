package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager extends JPanel implements Runnable {
	
	// Default versionUID for JStuff
	private static final long serialVersionUID = 1L;
	
	// Thread instance/time-keeping variables
	Thread gameThread;
	long startTime, endTime, framePeriod;
	
	// Determines game resolution
	final static int xRes = 1600, yRes = 900;
	
	// Swing components for GUI elements
	JFrame frame;
	JPanel game;
	
	// Game objects must be static, as we're using an instance of this class for a JPanel
	static PlayerShip myShip, enemyShip;

	
	// Called when "Play" button on main menu is pressed
	public void start() {
		
		/* Other pre-game initialization tasks should go here */
		
		initEntities();
		
		/*
		 * TODO: Clean up this section (is all this needed??)
		 */
		
		// Inits JFrame/JPanel for drawing
		frame = new JFrame("Dinner's on You!");
		game = new GameManager();
		
		game.setVisible(true);
		game.setLayout(null);
		game.setSize(xRes, yRes);
		
		// Allows key listener to work when window is "focused"
		game.setFocusable(true);
		game.requestFocusInWindow();
		
		initKeyAdapter(game);
		
		frame.setSize(xRes, yRes);
		frame.add(game);
		//frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		frame.setLayout(null);
		
		// Frame period represents time in ms between frame updates
		// Effective target FPS is 1000 / framePeriod
		framePeriod = 16; //16 ms ~ 60 fps
		
		// Creates and starts the main game thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	// Initializes all starting entities such as player ships and bases
	public void initEntities() {
		myShip = new PlayerShip(new Vector(300, 300), Team.BLUE);
		enemyShip = new PlayerShip(new Vector(600, 600), Team.RED);
	}
	
	// Initializes key listener for game inputs
	public void initKeyAdapter(JPanel game) {
		game.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("Up!");
					myShip.setAccelerating(true);
					myShip.addSpeed(.2);
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("Left!");
					myShip.addAngle(-.1);
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					//System.out.println("Down!");
					//myShip.addSpeed(-.2);
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("Right!");
					myShip.addAngle(.1);
				}
			}
			
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_W) {
					myShip.setAccelerating(false);
				}
			}
		});
	}
	
	// Paint component method of the main game canvas JPanel
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.setColor(Color.red);
		g.drawRect(0, 0, 300, 300);
		
		g.setColor(Color.green);
		g.drawRect(0, 0, 500, 500);
		
		if(myShip != null) {
			myShip.draw(g);
			enemyShip.draw(g);
		}
	}
	
	public void run() {
		
		// Main game loop
		while(true) {
			
			// Marks time at start of this game loop
			startTime = System.currentTimeMillis();
			
			/*
			 * 
			 * 
			 * Update methods go here
			 * 
			 * 
			 */
			
			myShip.move();
			
			// Requests that AWT repaint our game JPanel we paint on in paint()
			game.repaint();
			
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
