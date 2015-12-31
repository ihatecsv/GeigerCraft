package com.halonium.geigercraft;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.ChunkCoordIntPair;

public class RadiationEventHandlerFML {
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent event) {
		if(event.phase == TickEvent.Phase.END){ 
            if(event.side.isServer()) { 
            	EntityPlayer player = (EntityPlayer)event.player;
				ExtendedPropertiesRadTarget props = ExtendedPropertiesRadTarget.get(player);
				
				double urX = player.posX;
                double urY = player.posY;
                double urZ = player.posZ;
                
				int x = (int)Math.floor(urX); 
                int y = (int)Math.floor(urY) + 1; 
                int z = (int)Math.floor(urZ);
                
                int chunkX = (int)Math.floor(x/16);
                int chunkZ = (int)Math.floor(z/16);
                
        		int cornerX = chunkX*16;
        		int cornerZ = chunkZ*16;
                
                double currentRad = 0;
                double shieldValue = 0;
                
                for(int n=chunkX-2; n < chunkX+2; n++){
                	for(int m=chunkZ-2; m < chunkZ+2; m++){
                		ChunkCoordIntPair chunk = new ChunkCoordIntPair(chunkX,chunkZ);
                		if(RadiationEventHandler.chunkList.containsKey(chunk)){
                			for(Point3D point : RadiationEventHandler.chunkList.get(chunk)){
                				double dist = Math.sqrt(((point.x-urX)*(point.x-urX))+((point.y-urY)*(point.y-urY))+((point.z-urZ)*(point.z-urZ)));
                				Block sourceBlock = player.worldObj.getBlock(point.x, point.y, point.z);
	                			String sourceName = sourceBlock.getUnlocalizedName();
	            				int sourceMeta = player.worldObj.getBlockMetadata(point.x, point.y, point.z);
	            				RadObj source = RadObjects.findObj(sourceName + "/" + sourceMeta);
	            				//System.out.println("Checking source at " + point.x + ", " + point.y + ", " + point.z);
	            				//System.out.println(sourceName + "/" + sourceMeta);
                				if(source != null && dist < source.radStrength){
                					Point3D[] shielding = RadiationCalculator.traverseLine(x,y,z,point.x,point.y,point.z);
                					for(int g = 0; g < shielding.length; g++){
                						Block shieldBlock = player.worldObj.getBlock(shielding[g].x, shielding[g].y, shielding[g].z);
                						String shieldName = shieldBlock.getUnlocalizedName();
        	            				int shieldMeta = player.worldObj.getBlockMetadata(shielding[g].x, shielding[g].y, shielding[g].z);
                						RadObj shield = RadObjects.findObj(shieldName + "/" + shieldMeta);
                						if(shield != null){
                							if(shield.absorbValue > 0){
                								shieldValue = shieldValue + shield.absorbValue;
                							}
                						}else{
                							shieldValue = shieldValue + RadObjects.materialVal(shieldBlock.getMaterial());
                						}
                					}
                					currentRad = (currentRad + ((source.radStrength-dist)/source.radStrength) * source.radValue) - shieldValue;
                					if(currentRad < 0){
                						currentRad = 0;
                					}
                				}
                			}
                		}else{
                			RadiationEventHandler.chunkList.put(chunk, new ArrayList<Point3D>());
                			for(int i = cornerX; i < cornerX+16; i++){
                				for(int k = cornerZ; k < cornerZ+16; k++){
                					for(int j = 0; j < 256; j++){
                						Block sourceBlock = player.worldObj.getBlock(i, j, k);
                						String sourceName = sourceBlock.getUnlocalizedName();
                		    			int sourceMeta = player.worldObj.getBlockMetadata(i, j, k);
                						RadObj source = RadObjects.findObj(sourceName + "/" + sourceMeta);
                						if(source != null){
                							RadiationEventHandler.chunkList.get(chunk).add(new Point3D(i, j, k));
                							System.out.println("Found new sourceblock at " + i + ", " + j + ", " + k);
                							System.out.println(sourceName + "/" + sourceMeta);
                						}
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
	                if(props.dose > 30){
	                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(10.0F));
	                }else if(props.dose > 8){
	                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(8.0F));
	                }else if(props.dose > 6){
	                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(5.0F));
	                }else if(props.dose > 2){
	                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(2.0F));
	                }else if(props.dose > 1){
	                	player.attackEntityFrom(RadiationDamageSource.radiationSickness, (float)(1.0F));
	                }
                }
                //dose = dose + (currentRad/216000); //Accurate
                props.dose = props.dose + (currentRad/2000); //Sped up
                props.lastRad = currentRad;
				if(player.getCurrentEquippedItem() != null){
					if(player.getCurrentEquippedItem().getItem().getUnlocalizedName().equals("item.geigerCounter")){
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "----------------"));
						player.addChatMessage(new ChatComponentText("Current readings: " + RadiationCalculator.formatSv(props.lastRad, true)));
		                player.addChatMessage(new ChatComponentText("Current dose: " + RadiationCalculator.formatSv(props.dose, false)));
		                //player.addChatMessage(new ChatComponentText("Pos: ยง4" + x + " ยง2" + y + " ยง1" + z));
		                player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "----------------"));
						if(props.lastRad != 0){
							if(props.lastRad > 0.001){
								//player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            	}else if(props.lastRad > 0.0001){
			            		if(rand.nextInt(2)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(props.lastRad > 0.00001){
			            		if(rand.nextInt(4)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(props.lastRad >= 0.000001){
			            		if(rand.nextInt(8)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(props.lastRad >= 0.0000001){
			            		if(rand.nextInt(16)<1){
			            			player.worldObj.playSoundAtEntity(player, "geigercraft:clickSound", 1.0F, 1.0F);
			            		}
			            	}else if(props.lastRad < 0.0000001){
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