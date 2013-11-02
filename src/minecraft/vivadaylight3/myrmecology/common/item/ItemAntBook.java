package vivadaylight3.myrmecology.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemAntBook extends Item {

    public ItemAntBook(int par1) {
	super(par1);
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(1);
	setUnlocalizedName(Reference.ITEM_ANTBOOK_NAME);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
	this.itemIcon = par1IconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.ITEM_ANTBOOK_NAME);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
	par3List.add("This book holds the secrets to "+Reference.MOD_ID);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
	par3EntityPlayer.openGui(Myrmecology.instance, Register.GUI_ID_ANTBOOK, par2World, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY,(int) par3EntityPlayer.posZ);
        return par1ItemStack;
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player,
	    World world, int x, int y, int z, int par7, float par8, float par9,
	    float par10) {

	player.openGui(Myrmecology.instance, Register.GUI_ID_ANTBOOK,
		world, x, y, z);

	return false;

    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

}
