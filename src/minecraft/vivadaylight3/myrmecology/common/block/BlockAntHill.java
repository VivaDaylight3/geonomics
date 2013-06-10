package vivadaylight3.myrmecology.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.item.ItemExtractor;
import vivadaylight3.myrmecology.common.lib.Variables;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockAntHill extends Block
{
	
	public static final int[] hillMeta = {0, 1, 2, 3, 4, 5, 6};
	
	private static final String NAME = "Ant Hill";
	
	public static final String[] hillNames = {"Forest "+NAME, "Hillside "+NAME, "Desert "+NAME, "Swamp "+NAME, 
		"Plains "+NAME, "Jungle "+NAME, "Snowy "+NAME};
		
	private static Icon[] icons;
	
	private final String[] iconNames = {Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME, 
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Forest", 
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Hills", 
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Desert",
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Swamp", 
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Plains", 
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Jungle", 
			Myrmecology.TEXTURE_PREFIX+Myrmecology.ITEM_ANT_NAME+"_Snow"};
	
	public BlockAntHill(int par1)
	{
		super(par1, Material.ground);
		setUnlocalizedName("antHill");
		setCreativeTab(Myrmecology.tabMyrmecology);
		setStepSound(Block.soundGrassFootstep);
		setHardness(1.0F);
		setResistance(1.0F);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		
		icons = Variables.iconsToArray(iconRegister, iconNames);
		
	}
	
	@Override
	public Icon getIcon(int side, int metadata){
		
		return icons[metadata];
				
	}
	
	public String getUnlocalizedName(ItemStack itemStack)
    {
		
    	return this.getUnlocalizedName() + hillNames[itemStack.getItemDamage()];
    
    }
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		
		for (int k = 0; k < hillMeta.length; k++){
		
			par3List.add(new ItemStack(this, 1, k));
			
		}
    }

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		
		return true;
		
	}

	public void harvestBlock(World world, EntityPlayer entity, int x, int y, int z){
		
		if(!world.isRemote){
		
			ItemStack tool = entity.getCurrentEquippedItem();
			ItemStack extractor = new ItemStack(Myrmecology.itemExtractor);
		
			if(tool != null && tool == extractor){
			
				int meta = world.getBlockMetadata(x, y, z);
				
				for(int k = 0; k < hillMeta.length * 5 + 1; k++){
					
					if(meta == k){
						
						ItemStack drone = new ItemStack(Myrmecology.itemAnt, k + ItemAnt.typeMeta[1], 1);
						ItemStack queen = new ItemStack(Myrmecology.itemAnt, k + ItemAnt.typeMeta[0], 1);
						entity.dropPlayerItem(drone);
						entity.dropPlayerItem(queen);
						
					}
					
				}
			/*
				switch(meta){
				
					case FOREST_METADATA:
						ItemStack drone = new ItemStack(Myrmecology.itemAnt, ItemAnt.FOREST_METADATA + ItemAnt.DRONE_METADATA, 1);
						ItemStack queen = new ItemStack(Myrmecology.itemAnt, ItemAnt.FOREST_METADATA + ItemAnt.QUEEN_METADATA, 1);
						entity.dropPlayerItem(drone);
						entity.dropPlayerItem(queen);
						break;
						
					case HILLS_METADATA:
						ItemStack drone2 = new ItemStack(Myrmecology.itemAnt, ItemAnt.HILLS_METADATA + ItemAnt.DRONE_METADATA, 1);
						ItemStack queen2 = new ItemStack(Myrmecology.itemAnt, ItemAnt.HILLS_METADATA + ItemAnt.QUEEN_METADATA, 1);
						entity.dropPlayerItem(drone2);
						entity.dropPlayerItem(queen2);
						break;
						
					case DESERT_METADATA:
						ItemStack drone3 = new ItemStack(Myrmecology.itemAnt, ItemAnt.DESERT_METADATA + ItemAnt.DRONE_METADATA, 1);
						ItemStack queen3 = new ItemStack(Myrmecology.itemAnt, ItemAnt.DESERT_METADATA + ItemAnt.QUEEN_METADATA, 1);
						entity.dropPlayerItem(drone3);
						entity.dropPlayerItem(queen3);
						break;
						
					case SWAMP_METADATA:
						ItemStack drone4 = new ItemStack(Myrmecology.itemAnt, ItemAnt.SWAMP_METADATA + ItemAnt.DRONE_METADATA, 1);
						ItemStack queen4 = new ItemStack(Myrmecology.itemAnt, ItemAnt.SWAMP_METADATA + ItemAnt.QUEEN_METADATA, 1);
						entity.dropPlayerItem(drone4);
						entity.dropPlayerItem(queen4);
						break;
						
					case PLAINS_METADATA:
						ItemStack drone5 = new ItemStack(Myrmecology.itemAnt, ItemAnt.PLAINS_METADATA + ItemAnt.DRONE_METADATA, 1);
						ItemStack queen5 = new ItemStack(Myrmecology.itemAnt, ItemAnt.PLAINS_METADATA + ItemAnt.QUEEN_METADATA, 1);
						entity.dropPlayerItem(drone5);
						entity.dropPlayerItem(queen5);
						break;
						
					case JUNGLE_METADATA:
						ItemStack drone6 = new ItemStack(Myrmecology.itemAnt, ItemAnt.JUNGLE_METADATA + ItemAnt.DRONE_METADATA, 1);
						ItemStack queen6 = new ItemStack(Myrmecology.itemAnt, ItemAnt.JUNGLE_METADATA + ItemAnt.QUEEN_METADATA, 1);
						entity.dropPlayerItem(drone6);
						entity.dropPlayerItem(queen6);
						break;
						
				}
				*/
			
			}
		}
		
	}
	
}
