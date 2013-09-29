package vivadaylight3.myrmecology.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class BlockFungi extends BlockMushroom {
    
    private String name;

    public BlockFungi(int par1, String par2Name) {
	super(par1);
	setUnlocalizedName(par2Name);
	setCreativeTab(Register.tabMyrmecology);
	setStepSound(Block.soundGrassFootstep);
	this.name = par2Name;
	setHardness(0.2F);
	setResistance(1.0F);
	float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setTickRandomly(true);
    }
    
    @Override
    public void registerIcons(IconRegister register){
	
	this.blockIcon = register.registerIcon(Resources.TEXTURE_PREFIX + name);
	
    }
    
    @Override
    public boolean fertilizeMushroom(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        return false;
    }
    /*
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world,
	    int x, int y, int z) {

	return new ItemStack(this.blockID, 1, 0);

    }
     */

}
