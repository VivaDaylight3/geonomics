package vivadaylight3.myrmecology.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;

public class BlockIncubator extends BlockContainer {

    private String name;

    private Icon iconTop;
    private Icon iconSideOn;
    private Icon iconSideOff;

    public static final int POWERED_META = 1;
    public static final int UNPOWERED_META = 0;

    public float blockLightValue = 0.5f;

    public BlockIncubator(int par1, String par2Name) {
	super(par1, Material.wood);
	setStepSound(Block.soundWoodFootstep);
	setUnlocalizedName(par2Name);
	setCreativeTab(Register.tabMyrmecology);
	setHardness(1.0F);
	setResistance(1.0F);
	name = par2Name;
	// func_111022_d(Reference.MOD_ID.toLowerCase() + name);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

	iconTop = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_Top");
	iconSideOn = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_Side_On");
	iconSideOff = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_Side_Off");
    }

    @Override
    public Icon getIcon(int side, int metadata) {
	if (side == 0 || side == 1) {
	    return iconTop;
	} else {
	    if (metadata == POWERED_META) {
		return iconSideOn;
	    } else {
		return iconSideOff;
	    }
	}
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random par5Random) {

	if (!world.isRemote && Environment.blockIsPowered(world, x, y, z)) {

	    world.setBlockMetadataWithNotify(x, y, z, POWERED_META, 2);

	} else {

	    world.setBlockMetadataWithNotify(x, y, z, UNPOWERED_META, 2);

	}

    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
	if (!par1World.isRemote) {
	    if (Environment.blockIsPowered(par1World, par2, par3, par4)) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4,
			POWERED_META, 2);
	    } else if (!Environment.blockIsPowered(par1World, par2, par3, par4)) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4,
			UNPOWERED_META, 2);
	    }
	}
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
	    int par4, int par5) {
	if (!par1World.isRemote) {
	    if (Environment.blockIsPowered(par1World, par2, par3, par4)) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4,
			POWERED_META, 2);
	    } else if (!Environment.blockIsPowered(par1World, par2, par3, par4)) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4,
			UNPOWERED_META, 2);
	    }
	}
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z,
	    int side) {
	return true;
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
	return Register.blockIncubator.blockID;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer player, int par6, float par7, float par8, float par9) {

	if (!world.isRemote && !player.isSneaking()) {

	    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

	    // if(tileEntity != null){

	    // if(tileEntity instanceof TileEntityIncubator){

	    player.openGui(Myrmecology.instance, Register.GUI_ID_INCUBATOR,
		    world, x, y, z);

	    return true;

	    // }

	    // }

	}

	return false;

    }

    @Override
    public TileEntity createNewTileEntity(World world) {
	return new TileEntityIncubator();
    }
}
