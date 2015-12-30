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

public class BlockTestSourceVeryLow extends Block{
	public BlockTestSourceVeryLow(){
		super(Material.redstoneLight);
		setBlockName("testSourceVeryLow");
		setHardness(0.8F);
		setBlockTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CommonProxy.tabGeigerCraft);
		setLightLevel(0.3F);
	}
	/*
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        float f = 0.01F;
        return AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)((float)(j + 1) - f), (double)(k + 1));
    }
	
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.cactus, 1.0F);
        entity.setFire(2);
    }
    */
}
