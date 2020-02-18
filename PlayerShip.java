package main;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerShip extends Ship {
	
	// Coordinates used to create vector shape for ship, centered at (0, 0)
	final int[] origXPts = {14,-10,-6,-10};
	final int[] origYPts = {0,-8,0,8};
	
	// Optional flag to make ship larger/smaller
	static final double sizeFactor = 1;
	
	// ==============================
	
	// Constructor (starting position)
	public PlayerShip(Vector pos, double angle) {
		this.pos = pos;
		this.angle = angle;
		
		// Clone avoids bad copy side effects
		xPts = origXPts.clone(); 
		yPts = origYPts.clone();
		
		updatePoints();
	}
	
	// ==============================

	// Updates vector points based on current position
	// Needs to be called every time the ships's position vector changes
	void updatePoints() {
		for(int i=0; i<xPts.length; i++) {
			xPts[i] = origXPts[i] + (int)pos.x;
			yPts[i] = origYPts[i] + (int)pos.y;
			
			// These aren't working right now
			//xPts[i]=(int)(xPts[i]*Math.cos(angle)-yPts[i]*Math.sin(angle));
			//yPts[i]=(int)(xPts[i]*Math.sin(angle)+yPts[i]*Math.cos(angle));
		}
	}
	
	// Moves the position vector depending on speed and heading, then updates
	// the vector drawing points accordingly
	public void move() {	
		pos.x += (Math.cos(angle)*(speed));
		pos.y += (Math.sin(angle)*(speed));
		
		updatePoints();
	}
	
	public void moveTest() {
		pos.x++;
		//angle+=.01;
		updatePoints();
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void addSpeed(double addedSpeed) {
		this.speed += addedSpeed;
	}
	
	public void addAngle(double addedAngle) {
		this.angle += addedAngle;
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillPolygon(xPts, yPts, 4);
	}
	
}

/*
//origFlameXPts = {-6,-23,-6}, 
//origFlameYPts = {-3,0,3};	 
*/
