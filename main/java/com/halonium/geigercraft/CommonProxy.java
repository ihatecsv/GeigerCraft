package com.halonium.geigercraft;

import com.halonium.geigercraft.blocks.BlockTestShield;
import com.halonium.geigercraft.blocks.BlockTestSourceHigh;
import com.halonium.geigercraft.blocks.BlockTestSourceLow;
import com.halonium.geigercraft.blocks.BlockTestSourceMedium;
import com.halonium.geigercraft.blocks.BlockTestSourceVeryHigh;
import com.halonium.geigercraft.blocks.BlockTestSourceVeryLow;
import com.halonium.geigercraft.items.ItemGeigerCounter;
import com.halonium.geigercraft.refs.RegisterHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	
	public static Block testSourceVeryLow;
	public static Block testSourceLow;
	public static Block testSourceMedium;
	public static Block testSourceHigh;
	public static Block testSourceVeryHigh;
	public static Block testShield;
	
	public static Item geigerCounter;
	
	public static final CreativeTabs tabGeigerCraft = new GeigerCreativeTab(CreativeTabs.getNextID(), "geigerCraft");

    public void preInit(FMLPreInitializationEvent event) {
    	testSourceVeryLow = new BlockTestSourceVeryLow();
    	testSourceLow = new BlockTestSourceLow();
    	testSourceMedium = new BlockTestSourceMedium();
    	testSourceHigh = new BlockTestSourceHigh();
    	testSourceVeryHigh = new BlockTestSourceVeryHigh();
		testShield = new BlockTestShield();
		geigerCounter = new ItemGeigerCounter();
		
		RegisterHelper.registerBlock(testSourceVeryLow);
		RegisterHelper.registerBlock(testSourceLow);
		RegisterHelper.registerBlock(testSourceMedium);
		RegisterHelper.registerBlock(testSourceHigh);
		RegisterHelper.registerBlock(testSourceVeryHigh);
		RegisterHelper.registerBlock(testShield);
		
		RegisterHelper.registerItem(geigerCounter);
		
		MinecraftForge.EVENT_BUS.register(new RadiationEventHandler());
		FMLCommonHandler.instance().bus().register(new RadiationEventHandlerFML());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}