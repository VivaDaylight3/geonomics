package vivadaylight3.myrmecology.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.lib.Variables;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAnt extends Item{
	
	private final static String NAME = Myrmecology.ITEM_ANT_NAME;
		
	private final static String[] iconNames = Variables.antIconNames();
		
		/*{ANT_PATH+NAME+"_forestQueen", ANT_PATH+NAME+"_forestDrone", ANT_PATH+NAME+"_forestWorker", 
		ANT_PATH+NAME+"_forestSoldier", ANT_PATH+NAME+"_forestLarva",
		ANT_PATH+NAME+"_hillsQueen", ANT_PATH+NAME+"_hillsDrone", ANT_PATH+NAME+"_hillsWorker", 
		ANT_PATH+NAME+"_hillsSoldier", ANT_PATH+NAME+"_hillsLarva", ANT_PATH+NAME+"_desertQueen", 
		ANT_PATH+NAME+"_desertDrone", ANT_PATH+NAME+"_desertWorker", ANT_PATH+NAME+"_desertSoldier", 
		ANT_PATH+NAME+"_desertLarva", ANT_PATH+NAME+"_swampQueen", ANT_PATH+NAME+"_swampDrone", 
		ANT_PATH+NAME+"_swampWorker", ANT_PATH+NAME+"_swampSoldier", ANT_PATH+NAME+"_swampLarva", 
		ANT_PATH+NAME+"_plainsQueen", ANT_PATH+NAME+"_plainsDrone", ANT_PATH+NAME+"_plainsWorker", 
		ANT_PATH+NAME+"_plainsSoldier", ANT_PATH+NAME+"_plainsLarva", ANT_PATH+NAME+"_jungleQueen", 
		ANT_PATH+NAME+"_jungleDrone", ANT_PATH+NAME+"_jungleWorker", ANT_PATH+NAME+"_jungleSoldier", 
		ANT_PATH+NAME+"_jungleLarva", ANT_PATH+NAME+"_snowQueen", ANT_PATH+NAME+"_snowDrone", 
		ANT_PATH+NAME+"_snowWorker", ANT_PATH+NAME+"_snowSoldier", ANT_PATH+NAME+"_snowLarva"};*/
	
	private static Icon[] icons;
	
	public static final String[] names = Variables.antNames();
	
	public ItemAnt(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setCreativeTab(Myrmecology.tabMyrmecology);
		setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		
		ItemAnt.icons = Variables.iconsToArray(iconRegister, iconNames);
		
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
		
    	return this.getUnlocalizedName() + names[itemStack.getItemDamage()];
    
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list){
		
            for(int i = 0; i < iconNames.length; ++i){
            	
                    list.add(new ItemStack(itemID, 1, i));
                    
             }
            
     }
	
	@Override
	public Icon getIconFromDamage(int meta){
							
			return icons[meta];
		
	}

}
