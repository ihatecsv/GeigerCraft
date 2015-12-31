package com.halonium.geigercraft;

public class RadObj {
	public String id;
	public double radValue;
	public int radStrength;
	public double absorbValue;
	public RadObj(String id, double radValue, int radStrength, double absorbValue){
		this.id = id;
		this.radValue = radValue;
		this.radStrength = radStrength;
		this.absorbValue = absorbValue;
	}
}
