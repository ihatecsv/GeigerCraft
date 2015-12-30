package com.lucidity.geigercraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPropertiesRadTarget implements IExtendedEntityProperties {
	public final static String NAME = "gcRadTarget";
	private EntityPlayer player;
	private double dose;
	private double lastRad = 0;
	
	public ExtendedPropertiesRadTarget(EntityPlayer player){
		 this.player=player;
		 this.dose=0;
	}

	public static final void register(EntityPlayer player){
		 player.registerExtendedProperties(NAME, new ExtendedPropertiesRadTarget(player));
	}
	
	public static final ExtendedPropertiesRadTarget get(EntityPlayer player){
		 return (ExtendedPropertiesRadTarget)player.getExtendedProperties(NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound parCompound){
		NBTTagCompound properties = new NBTTagCompound();
		properties.setDouble("dose", this.dose);
		parCompound.setTag(NAME, properties);
		System.out.println("[TUT SAVE] Dose from NBT: " + this.dose);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound parCompound){
		NBTTagCompound properties = (NBTTagCompound)parCompound.getTag(NAME);
		this.dose = properties.getDouble("dose");
		System.out.println("[TUT LOAD] Dose from NBT: " + this.dose);
	}
	
	@Override
	public void init(Entity entity, World world){
		
	}
	
	public double getDose(){
		return this.dose;
	}
	
	public void setDose(double dose){
		this.dose = dose;
	}
	
	public double getLastRad(){
		return this.lastRad;
	}
	
	public void setLastRad(double lastRad){
		this.lastRad = lastRad;
	}
}
