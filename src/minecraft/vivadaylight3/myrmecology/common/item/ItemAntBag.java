package vivadaylight3.myrmecology.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class ItemAntBag extends Item {

    String name;

    public ItemAntBag(int par1, String name) {
	super(par1);
	setUnlocalizedName(name);
	setCreativeTab(Register.tabMyrmecology);
	this.name = name;
    }

    @Override
    public void registerIcons(IconRegister reg) {

	this.itemIcon = reg.registerIcon(Resources.TEXTURE_PREFIX + name);

    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
	    EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

	par3List.add("Compacts ants into one small space for carrying");

    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
	    EntityPlayer par3EntityPlayer) {

	/*
	 * player.openGui(Myrmecology.instance, Register.GUI_ID_MYRMOPAEDIA,
	 * world, x, y, z);
	 */

	return par1ItemStack;
    }

}
