package com.halonium.geigercraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPropertiesRadTarget implements IExtendedEntityProperties {
	public final static String NAME = "gcRadTarget";
	private EntityPlayer player;
	public double dose;
	public double lastRad;
	public boolean justDied;
	
	public ExtendedPropertiesRadTarget(EntityPlayer player){
		 this.player=player;
		 this.dose = 0;
		 this.lastRad = 0;
		 this.justDied = false;
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
		properties.setBoolean("justDied", this.justDied);
		parCompound.setTag(NAME, properties);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound parCompound){
		NBTTagCompound properties = (NBTTagCompound)parCompound.getTag(NAME);
		this.dose = properties.getDouble("dose");
		this.justDied = properties.getBoolean("justDied");
	}
	
	@Override
	public void init(Entity entity, World world){
		
	}
}
