package main;

public class GameManager implements Runnable{
	
	public static boolean gameActive = false;
	
	long startTime, endTime, framePeriod;
	
	// Called when "Play" button on main menu is pressed
	public void start() {
		/* Other pre-game initialization tasks should go here */
		gameActive = true;
		
		//Starts timer
		startTime = 0;
		endTime = 0;
		framePeriod = 100; //25 ms ~ 40 fps
		
		// Creates and starts the main game thread
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
		long currentTime, endTime, startTime;
		
		// Main game loop
		while(gameActive) {
			
			// Marks time at start of this game loop
			startTime = System.currentTimeMillis();
			
			/*
			 * 
			 * Update methods go here
			 * 
			 */
			
			System.out.println("Update at " + System.currentTimeMillis());
			
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
