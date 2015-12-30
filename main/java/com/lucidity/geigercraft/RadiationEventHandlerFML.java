package com.lucidity.geigercraft;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.lucidity.geigercraft.RadiationEventHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class RadiationEventHandlerFML {
	private int tickCount = 0;
    /*@SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
    	EntityPlayer player = (EntityPlayer) event.player;
    	ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
    	RadiationEventHandler.dose = props.getDose();
    }*/
	
	Random rand = new Random();
	int range = RadObjects.maxRange();
	
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent event) { 
        if(event.phase == TickEvent.Phase.END){ 
            if(event.side.isServer()) { 
            	EntityPlayer player = (EntityPlayer)event.player;
				ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
				if(tickCount > 10){
					Block newBlock = player.worldObj.getBlock(64, 6, 64);
        			//String sourceModId = GameRegistry.findUniqueIdentifierFor(sourceBlock).modId;
    				//String sourceName = GameRegistry.findUniqueIdentifierFor(sourceBlock).name;
        			//String newName = newBlock.getUnlocalizedName();
    				//int newMeta = player.worldObj.getBlockMetadata(64, 6, 64);
    				//player.addChatMessage(new ChatComponentText(newName + "/" + newMeta));
	                double urX = player.posX;
	                double urY = player.posY;
	                double urZ = player.posZ;
	                
					int x = (int)Math.floor(urX); 
	                int y = (int)Math.floor(urY) + 1; 
	                int z = (int)Math.floor(urZ);
	                
	                double dose = props.getDose();
	                
	                double currentRad = 0;
	                double shieldValue = 0;
	                for(int i = x-range; i < x+range; i++){
	                	for(int j = y-range; j < y+range; j++){
	                		for(int k = z-range; k < z+range; k++){
	                			Block sourceBlock = player.worldObj.getBlock(i, j, k);
	                			//String sourceModId = GameRegistry.findUniqueIdentifierFor(sourceBlock).modId;
	            				//String sourceName = GameRegistry.findUniqueIdentifierFor(sourceBlock).name;
	                			String sourceName = sourceBlock.getUnlocalizedName();
	            				int sourceMeta = player.worldObj.getBlockMetadata(i, j, k);
	            				//RadObj source = RadObjects.findObj(sourceModId + ":" + sourceName + "/" + sourceMeta);
	            				RadObj source = RadObjects.findObj(sourceName + "/" + sourceMeta);
	                			if(source != null && source.radStrength > 0 && source.radValue > 0){
	                				double dist = Math.sqrt(((i-urX)*(i-urX))+((j-urY)*(j-urY))+((k-urZ)*(k-urZ)));
	                				if(dist < source.radStrength){
	                					Point3D[] shielding = RadiationCalculator.traverseLine(x,y,z,i,j,k);
	                					for(int m = 0; m < shielding.length; m++){
	                						Block shieldBlock = player.worldObj.getBlock((int)shielding[m].x, (int)shielding[m].y, (int)shielding[m].z);
	                						//String shieldModId = GameRegistry.findUniqueIdentifierFor(sourceBlock).modId;
	        	            				//String shieldName = GameRegistry.findUniqueIdentifierFor(sourceBlock).name;
	                						String shieldName = shieldBlock.getUnlocalizedName();
	        	            				int shieldMeta = player.worldObj.getBlockMetadata(i, j, k);
	                						RadObj shield = RadObjects.findObj(shieldName + "/" + shieldMeta);
	                						//RadObj shield = RadObjects.findObj(shieldModId + ":" + shieldName + "/" + shieldMeta);
	                						if(shield != null){
	                							if(shield.absorbValue > 0){
	                								shieldValue = shieldValue + shield.absorbValue;
	                							}
	                						}else{
	                							shieldValue = shieldValue + RadObjects.materialVal(shieldBlock.getMaterial());
	                						}
	                						//player.worldObj.setBlock((int)shielding[m].x,(int)shielding[m].y,(int)shielding[m].z,Blocks.clay);
	                					}
	                					currentRad = (currentRad + ((source.radStrength-dist)/source.radStrength) * source.radValue) - shieldValue;
	                					if(currentRad < 0){
	                						currentRad = 0;
	                					}
	                				}
	                			}
	                		}
	                	}
	                }
	                Random rand = new Random();
	                double randDose = Math.random()*0.0000001;
	                if(rand.nextInt(20) < 1){
	                	randDose = randDose + Math.random()*0.0000001;
	                }
	                currentRad = currentRad + randDose;
	                /*if(currentRad > 1){
	                	player.attackEntityFrom(RadiationDamageSource.radiationBurn, (float)(currentRad)*5.0F);
	                }*/
	                
	                if(rand.nextInt(100) < 1){
		                if(dose > 30){
		                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(10.0F));
		                }else if(dose > 8){
		                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(8.0F));
		                }else if(dose > 6){
		                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(5.0F));
		                }else if(dose > 2){
		                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(2.0F));
		                }else if(dose > 1){
		                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(1.0F));
		                }
	                }
	                //dose = dose + (currentRad/216000); //Accurate
	                dose = dose + (currentRad/2000); //Sped up
	                props.setDose(dose);
	                props.setLastRad(currentRad);
					tickCount = 0;
				}
				tickCount++;
				if(player.getCurrentEquippedItem() != null){
					if(player.getCurrentEquippedItem().getItem().getUnlocalizedName().equals("item.geigerCounter")){
						double lastRad = props.getLastRad();
						double lastDose = props.getDose();
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "----------------"));
						player.addChatMessage(new ChatComponentText("Current readings: " + RadiationCalculator.formatSv(lastRad, true)));
		                player.addChatMessage(new ChatComponentText("Current dose: " + RadiationCalculator.formatSv(lastDose, false)));
		                //player.addChatMessage(new ChatComponentText("Pos: ยง4" + x + " ยง2" + y + " ยง1" + z));
		                player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "----------------"));
						if(lastRad != 0){
							if(lastRad > 0.001){
								//player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            	}else if(lastRad > 0.0001){
			            		if(rand.nextInt(2)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(lastRad > 0.00001){
			            		if(rand.nextInt(4)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(lastRad >= 0.000001){
			            		if(rand.nextInt(8)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(lastRad >= 0.0000001){
			            		if(rand.nextInt(16)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(lastRad < 0.0000001){
			            		if(rand.nextInt(32)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}
						}
					}
				}
            }
		}
	}
}
