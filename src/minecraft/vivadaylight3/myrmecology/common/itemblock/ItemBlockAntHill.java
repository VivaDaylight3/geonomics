package vivadaylight3.myrmecology.common.itemblock;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockAntHill extends ItemBlock {

	public static final String[] subName = Myrmecology.biomeSubNames;

	public ItemBlockAntHill(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("antHill");
		this.setCreativeTab(Myrmecology.tabMyrmecology);
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
    	return this.getUnlocalizedName() + subName[itemStack.getItemDamage()];
    }
	
}