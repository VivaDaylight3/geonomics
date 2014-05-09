package vivadaylight3.myrmecology.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.util.MyrmopaediaProperties;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.lib.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMyrmopaedia extends Item {

    public ItemMyrmopaedia() {
	super();
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(1);
	setUnlocalizedName(Reference.ITEM_MYRMOPAEDIA_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
	this.itemIcon = par1IconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.ITEM_MYRMOPAEDIA_NAME);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
	    EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

	par3List.add("Analyses the properties of ants");

    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World,
	    EntityPlayer par3EntityPlayer) {

	MyrmopaediaProperties.initiateMyrmopaedia(par1ItemStack);

    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target,
	    EntityLivingBase player) {

	if (target instanceof EntityAnt && player instanceof EntityPlayer) {

	    ((EntityAnt) target)
		    .sendBehaviourErrorMessage((EntityPlayer) player);

	    return true;

	}

	return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
	    EntityPlayer par3EntityPlayer) {

	this.onItemUse(par1ItemStack, par3EntityPlayer, par2World,
		(int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY,
		(int) par3EntityPlayer.posZ, 0, 0f, 0f, 0f);
	return par1ItemStack;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player,
	    World world, int x, int y, int z, int par7, float par8, float par9,
	    float par10) {

	player.openGui(Myrmecology.instance, Register.GUI_ID_MYRMOPAEDIA,
		world, x, y, z);
	player.addStat(Register.achieveReadBook, 1);

	return false;

    }

}
