package vivadaylight3.myrmecology.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.item.ItemExtractor;
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
	
	public final static int FOREST_METADATA = 0;
	public final static int HILLS_METADATA = 1;
	public final static int DESERT_METADATA = 2;
	public final static int SWAMP_METADATA = 3;
	public final static int PLAINS_METADATA = 4;
	public final static int JUNGLE_METADATA = 5;
	
	private String name;
	
	private Icon baseIcon;
	private Icon forestIcon;
	private Icon hillsIcon;
	private Icon desertIcon;
	private Icon swampIcon;
	private Icon plainsIcon;
	private Icon jungleIcon;

	public BlockAntHill(int par1, String par2Name)
	{
		super(par1, Material.ground);
		setUnlocalizedName(par2Name);
		setCreativeTab(Myrmecology.tabMyrmecology);
		setStepSound(Block.soundGrassFootstep);
		setHardness(1.0F);
		setResistance(1.0F);
		name = par2Name;
	}
	
	public void registerIcons(IconRegister iconRegister){
		
		baseIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name);
		forestIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Forest");
		hillsIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Hills");
		desertIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Desert");
		swampIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Swamp");
		plainsIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Plains");
		jungleIcon = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Jungle");
		
	}
	
	@Override
	public Icon getIcon(int side, int metadata){
		
		switch(metadata){
				
			case FOREST_METADATA:
				return forestIcon;
				
			case HILLS_METADATA:
				return hillsIcon;
				
			case DESERT_METADATA:
				return desertIcon;
			
			case SWAMP_METADATA:
				return swampIcon;
				
			case PLAINS_METADATA:
				return plainsIcon;
			
			default:
				return jungleIcon;
		}
		
	}
	
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
	
	public int quantityDropped(Random par1Random)
    {
        return 2;
    }

	public void harvestBlock(World world, EntityPlayer entity, int x, int y, int z){
		
		if(!world.isRemote){
		
			ItemStack tool = entity.getCurrentEquippedItem();
			ItemStack extractor = new ItemStack(Myrmecology.itemExtractor);
		
			if(tool == extractor){
			
				int meta = world.getBlockMetadata(x, y, z);
			
				switch(meta){
				
					case FOREST_METADATA:
						entity.dropItem(Myrmecology.itemAnt.itemID, 1);
						entity.dropItem(Myrmecology.itemAnt.itemID, 1);
						break;
				}
			
			}
		}
		
	}
	
}
