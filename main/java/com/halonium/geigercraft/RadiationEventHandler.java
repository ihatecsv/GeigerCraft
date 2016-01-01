package com.halonium.geigercraft;

import java.util.*;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
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
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;

public class RadiationEventHandler {
	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
			if(ExtendedPropertiesRadTarget.get(player) != null){
				if(props.justDied){
					props.dose = 0;
					props.justDied = false;
				}
				int x = (int)Math.floor(player.posX); 
                int y = (int)Math.floor(player.posY); 
                int z = (int)Math.floor(player.posZ);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingDeathEvent (LivingDeathEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
			props.justDied = true;
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event){
		if(event.entity instanceof EntityPlayer && ExtendedPropertiesRadTarget.get((EntityPlayer)event.entity) == null){
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPropertiesRadTarget.register(player);
		}
	}
	
	public static HashMap<ChunkCoordIntPair, List<Point3D>> chunkList = new HashMap<ChunkCoordIntPair, List<Point3D>>();
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onChunkLoad(ChunkEvent.Load event) { 
		int chunkX = event.getChunk().xPosition;
		int chunkZ = event.getChunk().zPosition;
		int cornerX = chunkX*16;
		int cornerZ = chunkZ*16;
		
		//System.out.println("chunk at " + chunkX + ", " + chunkZ + " getting loaded");
		//System.out.println("Boundaries at " + cornerX + ", " + cornerZ);
		//System.out.println("-----------------------------");
		ChunkCoordIntPair chunk = new ChunkCoordIntPair(chunkX,chunkZ);
		if(!chunkList.containsKey(chunk)){
			chunkList.put(chunk, new ArrayList<Point3D>());
			//System.out.println(chunkList.size());
			for(int i = cornerX; i < cornerX+16; i++){
				for(int k = cornerZ; k < cornerZ+16; k++){
					for(int j = 0; j < 256; j++){
						Block sourceBlock = event.world.getBlock(i, j, k);
						int sourceMeta = event.world.getBlockMetadata(i, j, k);
        				String sourceModId = GameRegistry.findUniqueIdentifierFor(sourceBlock).modId;
        				String sourceName = GameRegistry.findUniqueIdentifierFor(sourceBlock).name;
        				RadObj source = RadObjects.findObj(sourceModId + ":" + sourceName + "/" + sourceMeta);
						if(source != null){
							chunkList.get(chunk).add(new Point3D(i, j, k));
							System.out.println("Found new sourceblock at " + i + ", " + j + ", " + k);
							System.out.println(sourceName + "/" + sourceMeta);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onChunkUnload(ChunkEvent.Unload event) { 
		int chunkX = event.getChunk().xPosition;
		int chunkZ = event.getChunk().zPosition;
		int cornerX = chunkX*16;
		int cornerZ = chunkZ*16;
		
		/*ChunkCoordIntPair chunk = new ChunkCoordIntPair(chunkX,chunkZ);
		if(chunkList.containsKey(chunk)){
			chunkList.remove(chunk);
			System.out.println("XXXXXXXXXXXXXXXXXXX-Removed chunk!");
		}*/
	} 
	
	@SubscribeEvent
	public void onBlockEvent(BlockEvent event){
		int posX = event.x;
		int posY = event.y;
		int posZ = event.z;
		
		int chunkX = (int)Math.floor(posX/16);
		int chunkZ = (int)Math.floor(posZ/16);
		
		int cornerX = chunkX*16;
		int cornerZ = chunkZ*16;
		
		ChunkCoordIntPair chunk = new ChunkCoordIntPair(chunkX,chunkZ);
		
		if(chunkList.containsKey(chunk)){
			chunkList.remove(chunk);
		}
	}
}
