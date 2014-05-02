package vivadaylight3.myrmecology.common.block;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.EnumBlockSide;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;

public class BlockIncubator extends BlockContainer {

    private String name;

    private IIcon iconTopOn;
    private IIcon iconTopOff;
    private IIcon iconFrontOn;
    private IIcon iconFrontOff;
    private IIcon iconSide;

    public static final int POWERED_META = 4;
    public static final int UNPOWERED_META = 0;

    public float blockLightValue = 0.5f;

    public BlockIncubator(String par2Name) {
	super(Material.wood);
	setStepSound(Block.soundTypeWood);
	setBlockName(par2Name);
	setCreativeTab(Register.tabMyrmecology);
	setHardness(1.0F);
	setResistance(1.0F);
	name = par2Name;
	// func_111022_d(Reference.MOD_ID.toLowerCase() + name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

	iconTopOn = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_top_on");
	iconTopOff = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_top_off");
	iconFrontOn = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_front_on");
	iconFrontOff = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_front_off");
	iconSide = iconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_INCUBATOR_NAME + "_side");
    }

    @Override
    public IIcon getIcon(int side, int metadata) {

	int base = 0;

	if (metadata >= POWERED_META) {

	    base = POWERED_META;

	}

	if (side == 0 || side == 1) {

	    if (metadata >= POWERED_META) {

		return iconTopOn;

	    } else {

		return iconTopOff;

	    }

	} else if (Environment.getBlockSide(side, metadata, base) == EnumBlockSide.FRONT) {

	    if (metadata >= POWERED_META) {

		return iconFrontOn;

	    } else {

		return iconFrontOff;

	    }

	} else {

	    return iconSide;

	}
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

	return this.isPowered(world.getBlockMetadata(x, y, z)) ? 10 : 0;

    }

    @Override
    public boolean isOpaqueCube() {

	return false;

    }

    @Override
    public boolean renderAsNormalBlock() {

	return true;

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
    public void updateTick(World world, int x, int y, int z, Random par5Random) {

	this.updateMeta(world, x, y, z);

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
	    int par4, Block par5) {
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
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
    	ArrayList<ItemStack> res = new ArrayList<ItemStack>();
    	res.add(new ItemStack(this.getItemDropped(0, new Random(), 0)));
    	return res;
    
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer player, int par6, float par7, float par8, float par9) {

	if (!world.isRemote && !player.isSneaking()) {

	    TileEntity tileEntity = world.getTileEntity(x, y, z);

	    player.openGui(Myrmecology.instance, Register.GUI_ID_INCUBATOR,
		    world, x, y, z);

	    return true;

	}

	return false;

    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityIncubator();
	}
}
