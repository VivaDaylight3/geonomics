package vivadaylight3.myrmecology.common.block;

import java.util.Random;

import vivadaylight3.myrmecology.common.Register;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockFungi extends BlockMushroom {

    public BlockFungi(int par1, String par2Name) {
	super(par1);
	setUnlocalizedName(par2Name);
	setCreativeTab((CreativeTabs)null);
	setStepSound(Block.soundMetalFootstep);
	setHardness(1.0F);
	setResistance(1.0F);
    }
    
    @Override
    public boolean fertilizeMushroom(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        return false;
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world,
	    int x, int y, int z) {

	return null;

    }

}
