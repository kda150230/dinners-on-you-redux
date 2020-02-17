package main;

public class Vector {
	// Class variables
	public double x, y;
	
	// Constructor
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// No-arg constructor
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	// Adds vector 2 to vector 1
	public void add(Vector v1, Vector v2) {
		v1.x += v2.x;
		v1.y += v2.y;
	}
	
	// Subs vector 2 from vector 1
	public void sub(Vector v1, Vector v2) {
		v1.x -= v2.x;
		v1.y -= v2.y;
	}
	
	// Multiplies vector by a scalar value
	public void multByScalar(Vector v, double scalar) {
		v.x = (v.x * scalar);
		v.y = (v.y * scalar);
	}
	
	// Returns distance between two vectors using distance formula
	public static double distance(Vector v1, Vector v2) {
		return Math.sqrt(Math.pow(v2.x-v1.x, 2) + Math.pow(v2.y-v1.y, 2));
	}
}
