package com.lucidity.geigercraft;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.ChunkEvent;

public class RadiationEventHandler {
	private boolean justDied = false;
	
	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
			if(ExtendedPropertiesRadTarget.get(player) != null){
				if(justDied){
					props.setDose(0);
					justDied = false;
				}
				int x = (int)Math.floor(player.posX); 
                int y = (int)Math.floor(player.posY); 
                int z = (int)Math.floor(player.posZ);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingDeathEvent (LivingDeathEvent event){
		if (event.entity instanceof EntityPlayer){
			justDied = true;
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event){
		if (event.entity instanceof EntityPlayer && ExtendedPropertiesRadTarget.get((EntityPlayer) event.entity) == null){
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPropertiesRadTarget.register(player);
		}
	}
	
	@SubscribeEvent 
	public void onChunkLoad(ChunkEvent.Load event) { 
		int chunkX = event.getChunk().xPosition;
		int chunkZ = event.getChunk().zPosition;
		System.out.println("chunk at " + chunkX + ", " + chunkZ + " getting loaded");
		System.out.println("Boundries at " + chunkX*16 + ", " + chunkZ*16);
		System.out.println("-----------------------------");
		//event.world.setBlock(chunkX*16+15, 100, chunkZ*16, Blocks.bedrock);
		//event.world.setBlock(chunkX*16, 100, chunkZ*16+15, Blocks.bedrock);
		//event.world.setBlock(chunkX*16+15, 100, chunkZ*16+15, Blocks.bedrock);
	} 
}
