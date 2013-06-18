package vivadaylight3.myrmecology.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.item.ItemExtractor;
import vivadaylight3.myrmecology.common.lib.Ants;
import vivadaylight3.myrmecology.common.lib.Breeding;
import vivadaylight3.myrmecology.common.lib.Breeding;
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
	
	public static final String NAME = "Ant Hill";
	
	public static final String[] hillNames = Variables.hillNames();
	
	public static final int[] hillMeta = {0, 1, 2, 3, 4, 5, 6};
		
	private static Icon[] icons;
	
	private final String[] iconNames = Variables.hillIconNames();
	
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
	
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		
		for (int k = 0; k < hillMeta.length; k++){
		
			par3List.add(new ItemStack(blockID, 1, k));
			
		}
    }

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9){
		
		Breeding breeding = new Breeding();
		
		if(breeding.getBreedingResult(0, 1) != null || breeding.getBreedingResult(1, 0) != null){
			
			ItemStack ant = breeding.getBreedingResult(0, 1);
			
			player.sendChatToPlayer("Have breeding result: "+ItemAnt.names[ant.getItemDamage()]);
			player.sendChatToPlayer("Damage is: "+ant.getItemDamage());
			
		}
				
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
						
						ItemStack drone = new ItemStack(Myrmecology.itemAnt, k + Ants.typeMeta[Ants.getDroneMeta()], 1);
						ItemStack queen = new ItemStack(Myrmecology.itemAnt, k + Ants.typeMeta[Ants.getQueenMeta()], 1);
						entity.dropPlayerItem(drone);
						entity.dropPlayerItem(queen);
						
					}
					
				}
			
			}
		}
		
	}
	
}
