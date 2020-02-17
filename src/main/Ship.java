package main;

public abstract class Ship extends Entity {
	
	// Represents the current location of points used to draw a certain ship
	// The reference points will be provided in each Ship child class under
	// the origXPts and origYPts arrays
	int[] xPts, yPts, origXPts, origYPts;
	
	abstract void updatePoints();
	
}
