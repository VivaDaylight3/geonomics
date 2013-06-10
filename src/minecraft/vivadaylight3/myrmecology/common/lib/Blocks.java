package vivadaylight3.myrmecology.common.lib;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class Blocks
{

	public static BiomeGenBase getBiome(World world, int x, int z){
		
		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);
		
		return biome;
		
	}
	
	public static int getBlockOrientation(int x, int y, int z, EntityLiving entity){
		
		int angle = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int change = 0;
		
		switch(angle){
			
			case 0:
				change = 0;
				break;

			case 1:
				change = 1;
				break;

			case 2:
				change = 2;
				break;

			case 3:
				change = 3;
				break;
			
		}
		
		return change;
		
	}
	
	/**
	 * Returns the side name of the block depending on its metadata and side
	 * @author VivaDaylight3
	 * @param side
	 * @param metadata
	 * @param basemeta
	 * @return String 'top', 'bottom', 'front', 'input', 'output' or 'back'
	 */
		
	public static String getBlockSide(int side, int metadata, int basemeta){
		
		int meta1 = basemeta;
		int meta2 = basemeta + 1;
		int meta3 = basemeta + 2;
		int meta4 = basemeta + 3;
		
		if (side == 1){
			
			return "top";
			
		}if (side == 0){
			
			return "bottom";
		
		}else if((metadata == meta1 && side == 2) || (metadata == meta2 && side == 5) || (metadata == meta3 && side == 3) || (metadata == meta4 && side == 4)){
			
			return "front";
			
		}else if((metadata == meta1 && side == 4) || (metadata == meta2 && side == 2) || (metadata == meta3 && side == 5) || (metadata == meta4 && side == 3)){
			
			return "input";
			
		}else if((metadata == meta1 && side == 5) || (metadata == meta2 && side == 3) || (metadata == meta3 && side == 4) || (metadata == meta4 && side == 2)){
			
			return "output";
			
		}else{
		
			return "back";
		
		}
		
	}
	
	/**
	 * Gets the input side depending on metadata
	 * @param int metadata
	 * @param int block's lowest metadata
	 * @return int input side number
	 */
	public static int getBlockInput(int metadata, int basemetadata){
		
		if(metadata == basemetadata){
			
			return 4;
			
		}else if(metadata == basemetadata + 1){
			
			return 2;
			
		}else if(metadata == basemetadata + 2){
			
			return 5;
			
		}else{
			
			return 3;
			
		}
		
	}
	
	/**
	 * Gets the output side depending on metadata
	 * @param int metadata
	 * @param int block's lowest metadata
	 * @return int output side number
	 */
	public static int getBlockOutput(int metadata, int basemetadata){
		
		if(metadata == basemetadata){
			
			return 5;
			
		}else if(metadata == basemetadata + 1){
			
			return 3;
			
		}else if(metadata == basemetadata + 2){
			
			return 4;
			
		}else{
			
			return 2;
			
		}
		
	}
	
}
