package vivadaylight3.myrmecology.common.blocks;

import java.util.Random;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockAntFarm extends Block
{
	
	public final int BLOCK_ANTFARM_METADATA = 0;
	
	private String name;
	
	private Icon iconFront;
	private Icon iconBack;
	private Icon iconSide;
	private Icon iconOther;

	public BlockAntFarm(int par1, String par2Name)
	{
		super(par1, Material.iron);
		setUnlocalizedName(par2Name);
		setCreativeTab(Myrmecology.tabMyrmecology);
		setStepSound(Block.soundMetalFootstep);
		setHardness(1.0F);
		setResistance(1.0F);
		name = par2Name;
	}
	
	public void registerIcons(IconRegister iconRegister){
		
		iconFront = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Front");
		iconBack = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Back");
		iconSide = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Side");
		iconOther = iconRegister.registerIcon(Myrmecology.TEXTURE_PREFIX+name+"_Other");
		
	}
	
	public Icon getIcon(int side, int metadata){
		
		String which = Myrmecology.getBlockSide(side, metadata, BLOCK_ANTFARM_METADATA);
		
		if (which == "front") return iconFront;
		else if (which == "back") return iconBack;
		else if (which == "input" || which == "output") return iconSide;
		else return iconOther;
		
	}
	
	@Override
	public boolean isOpaqueCube(){

		return false;

	}

	@Override
	public boolean renderAsNormalBlock(){

		return false;

	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack){
		
		world.setBlockMetadataWithNotify(x, y, z, BLOCK_ANTFARM_METADATA + Myrmecology.getBlockOrientation(x, y, z, entityLiving), 3);
		
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		int id = idPicked(world, x, y, z);

		if (id == 0)
		{
			return null;
		}

		Item item = Item.itemsList[id];

		if (item == null)
		{
			return null;
		}

		int metadata = getDamageValue(world, x, y, z);

		return new ItemStack(id, 1, metadata);
	}

	public int idDropped(int par1, Random random, int zero){
    	
    	return Myrmecology.blockAntFarm.blockID;
    	
    }
	
	public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityAntFarm();
    }
	
	

}
