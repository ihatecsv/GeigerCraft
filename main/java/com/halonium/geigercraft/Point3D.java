package com.halonium.geigercraft;

public class Point3D{
	public int x;
	public int y;
	public int z;
	
	public Point3D(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean equals(Point3D point){
		if(this.x == point.x && this.y == point.y && this.z == point.z){
			return true;
		}
		return false;
	}
}