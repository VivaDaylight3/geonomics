package vivadaylight3.myrmecology.common.block;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class BlockAntFarm extends BlockContainer {

    public final int BLOCK_ANTFARM_METADATA = 0;

    private String name;

    private Icon blockIcon;
    private Icon topIcon;

    private final Random random = new Random();

    public BlockAntFarm(int par1, String par2Name) {
	super(par1, Material.iron);
	setUnlocalizedName(par2Name);
	setCreativeTab(Register.tabMyrmecology);
	setStepSound(Block.soundMetalFootstep);
	setHardness(1.0F);
	setResistance(1.0F);
	name = par2Name;
	this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.9F, 0.9F);
	func_111022_d(Reference.MOD_ID.toLowerCase() + name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer parPlayer, int par6, float par7, float par8, float par9) {

	if (world.isRemote) {

	    Random random = new Random();
	    int randomInt1 = random.nextInt();
	    int randomInt2 = random.nextInt();

	    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	    DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
		outputStream.writeInt(randomInt1);
		outputStream.writeInt(randomInt2);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }

	    Packet250CustomPayload packet = new Packet250CustomPayload();
	    packet.channel = "GenericRandom";
	    packet.data = bos.toByteArray();
	    packet.length = bos.size();

	    Side side = FMLCommonHandler.instance().getEffectiveSide();
	    if (side == Side.SERVER) {
		// We are on the server side.
		EntityPlayerMP player = (EntityPlayerMP) parPlayer;
	    } else if (side == Side.CLIENT) {

		EntityClientPlayerMP player = (EntityClientPlayerMP) parPlayer;
		player.sendQueue.addToSendQueue(packet);
	    } else {

	    }

	}

	// TODO

	if (!parPlayer.isSneaking() && !world.isRemote) {

	    parPlayer.openGui(Myrmecology.instance, Register.GUI_ID_ANTFARM,
		    world, x, y, z);

	    return true;

	}

	return false;

    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

	blockIcon = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + name);
	topIcon = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + name
		+ "_Top");

    }

    @Override
    public Icon getIcon(int side, int metadata) {

	if (side == 1 || side == 0) {

	    return topIcon;

	}

	return blockIcon;

    }

    @Override
    public boolean isOpaqueCube() {

	return false;

    }

    @Override
    public boolean renderAsNormalBlock() {

	return true;

    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world,
	    int x, int y, int z) {

	return new ItemStack(Register.blockAntFarm, 1);

    }

    @Override
    public int idDropped(int par1, Random random, int zero) {

	return Register.blockAntFarm.blockID;

    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
	return new TileEntityAntFarm();
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4,
	    int par5, int par6) {
	TileEntityAntFarm tileEntity = (TileEntityAntFarm) par1World
		.getBlockTileEntity(par2, par3, par4);

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
				new ItemStack(itemstack.itemID, k1,
					itemstack.getItemDamage()));
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

	    par1World.func_96440_m(par2, par3, par4, par5);
	}

	super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

}
