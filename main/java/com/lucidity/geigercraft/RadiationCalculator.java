package com.lucidity.geigercraft;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

public class RadiationCalculator {
	public static final String formatSv(double value, boolean doseRate){
		DecimalFormat fm = new DecimalFormat("0.00");
		String postfix = "";
		if(doseRate){
			postfix = "/h";
		}
		if(value < 0.000001){
			return fm.format(value*1000000000) + " nSv" + postfix;
		}else if(value < 0.001){
			return EnumChatFormatting.GREEN + fm.format(value*1000000) + " uSv" + postfix;
		}else if(value < 1){
			return EnumChatFormatting.GOLD + fm.format(value*1000) + " mSv" + postfix;
		}else if(value < 1000){
			return EnumChatFormatting.RED + fm.format(value) + " Sv" + postfix;
		}else if(value < 1000000){
			return EnumChatFormatting.DARK_PURPLE + fm.format(value/1000) + " kSv" + postfix;
		}else if(value < 1000000000){
			return EnumChatFormatting.BLUE + fm.format(value/1000000) + " MSv" + postfix;
		}else{
			return EnumChatFormatting.OBFUSCATED + "-------------------";
		}
	}
	
	public static final Point3D[] traverseLine(int x1, int y1, int z1, int x2, int y2, int z2){
		int x = x1;
		int y = y1;
		int z = z1;
		int dx = x2-x1;
		int dy = y2-y1;
		int dz = z2-z1;
	    int n, sx, sy, sz, exy, exz, ezy, ax, ay, az, bx, by, bz;
		
		List<Point3D> pointList = new ArrayList<Point3D>();
		
	    sx = Integer.signum(dx);  sy = Integer.signum(dy);  sz = Integer.signum(dz);
	    ax = Math.abs(dx);  ay = Math.abs(dy);  az = Math.abs(dz);
	    bx = 2*ax;	   by = 2*ay;	  bz = 2*az;
	    exy = ay-ax;   exz = az-ax;	  ezy = ay-az;
	    n = ax+ay+az;
	    for(int j = n-1; j >= 0; j--){
	    	pointList.add(new Point3D(x, y, z));
			if( exy < 0 ){
				if( exz < 0 ){
					x += sx;
					exy += by; exz += bz;
				}else{
					z += sz;
					exz -= bx; ezy += by;
				}
			}else{
				if( ezy < 0 ){
					z += sz;
					exz -= bx; ezy += by;
				}else{
					y += sy;
					exy -= bx; ezy -= bz;
				}
			}
	    }
		return pointList.toArray(new Point3D[pointList.size()]);
	}
}
