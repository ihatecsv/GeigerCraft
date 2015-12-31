package com.halonium.geigercraft;

import net.minecraft.block.material.Material;

public class RadObjects {
	public static final RadObj findObj(String id){
		for(int i = 0; i < RADOBJS.length; i++){
			if(RADOBJS[i].id.equals(id)){
				return RADOBJS[i];
			}
		}
		return null;
	}
	
	public static final int maxRange(){
		int maxRange = 0;
		for(int i = 0; i < RADOBJS.length; i++){
			if(RADOBJS[i].radStrength>maxRange){
				maxRange = RADOBJS[i].radStrength;
			}
		}
		return maxRange;
	}
	
	public static final double materialVal(Material material){
		if(material == Material.air ||
				material == Material.circuits ||
				material == Material.fire ||
				material == Material.plants ||
				material == Material.dragonEgg ||
				material == Material.leaves ||
				material == Material.vine ||
				material == Material.web ||
				material == Material.cake ||
				material == Material.portal ||
				material == Material.glass ||
				material == Material.cloth ||
				material == Material.carpet ||
				material == Material.sponge ||
				material == Material.gourd || 
				material == Material.cactus){
			return 0.00000002;
		}else if(material == Material.grass ||
				material == Material.ground || 
				material == Material.wood ||
				material == Material.coral ||
				material == Material.clay ||
				material == Material.sand ||
				material == Material.tnt ||
				material == Material.redstoneLight){
			return 0.0000001;
		}else if(material == Material.rock){
			return 0.0000008;
		}else if(material == Material.iron ||
				material == Material.anvil ||
				material == Material.lava){
			return 0.000001;
		}else if(material == Material.water ||
				material == Material.ice ||
				material == Material.packedIce){
			return 0.0000003;
		}else if(material == Material.snow || material == Material.craftedSnow){
			return 0.00000007;	
		}
		return 0.0000001;
	}
	
	public static final RadObj[] RADOBJS = {
			//Radioactive blocks
			new RadObj("tile.testSourceVeryLow/0", 0.0001, 5, 0),
			new RadObj("tile.testSourceLow/0", 0.001, 5, 0),
			new RadObj("tile.testSourceMedium/0", 0.01, 10, 0),
			new RadObj("tile.testSourceHigh/0", 0.1, 20, 0),
			new RadObj("tile.testSourceVeryHigh/0", 1, 30, 0),
			new RadObj("blockOreUran/0", 0.0001, 5, 0), //IC2 Uranium Ore
			new RadObj("blockMetal/3", 0.001, 5, 0), //IC2 Uranium Block
			new RadObj("blockNuke/0", 0.00001, 5, 0), //IC2 Nuke
			new RadObj("tile.netherores.ore.0/11", 0.0001, 5, 0), //Nether Ores Nether Uranium Ore
			new RadObj("tile.chisel.uraniumblock/0", 0.001, 5, 0), //Chisel Uranium Block 1
			new RadObj("tile.chisel.uraniumblock/1", 0.001, 5, 0), //Chisel Uranium Block 2
			new RadObj("tile.chisel.uraniumblock/2", 0.001, 5, 0), //Chisel Uranium Block 3
			new RadObj("tile.chisel.uraniumblock/3", 0.001, 5, 0), //Chisel Uranium Block 4
			new RadObj("tile.chisel.uraniumblock/4", 0.001, 5, 0), //Chisel Uranium Block 5
			new RadObj("tile.chisel.uraniumblock/5", 0.001, 5, 0), //Chisel Uranium Block 6
			new RadObj("tile.denseores:block0/8", 0.0001, 5, 0), //Dense Ores Dense Uranium Ore
			new RadObj("tile.denseores:block1/13", 0.0001, 5, 0), //Dense Ores Dense Nether Uranium Ore
			new RadObj("tile.oreUranium/0", 0.0001, 5, 0), //MineChem Uranium Ore
			new RadObj("tile.brOre/0", 0.0001, 5, 0), //BigReactors Yellorite Ore
			new RadObj("tile.brMetal/0", 0.001, 5, 0), //BigReactors Yellorium Block
			new RadObj("tile.brMetal/1", 0.001, 5, 0), //BigReactors Cyanite Block
			new RadObj("tile.brMetal/3", 0.001, 5, 0), //BigReactors Blutonium Block
			new RadObj("tile.brMetal/4", 0.001, 5, 0), //BigReactors Ludicrite Block
			new RadObj("tile.yelloriumFuelRod/0", 0.001, 5, 0), //BigReactors Yellorium Fuel Rod
			new RadObj("tile.fluid.yellorium.still/0", 0.001, 5, 0), //BigReactors Still Fluid Yellorium
			new RadObj("tile.fluid.yellorium.still/1", 0.001, 5, 0), //BigReactors Flowing Fluid Yellorium
			new RadObj("tile.fluid.cyanite.still/0", 0.001, 5, 0), //BigReactors Still Fluid Cyanite
			new RadObj("tile.fluid.cyanite.still/1", 0.001, 5, 0), //BigReactors Flowing Fluid Cyanite
			new RadObj("tile.Ore/1", 0.0001, 5, 0), //ReactorCraft Pitchblende
			new RadObj("tile.Ore/5", 0.0001, 5, 0), //ReactorCraft Pitchblende 2
			new RadObj("tile.Ore/9", 0.0001, 5, 0), //ReactorCraft Thorite
			new RadObj("tile.dragonEgg/0", 1, 30, 0), //Minecraft Dragon Egg
			
			//Shielding blocks
			new RadObj("tile.testShield/0", 0, 0, 0.0001),
			new RadObj("tile.ImmersiveEngineering.storage/2", 0, 0, 0.0001), //Immersive Engineering Block of Lead
			new RadObj("blockMetal/4", 0, 0, 0.0001), //IC2 Lead Block
			new RadObj("tile.railcraft.cube/11", 0, 0, 0.0001), //Railcraft Block of Lead
			new RadObj("tile.thermalfoundation.storage/3", 0, 0, 0.0001), //Thermal Founcation Lead Block
			new RadObj("tile.chisel.leadblock/0", 0, 0, 0.0001), //Chisel Block of Lead 1
			new RadObj("tile.chisel.leadblock/1", 0, 0, 0.0001), //Chisel Block of Lead 2
			new RadObj("tile.chisel.leadblock/2", 0, 0, 0.0001), //Chisel Block of Lead 3
			new RadObj("tile.chisel.leadblock/3", 0, 0, 0.0001), //Chisel Block of Lead 4
			new RadObj("tile.chisel.leadblock/4", 0, 0, 0.0001), //Chisel Block of Lead 5
			new RadObj("tile.chisel.leadblock/5", 0, 0, 0.0001), //Chisel Block of Lead 6
			new RadObj("tile.e_metal/3", 0, 0, 0.0001), //Engineer's Toolbox Lead Block
			new RadObj("tile.factorization.ResourceBlock/2", 0, 0, 0.0001), //Factorization Block of Lead
			new RadObj("tile.Reactor/1", 0.00001, 5, 0.0001), //ReactorCraft Neutron Absorber
			new RadObj("tile.brMultiblockGlass/0", 0, 0, 0.0001), //BigReactors Reactor Glass
			new RadObj("tile.blockReactorPart/0", 0, 0, 0.0001), //BigReactors Reactor Casing
			new RadObj("tile.blockReactorPart/2", 0.00001, 5, 0.0001) //BigReactors Reactor Control Rod
			//new RadObj("", 0, 0, 0.0001)
			
	};
}
