package vivadaylight3.myrmecology.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vivadaylight3.myrmecology.api.util.AntProperties;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemUpgrade extends Item {

    String name;
    private Icon[] icons = new Icon[2];
    private String[] subNames = new String[] { "Solarium", "Formicarium" };

    private String[] desc = new String[] {
	    "Halves the time required to incubate a larva in a solarium",
	    "Halves the time required for breeding an ant queen" };

    public ItemUpgrade(int par1, String name) {
	super(par1);
	setHasSubtypes(true);
	setUnlocalizedName(name);
	this.setMaxDamage(icons.length - 1);
	setCreativeTab(Register.tabMyrmecology);
	this.name = name;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
	return super.getUnlocalizedName() + "."
		+ subNames[par1ItemStack.getItemDamage()];
    }

    @Override
    public void registerIcons(IconRegister reg) {

	for (int k = 0; k < icons.length; k++) {

	    icons[k] = reg.registerIcon(Resources.TEXTURE_PREFIX + name + k);

	}

    }

    @Override
    public Icon getIconFromDamage(int dam) {

	return icons[dam];

    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
	    EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

	par3List.add(desc[par1ItemStack.getItemDamage()]);

    }

    @Override
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {

	for (int k = 0; k < icons.length; k++) {

	    list.add(new ItemStack(itemID, 1, k));

	}

    }

}
