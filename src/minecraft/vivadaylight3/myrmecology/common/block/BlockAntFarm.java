package vivadaylight3.myrmecology.common.block;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;

public class BlockAntFarm extends BlockContainer {

    public final int BLOCK_ANTFARM_METADATA = 0;

    private String name;

    private IIcon blockIcon;
    private IIcon topIcon;

    private final Random random = new Random();

    public BlockAntFarm(String par2Name) {
	super(Material.iron);
	setBlockName(par2Name);
	setCreativeTab(Register.tabMyrmecology);
	setStepSound(Block.soundTypeMetal);
	setHardness(1.0F);
	setResistance(1.0F);
	name = par2Name;
	// this.setBlockBounds(0.35F, 0.0F, 0.1F, 0.65F, 0.9F, 0.9F);
	// func_111022_d(Reference.MOD_ID.toLowerCase() + name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer parPlayer, int par6, float par7, float par8, float par9) {
	
	if (!parPlayer.isSneaking() && !world.isRemote) {

	    parPlayer.openGui(Myrmecology.instance, Register.GUI_ID_ANTFARM,
		    world, x, y, z);

	    return true;

	}

	return false;

    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

	blockIcon = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + name);
	topIcon = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + name
		+ "_Top");

    }

    @Override
    public IIcon getIcon(int side, int metadata) {

	if (side == 1 || side == 0) {

	    return topIcon;

	}

	return blockIcon;

    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y,
	    int z) {

	float minX = 0, minY = 0, minZ = 0, maxX = 0, maxY = 0, maxZ = 0;

	switch (world.getBlockMetadata(x, y, z)) {

	case 1:
	case 3:
	    minX = 0.35F;
	    minZ = 0.1F;
	    maxX = 0.55f;
	    maxY = 0.9f;
	    maxZ = 0.9f;
	    break;

	case 0:
	case 2:
	    minX = 0.1F;
	    minZ = 0.35F;
	    maxX = 0.9f;
	    maxY = 0.9f;
	    maxZ = 0.55f;
	    break;

	}

	this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);

    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
	    EntityLivingBase entity, ItemStack par6ItemStack) {

	int metadata = world.getBlockMetadata(x, y, z);
	int angle = MathHelper
		.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

	world.setBlockMetadataWithNotify(x, y, z, angle, 2);
	this.updateTick(world, x, y, z, new Random());

    }

    @Override
    public boolean isOpaqueCube() {

	return false;

    }

    @Override
    public boolean renderAsNormalBlock() {

	return false;

    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world,
	    int x, int y, int z) {

	return new ItemStack(Register.blockAntFarm, 1);

    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
    	ArrayList<ItemStack> res = new ArrayList<ItemStack>();
    	res.add(new ItemStack(this.getItemDropped(0, new Random(), 0)));
    	return res;
    
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4,
	    Block par5, int par6) {
	TileEntityAntFarm tileEntity = (TileEntityAntFarm) par1World
		.getTileEntity(par2, par3, par4);

	if (tileEntity != null) {
	    for (int j1 = 0; j1 < tileEntity.getSizeInventory(); ++j1) {
		ItemStack itemstack = tileEntity.getStackInSlot(j1);

		if (itemstack != null) {
		    float f = this.random.nextFloat() * 0.8F + 0.1F;
		    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
		    EntityItem entityitem;

		    for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World
			    .spawnEntityInWorld(entityitem)) {
			int k1 = this.random.nextInt(21) + 10;

			if (k1 > itemstack.stackSize) {
			    k1 = itemstack.stackSize;
			}

			itemstack.stackSize -= k1;
			entityitem = new EntityItem(par1World, par2 + f, par3
				+ f1, par4 + f2,
				itemstack);
			float f3 = 0.05F;
			entityitem.motionX = (float) this.random.nextGaussian()
				* f3;
			entityitem.motionY = (float) this.random.nextGaussian()
				* f3 + 0.2F;
			entityitem.motionZ = (float) this.random.nextGaussian()
				* f3;

			if (itemstack.hasTagCompound()) {
			    entityitem.getEntityItem().setTagCompound(
				    (NBTTagCompound) itemstack.getTagCompound()
					    .copy());
			}
		    }
		}
	    }

	    //TODO this is an old function name: par1World.func_96440_m(par2, par3, par4, par5);
	}

	super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityAntFarm();
	}

}
