package vivadaylight3.myrmecology.common.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

public class BlockAntChest extends BlockChest {

    public static final String tileName = Reference.MOD_ID.toLowerCase()
	    + ".antChestDouble";

    public BlockAntChest(int par1) {
	super(par1, 2);
	this.setCreativeTab(Register.tabMyrmecology);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
	this.blockIcon = par1IconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_ANTCHEST_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
	TileEntityAntChest tileentitychest = new TileEntityAntChest();
	return tileentitychest;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3,
	    int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
	    float par8, float par9) {
	if (par1World.isRemote) {
	    return true;
	} else {
	    IInventory iinventory = this.getInventory(par1World, par2, par3,
		    par4);

	    if (iinventory != null) {
		par5EntityPlayer.displayGUIChest(iinventory);
	    }

	    return true;
	}
    }

    @Override
    public IInventory getInventory(World par1World, int par2, int par3, int par4) {
	Object object = (TileEntityChest) par1World.getBlockTileEntity(par2,
		par3, par4);

	if (object == null) {
	    return null;
	} else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN)) {
	    return null;
	} else if (isOcelotBlockingChest(par1World, par2, par3, par4)) {
	    return null;
	} else if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID
		&& (par1World
			.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(
			par1World, par2 - 1, par3, par4))) {
	    return null;
	} else if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID
		&& (par1World
			.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(
			par1World, par2 + 1, par3, par4))) {
	    return null;
	} else if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID
		&& (par1World
			.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlockingChest(
			par1World, par2, par3, par4 - 1))) {
	    return null;
	} else if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID
		&& (par1World
			.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlockingChest(
			par1World, par2, par3, par4 + 1))) {
	    return null;
	} else {
	    if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID) {
		object = new InventoryLargeChest(tileName,
			(TileEntityChest) par1World.getBlockTileEntity(
				par2 - 1, par3, par4), (IInventory) object);
	    }

	    if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID) {
		object = new InventoryLargeChest(tileName, (IInventory) object,
			(TileEntityChest) par1World.getBlockTileEntity(
				par2 + 1, par3, par4));
	    }

	    if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID) {
		object = new InventoryLargeChest(tileName,
			(TileEntityChest) par1World.getBlockTileEntity(par2,
				par3, par4 - 1), (IInventory) object);
	    }

	    if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID) {
		object = new InventoryLargeChest(tileName, (IInventory) object,
			(TileEntityChest) par1World.getBlockTileEntity(par2,
				par3, par4 + 1));
	    }

	    return (IInventory) object;
	}
    }

}
