package vivadaylight3.myrmecology.common.item;

import vivadaylight3.myrmecology.common.Myrmecology;
import net.minecraft.item.Item;

public class ItemExtractor extends Item
{
	
	private String name;

	public ItemExtractor(int par1, String par2Name)
	{
		super(par1);
		setMaxStackSize(64);
		setCreativeTab(Myrmecology.tabMyrmecology);
		setUnlocalizedName(par2Name);
		name = par2Name;
	}

}
