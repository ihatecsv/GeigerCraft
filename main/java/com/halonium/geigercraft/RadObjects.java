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
			new RadObj("geigercraft:geigercraft_testSourceVeryLow/0", 0.0001, 5, 0),
			new RadObj("geigercraft:geigercraft_testSourceLow/0", 0.001, 5, 0),
			new RadObj("geigercraft:geigercraft_testSourceMedium/0", 0.01, 10, 0),
			new RadObj("geigercraft:geigercraft_testSourceHigh/0", 0.1, 20, 0),
			new RadObj("geigercraft:geigercraft_testSourceVeryHigh/0", 1, 30, 0),
			new RadObj("IC2:blockOreUran/0", 0.0001, 5, 0), //IC2 Uranium Ore
			new RadObj("IC2:blockMetal/3", 0.001, 5, 0), //IC2 Uranium Block
			new RadObj("IC2:blockNuke/0", 0.00001, 5, 0), //IC2 Nuke
			new RadObj("NetherOres:tile.netherores.ore.0/11", 0.0001, 5, 0), //Nether Ores Nether Uranium Ore
			new RadObj("chisel:uraniumblock/0", 0.001, 5, 0), //Chisel Uranium Block 1
			new RadObj("chisel:uraniumblock/1", 0.001, 5, 0), //Chisel Uranium Block 2
			new RadObj("chisel:uraniumblock/2", 0.001, 5, 0), //Chisel Uranium Block 3
			new RadObj("chisel:uraniumblock/3", 0.001, 5, 0), //Chisel Uranium Block 4
			new RadObj("chisel:uraniumblock/4", 0.001, 5, 0), //Chisel Uranium Block 5
			new RadObj("chisel:uraniumblock/5", 0.001, 5, 0), //Chisel Uranium Block 6
			new RadObj("denseores:block0/8", 0.0001, 5, 0), //Dense Ores Dense Uranium Ore
			new RadObj("denseores:block1/13", 0.0001, 5, 0), //Dense Ores Dense Nether Uranium Ore
			new RadObj("minechem:tile.oreUranium/0", 0.0001, 5, 0), //MineChem Uranium Ore
			new RadObj("BigReactors:YelloriteOre/0", 0.0001, 5, 0), //BigReactors Yellorite Ore
			new RadObj("BigReactors:BRMetalBlock/0", 0.001, 5, 0), //BigReactors Yellorium Block
			new RadObj("BigReactors:BRMetalBlock/1", 0.001, 5, 0), //BigReactors Cyanite Block
			new RadObj("BigReactors:BRMetalBlock/3", 0.001, 5, 0), //BigReactors Blutonium Block
			new RadObj("BigReactors:BRMetalBlock/4", 0.001, 5, 0), //BigReactors Ludicrite Block
			new RadObj("BigReactors:YelloriumFuelRod/0", 0.001, 5, 0), //BigReactors Yellorium Fuel Rod
			new RadObj("BigReactors:tile.fluid.yellorium.still/0", 0.001, 5, 0), //BigReactors Still Fluid Yellorium
			new RadObj("BigReactors:tile.fluid.yellorium.still/1", 0.001, 5, 0), //BigReactors Flowing Fluid Yellorium
			new RadObj("BigReactors:tile.fluid.cyanite.still/0", 0.001, 5, 0), //BigReactors Still Fluid Cyanite
			new RadObj("BigReactors:tile.fluid.cyanite.still/1", 0.001, 5, 0), //BigReactors Flowing Fluid Cyanite
			new RadObj("ReactorCraft:reactorcraft_block_ore/1", 0.0001, 5, 0), //ReactorCraft Pitchblende
			new RadObj("ReactorCraft:reactorcraft_block_ore/5", 0.0001, 5, 0), //ReactorCraft Pitchblende 2
			new RadObj("ReactorCraft:reactorcraft_block_ore/9", 0.0001, 5, 0), //ReactorCraft Thorite
			new RadObj("minecraft:dragon_egg/0", 1, 30, 0), //Minecraft Dragon Egg
			
			//Shielding blocks
			new RadObj("geigercraft:geigercraft_testShield/0", 0, 0, 0.0001),
			new RadObj("ImmersiveEngineering:storage/2", 0, 0, 0.0001), //Immersive Engineering Block of Lead
			new RadObj("IC2:blockMetal/4", 0, 0, 0.0001), //IC2 Lead Block
			new RadObj("Railcraft:cube/11", 0, 0, 0.0001), //Railcraft Block of Lead
			new RadObj("ThermalFoundation:Storage/3", 0, 0, 0.0001), //Thermal Founcation Lead Block
			new RadObj("chisel:leadblock/0", 0, 0, 0.0001), //Chisel Block of Lead 1
			new RadObj("chisel:leadblock/1", 0, 0, 0.0001), //Chisel Block of Lead 2
			new RadObj("chisel:leadblock/2", 0, 0, 0.0001), //Chisel Block of Lead 3
			new RadObj("chisel:leadblock/3", 0, 0, 0.0001), //Chisel Block of Lead 4
			new RadObj("chisel:leadblock/4", 0, 0, 0.0001), //Chisel Block of Lead 5
			new RadObj("chisel:leadblock/5", 0, 0, 0.0001), //Chisel Block of Lead 6
			new RadObj("eng_toolbox:metal/3", 0, 0, 0.0001), //Engineer's Toolbox Lead Block
			new RadObj("factorization:ResourceBlock/2", 0, 0, 0.0001), //Factorization Block of Lead
			new RadObj("minechem:tile.leadChest/2", 0, 0, 0.0001), //MineChem Leaded Chest
			new RadObj("ReactorCraft:reactorcraft_block_reactor/1", 0.00001, 5, 0.0001), //ReactorCraft Neutron Absorber
			new RadObj("BigReactors:BRMultiblockGlass/0", 0, 0, 0.0001), //BigReactors Reactor Glass
			new RadObj("BigReactors:BRReactorPart/0", 0, 0, 0.0001), //BigReactors Reactor Casing
			new RadObj("BigReactors:BRReactorPart/2", 0.00001, 5, 0.0001) //BigReactors Reactor Control Rod
			//new RadObj("", 0, 0, 0.0001)
	};
}
