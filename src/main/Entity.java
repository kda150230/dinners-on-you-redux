package main;

import java.awt.Graphics;
import java.awt.Shape;

/* Entity represents a parent class for objects in the game such as
 * ships, bullets, bases, etc that all require a location, velocity,
 * and an "alive" flag.
 */
public abstract class Entity {
	
	//Class variables
	private Vector loc;
	private Vector speed;
	private float acceleration; 
	private boolean isAlive = true;
	public Shape hitbox;
	
	public abstract void draw(Graphics g);

	public void kill() {
		isAlive = false;
	}
	
	public void revive() {
		isAlive = true;
	}
	
}
