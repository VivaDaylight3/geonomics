package vivadaylight3.myrmecology.common.item;

import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAgaricusFungi extends Item {

    public ItemAgaricusFungi(int par1) {
	super(par1);
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(64);
	setUnlocalizedName(Reference.ITEM_FUNGI_NAME);
	func_111206_d(Resources.TEXTURE_PREFIX + Reference.ITEM_FUNGI_NAME);
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
	
	if(par3World.isAirBlock(par4, par5+1, par6)){
	    
	    par3World.setBlock(par4, par5+1, par6, Register.blockFungi.blockID);
	    par1ItemStack.splitStack(1);
	    return true;
	    
	}
	
        return false;
    }

}
