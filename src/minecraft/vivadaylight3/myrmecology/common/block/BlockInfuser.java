package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.EnumBlockSide;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityInfuser;

public class BlockInfuser extends Block {

    String name;
    Icon[] icons = new Icon[3];

    public static final int UNPOWERED_META = 0;
    public static final int POWERED_META = 4;

    public BlockInfuser(int par1, String name) {
	super(par1, Material.iron);
	setCreativeTab(Register.tabMyrmecology);
	setUnlocalizedName(name);
	this.name = name;
    }

    @Override
    public void registerIcons(IconRegister reg) {

	icons[0] = reg.registerIcon(Resources.TEXTURE_PREFIX + name + "_top");
	icons[1] = reg.registerIcon(Resources.TEXTURE_PREFIX + name + "_side");
	icons[2] = reg.registerIcon(Resources.TEXTURE_PREFIX + name + "_front");
	// icons[3] = reg.registerIcon(Resources.TEXTURE_PREFIX+name+"_bottom");

    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {

	return new TileEntityInfuser();

    }

    @Override
    public Icon getIcon(int side, int metadata) {

	int base = 0;

	if (metadata >= POWERED_META) {

	    base = POWERED_META;

	}

	if (side == 0 || side == 1) {

	    return icons[0];

	} else if (Environment.getBlockSide(side, metadata, base) == EnumBlockSide.FRONT) {

	    return icons[2];

	} else {

	    return icons[1];

	}

    }

    public static boolean isPowered(int meta) {

	if (meta >= POWERED_META) {

	    return true;

	}

	return false;

    }

    private void updateMeta(World world, int x, int y, int z) {

	int meta = 0;

	if (!world.isRemote && Environment.blockIsPowered(world, x, y, z)) {

	    if (world.getBlockMetadata(x, y, z) < POWERED_META) {

		meta += world.getBlockMetadata(x, y, z) + POWERED_META;

	    } else {

		meta = world.getBlockMetadata(x, y, z);

	    }

	    world.setBlockMetadataWithNotify(x, y, z, meta, 2);

	} else {

	    if (world.getBlockMetadata(x, y, z) < POWERED_META) {

		meta += world.getBlockMetadata(x, y, z);

	    } else {

		meta = world.getBlockMetadata(x, y, z) - POWERED_META;

	    }

	}

	world.setBlockMetadataWithNotify(x, y, z, meta, 2);

    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
	    EntityLivingBase entity, ItemStack par6ItemStack) {

	int metadata = world.getBlockMetadata(x, y, z);
	int angle = MathHelper
		.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

	world.setBlockMetadataWithNotify(x, y, z, angle, 2);

    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
	if (!par1World.isRemote) {
	    this.updateMeta(par1World, par2, par3, par4);
	}
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
	    int par4, int par5) {
	if (!par1World.isRemote) {
	    this.updateMeta(par1World, par2, par3, par4);

	}
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z,
	    int side) {
	return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer player, int par6, float par7, float par8, float par9) {

	if (!world.isRemote && !player.isSneaking()) {

	    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

	    player.openGui(Myrmecology.instance, Register.GUI_ID_INFUSER,
		    world, x, y, z);

	    return true;

	}

	return false;

    }

}
