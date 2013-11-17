package vivadaylight3.myrmecology.common.item;

import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemPheromone extends Item {

    public ItemPheromone(int par1) {
	super(par1);
	setCreativeTab(Register.tabMyrmecology);
    }
    
    @Override
    public void registerIcons(IconRegister register){
	
	this.itemIcon = register.registerIcon(Resources.TEXTURE_PREFIX+"pheromone.png");
	
    }

}
