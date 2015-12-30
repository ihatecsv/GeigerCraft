package com.lucidity.geigercraft.items;

import com.lucidity.geigercraft.CommonProxy;
import com.lucidity.geigercraft.ExtendedPropertiesRadTarget;
import com.lucidity.geigercraft.RadiationCalculator;
import com.lucidity.geigercraft.refs.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemGeigerCounter extends Item{
	public ItemGeigerCounter() {
        maxStackSize = 1;
        setCreativeTab(CommonProxy.tabGeigerCraft);
        setUnlocalizedName("geigerCounter");
        setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
	}
	
	/*@Override
    public boolean onItemRightClick(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
		if(player.getCurrentEquippedItem() != null){
			if(player.getCurrentEquippedItem().getItem().getUnlocalizedName().equals("item.geigerCounter")){
				double lastRad = props.getLastRad();
				double lastDose = props.getDose();
				
				
			}
		}
		return true;
	}*/
}
