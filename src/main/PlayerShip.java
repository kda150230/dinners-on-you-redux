package main;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerShip extends Ship {
	
	// Coordinates used to create vector shape for ship, centered at (0, 0)
	int[] xPts = {14,-10,-6,-10},
		  yPts = {0,-8,0,8};
				 //origFlameXPts = {-6,-23,-6}, 
				 //origFlameYPts = {-3,0,3};
	
	int[] origXPts = xPts;
	int[] origYPts = yPts;
	
	// Optional flag to make ship larger/smaller
	static final double sizeFactor = 1;
	
	// ==============================
	
	// Constructor (starting location)
	public PlayerShip(Vector loc, double angle) {
		this.loc = loc;
		this.angle = angle;
		updatePoints();
	}
	
	// ==============================

	// Updates vector points based on current position
	public void updatePoints() {
		for(int i=0; i<xPts.length; i++) {
			xPts[i] = origXPts[i] + loc.x;
			yPts[i] = origYPts[i] + loc.y;
			
			//xPts[i]=(int)(xPts[i]*Math.cos(angle)-yPts[i]*Math.sin(angle));
			//yPts[i]=(int)(xPts[i]*Math.sin(angle)+yPts[i]*Math.cos(angle));
		}
	}
	
	
	public void moveTest() {
		loc.x++;
	}

	
	public void draw(Graphics g) {
		
		updatePoints();
		
		g.setColor(Color.red);
		g.fillPolygon(xPts, yPts, 4);
	}
	
}
