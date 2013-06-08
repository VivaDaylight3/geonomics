package vivadaylight3.myrmecology.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vivadaylight3.myrmecology.common.Myrmecology;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockAntHill extends Block
{
	
	public final static int BLOCK_ANTHILL_METADATA = 0;
	public final static int FOREST_METADATA = 1;
	public final static int HILLS_METADATA = 2;
	public final static int DESERT_METADATA = 3;
	public final static int SWAMP_METADATA = 4;
	public final static int PLAINS_METADATA = 5;
	public final static int JUNGLE_METADATA = 6;
	
	public final static String FOREST_ANT = "Carpenter Ant";
	public final static String HILLS_ANT = "Hillside Ant";
	public final static String DESERT_ANT = "Desert Ant";
	public final static String SWAMP_ANT = "Argentine Ant";
	public final static String PLAINS_ANT = "Red Ant";
	public final static String JUNGLE_ANT = "Fire Ant";
	
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
			
			case BLOCK_ANTHILL_METADATA:
				return baseIcon;
				
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

	public int idDropped(int par1, Random par2Random, int par3){
		
		return Myrmecology.itemLarva.itemID;
		
	}
	
}
