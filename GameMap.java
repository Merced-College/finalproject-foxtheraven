/*
Xandra Quevedo
This class was made Nov 13, 2025
Finished Dec 4, 2024
Special map class for Dungeons of Java

This is basically an array replacement class, for use in
my map hash table. Since arrays aren't directly comparable,
but are the simplest way to store an x and y value in one object,
I needed to make this class to store arrays that ARE comparable.
A unique equals method will override the standard and directly
compare X and Y.
*/

public class GameMap {
	//These two values specifically store valid X and Y coordinates
	//AKA real map locations, based on my drawn map
	private int validX;
	private int validY;
	
	//Fully parameterized constructor
	public GameMap (int validX, int validY) {
		this.validX = validX;
		this.validY = validY;
	}
	
	//Non-parameterized constructor
	//Shouldn't ever use this but it's here for safety
	public GameMap() {
		this.validX = 0;
		this.validY = 0;
	}
	
	//Setters
	public void setValidX(int validX) {
		this.validX = validX;
	}
	public void setValidY(int validY) {
		this.validY = validY;
	}
	
	//Getters
	public int getValidX() {
		return this.validX;
	}
	public int getValidY() {
		return this.validY;
	}
	
	//For collision functionality - compares x and y directly
	//and returns true if BOTH are equal
	@Override
	public boolean equals(Object map) {
		GameMap other = (GameMap) map;
		if (this.validX == other.validX && this.validY == other.validY) {
			return true;
		}
		return false;
	}
	
	//For debugging purposes, so the map can be printed at any time
	@Override 
	public String toString() {
		return this.validX + ", " + this.validY;
	}
	
}
