package com.halonium.geigercraft.blocks;

import com.halonium.geigercraft.CommonProxy;
import com.halonium.geigercraft.refs.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockTestSourceLow extends Block{
	public BlockTestSourceLow(){
		super(Material.redstoneLight);
		setBlockName("testSourceLow");
		setHardness(0.8F);
		setBlockTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CommonProxy.tabGeigerCraft);
		setLightLevel(0.3F);
	}
}
