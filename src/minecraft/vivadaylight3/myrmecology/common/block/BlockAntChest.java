package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

public class BlockAntChest extends BlockChest {

    public BlockAntChest(int par1) {
	super(par1, 2);
	this.setCreativeTab(Register.tabMyrmecology);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
	this.blockIcon = par1IconRegister.registerIcon("planks_oak");
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

}
