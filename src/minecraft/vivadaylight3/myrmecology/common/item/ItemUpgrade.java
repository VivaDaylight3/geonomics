package vivadaylight3.myrmecology.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class ItemUpgrade extends Item {

    String name;
    private IIcon[] icons = new IIcon[2];
    private String[] subNames = new String[] { "Solarium", "Formicarium" };

    private String[] desc = new String[] {
	    "Halves the time required to incubate a larva in a solarium",
	    "Halves the time required for breeding an ant queen" };

    public ItemUpgrade(String name) {
	super();
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
    public void registerIcons(IIconRegister reg) {

	for (int k = 0; k < icons.length; k++) {

	    icons[k] = reg.registerIcon(Resources.TEXTURE_PREFIX + name + k);

	}

    }

    @Override
    public IIcon getIconFromDamage(int dam) {

	return icons[dam];

    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
	    EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

	par3List.add(desc[par1ItemStack.getItemDamage()]);

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {

	for (int k = 0; k < icons.length; k++) {

	    list.add(new ItemStack(item, 1, k));

	}

    }

}
