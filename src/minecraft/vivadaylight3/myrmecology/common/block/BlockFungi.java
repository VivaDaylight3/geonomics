package vivadaylight3.myrmecology.common.block;

import java.util.Random;

import net.minecraft.block.BlockMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockFungi extends BlockMushroom {
    
    public BlockFungi(int par1) {
	super(par1);
    }
    
    @Override
    public boolean fertilizeMushroom(World par1World, int par2, int par3,
	    int par4, Random par5Random) {
	
	return false;
	
    }
    
}
