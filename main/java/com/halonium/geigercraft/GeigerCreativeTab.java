package com.halonium.geigercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GeigerCreativeTab extends CreativeTabs {
	public GeigerCreativeTab(int id, String unlocalizedName) {
        super(id, unlocalizedName);
	}
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        //return Item.getItemFromBlock(Blocks.chest);
        return CommonProxy.geigerCounter;
    }
}
