package vivadaylight3.myrmecology.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.block.BlockAntHill;
import vivadaylight3.myrmecology.common.lib.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class ItemExtractor extends Item {
    
    private String name;
    private Icon icon;
    
    public ItemExtractor(int par1, String par2Name) {
	super(par1);
	setMaxStackSize(1);
	setCreativeTab(Register.tabMyrmecology);
	setUnlocalizedName(par2Name);
	name = par2Name;
	func_111206_d(Myrmecology.MOD_ID_LOWER + name);
	
    }
    
    @Override
    public void registerIcons(IconRegister register){
	
	icon = register.registerIcon(Resources.TEXTURE_PREFIX+Register.ITEM_EXTRACTOR_NAME);
	
    }
    
    @Override
    public Icon getIconFromDamage(int par1){
	
	return icon;
	
    }
    
}
