package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class BlockFungi extends BlockMushroom {

    private String name;

    public BlockFungi(String par2Name) {
	super();
	setBlockName(par2Name);
	setCreativeTab(Register.tabMyrmecology);
	setStepSound(Block.soundTypeGrass);
	this.name = par2Name;
	setHardness(0.2F);
	setResistance(1.0F);
	float f = 0.2F;
	this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F,
		0.5F + f);
	this.setTickRandomly(true);
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {

	this.blockIcon = register.registerIcon(Resources.TEXTURE_PREFIX + name);

    }
    /*
     * @Override public ItemStack getPickBlock(MovingObjectPosition target,
     * World world, int x, int y, int z) {
     * 
     * return new ItemStack(this.blockID, 1, 0);
     * 
     * }
     */

}
