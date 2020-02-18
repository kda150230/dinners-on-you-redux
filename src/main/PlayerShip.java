package main;

import java.awt.Color;
import java.awt.Graphics;

enum Team {
	RED, BLUE;
}

public class PlayerShip extends Ship {
	
	// Coordinates used to create vector shape for ship, centered at (0, 0)
	final int[] origXPts = {14,-10,-6,-10};
	final int[] origYPts = {0,-8,0,8};
	
	// Optional flag to make ship larger/smaller
	static final double sizeFactor = 1;
	
	// Sets max speed, and deceleration speed
	static final double MAX_SPEED = 4;
	static final double SPEED_DECAY = .99;
	
	private boolean accelerating = false;
	
	// ==============================
	
	// Constructor (starting position)
	public PlayerShip(Vector pos, Team team) {
		this.pos = pos;
		this.team = team;
		
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
			
			int[] tmpXPts = xPts.clone();
			int[] tmpYPts = yPts.clone();
			
			// These aren't working right now
			xPts[i]=(int)(pos.x + (tmpXPts[i]-pos.x)*Math.cos(angle)-(tmpYPts[i]-pos.y)*Math.sin(angle));
			yPts[i]=(int)(pos.y + (tmpXPts[i]-pos.x)*Math.sin(angle)+(tmpYPts[i]-pos.y)*Math.cos(angle));
		}
	}
	
	// Moves the position vector depending on speed and heading, then updates
	// the vector drawing points accordingly
	public void move() {
		
		// Slow down the ship if we aren't thrusting but have current speed
		if(!accelerating && speed > 0) {
			speed *= SPEED_DECAY;
			// Rounds speed down to zero if it gets close enough
			if(speed <= .1) {
				speed = 0;
			}
		}
		
		pos.x += (Math.cos(angle)*(speed));
		pos.y += (Math.sin(angle)*(speed));
		
		updatePoints();
	}
	
	public void setAccelerating(boolean accelerating) {
		this.accelerating = accelerating;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void addSpeed(double addedSpeed) {
		if(this.speed < MAX_SPEED)
			this.speed += addedSpeed;
	}
	
	public void addAngle(double addedAngle) {
		this.angle += addedAngle;
	}

	
	public void draw(Graphics g) {
		if(team == Team.BLUE)
			g.setColor(Color.blue);
		if(team == Team.RED)
			g.setColor(Color.red);
		g.fillPolygon(xPts, yPts, 4);
	}
	
}

/*
//origFlameXPts = {-6,-23,-6}, 
//origFlameYPts = {-3,0,3};	 
*/
