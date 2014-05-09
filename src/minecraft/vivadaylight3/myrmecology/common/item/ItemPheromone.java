package vivadaylight3.myrmecology.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class ItemPheromone extends Item {

    String name;

    public ItemPheromone(String name) {
	super();
	setCreativeTab(Register.tabMyrmecology);
	setUnlocalizedName(name);
	setMaxStackSize(64);
	this.name = name;
    }

    @Override
    public void registerIcons(IIconRegister register) {

	this.itemIcon = register.registerIcon(Resources.TEXTURE_PREFIX + name);

    }

}
