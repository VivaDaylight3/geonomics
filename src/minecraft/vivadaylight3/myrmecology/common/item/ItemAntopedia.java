package vivadaylight3.myrmecology.common.item;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAntopedia extends Item {

    public ItemAntopedia(int par1) {
	super(par1);
	setCreativeTab(CreativeTabs.tabRedstone);
	setMaxStackSize(1);
	setUnlocalizedName(Reference.ITEM_ANTOPEDIA_NAME);
	func_111206_d(Resources.TEXTURE_PREFIX + Reference.ITEM_ANTOPEDIA_NAME);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player,
	    World world, int x, int y, int z, int par7, float par8, float par9,
	    float par10) {

	if (!player.isSneaking() && world.isRemote) {

	    player.openGui(Myrmecology.instance, Register.GUI_ID_ANTOPEDIA,
		    world, x, y, z);

	    return true;

	}

	return false;

    }

}
