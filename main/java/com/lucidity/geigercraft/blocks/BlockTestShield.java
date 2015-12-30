package com.lucidity.geigercraft.blocks;

import com.lucidity.geigercraft.CommonProxy;
import com.lucidity.geigercraft.refs.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockTestShield extends Block{
	public BlockTestShield(){
		super(Material.redstoneLight);
		setBlockName("testShield");
		setHardness(0.8F);
		setBlockTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CommonProxy.tabGeigerCraft);
		setLightLevel(0.3F);
	}
}
