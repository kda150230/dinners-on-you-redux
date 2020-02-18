package main;

import java.awt.Shape;

/* Entity represents a parent class for objects in the game such as
 * ships, bullets, bases, etc that all require a position, velocity,
 * and an "alive" flag.
 */
public abstract class Entity {
	
	//Class variables
	protected Vector pos;
	protected double speed;
	protected double acceleration;
	protected double angle;
	protected boolean isAlive = true;
	protected Team team;
	public Shape hitbox;

	public void kill() {
		isAlive = false;
	}
	
	public void revive() {
		isAlive = true;
	}
	
}
