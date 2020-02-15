package main;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerShip extends Ship {
	
	// Vector coordinates for drawing player ship
	static float[] origXPts = {14,-10,-6,-10},
				   origYPts = {0,-8,0,8}/*,
				   origFlameXPts = {-6,-23,-6}, 
				   origFlameYPts = {-3,0,3}*/;
	int[] xPts = new int[origXPts.length];
	int[] yPts = new int[origYPts.length];
	static final double sizeFactor = 1; // optional flag to make ship larger/smaller
	
	// Constructor (starting location)
	public PlayerShip(Vector loc, float angle) {
		this.loc = loc;
		this.angle = angle;
		
		// Adds location offset to vector draw points, and optionally scales
		for(int i=0; i<origXPts.length; i++) {
			origXPts[i] *= sizeFactor;
			origXPts[i] += loc.x;
		}
		for(int i=0; i<origYPts.length; i++) {
			origYPts[i] *= sizeFactor;
			origYPts[i] += loc.y;
		}
	}

	// Rotates points according to the ship's current angle
	public void rotatePoints(float[] xPts, float[] yPts) {
		for(int i=0; i<xPts.length; i++)
		{
			this.xPts[i]=(int)(xPts[i]*Math.cos(angle)-
						  yPts[i]*Math.sin(angle));
			this.yPts[i]=(int)(xPts[i]*Math.sin(angle)+
						  yPts[i]*Math.cos(angle));
		}
	}
	
	public void draw(Graphics g) {
		
		System.out.println("Drawing!!!");
		
		rotatePoints(origXPts, origYPts);
		
		g.setColor(Color.red);
		g.fillPolygon(xPts, yPts, 4);
	}
	
}
