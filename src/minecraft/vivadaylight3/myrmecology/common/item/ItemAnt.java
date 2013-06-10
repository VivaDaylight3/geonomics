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

public class ItemAnt extends Item
{
	
	private final static String NAME = Myrmecology.ITEM_ANT_NAME;
	
	public final static String[] antNames = {"Carpenter Ant", "Hillside Ant", "Desert Ant", "Argentine Ant", "Red Ant", 
		"Fire Ant", "Hibernus Ant"};
	
	public final static String[] typeNames = {"Queen", "Drone", "Worker", "Soldier", "Larva"};
	
	public final static int[] antMeta = {0, 5, 10, 15, 20, 25, 30};
	
	public final static int[] typeMeta = {0, 1, 2, 3, 4};
	
	public static final String ANT_PATH = Myrmecology.TEXTURE_PREFIX+"ants/";
	
	private final static String[] iconNames = {ANT_PATH+NAME+"_forestQueen", ANT_PATH+NAME+"_forestDrone", ANT_PATH+NAME+"_forestWorker", 
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
		ANT_PATH+NAME+"_snowWorker", ANT_PATH+NAME+"_snowSoldier", ANT_PATH+NAME+"_snowLarva"};
	
	private static Icon[] icons;
	
	public static final String[] names = Variables.antNamesAndTypesToArray(antNames, typeNames);
	
		/*{FOREST_ANT+" "+QUEEN_ANT, FOREST_ANT+" "+DRONE_ANT, FOREST_ANT+" "+WORKER_ANT, 
		FOREST_ANT+" "+SOLDIER_ANT, FOREST_ANT+" "+LARVA_ANT, HILLS_ANT+" "+QUEEN_ANT, HILLS_ANT+" "+DRONE_ANT, 
		HILLS_ANT+" "+WORKER_ANT, HILLS_ANT+" "+SOLDIER_ANT, HILLS_ANT+" "+LARVA_ANT, DESERT_ANT+" "+QUEEN_ANT, 
		DESERT_ANT+" "+DRONE_ANT, DESERT_ANT+" "+WORKER_ANT, DESERT_ANT+" "+SOLDIER_ANT, DESERT_ANT+" "+LARVA_ANT, 
		SWAMP_ANT+" "+QUEEN_ANT, SWAMP_ANT+" "+DRONE_ANT, SWAMP_ANT+" "+WORKER_ANT, SWAMP_ANT+" "+SOLDIER_ANT, 
		SWAMP_ANT+" "+LARVA_ANT, PLAINS_ANT+" "+QUEEN_ANT, PLAINS_ANT+" "+DRONE_ANT, PLAINS_ANT+" "+WORKER_ANT, 
		PLAINS_ANT+" "+SOLDIER_ANT, PLAINS_ANT+" "+LARVA_ANT, JUNGLE_ANT+" "+QUEEN_ANT, JUNGLE_ANT+" "+DRONE_ANT, 
		JUNGLE_ANT+" "+WORKER_ANT, JUNGLE_ANT+" "+SOLDIER_ANT, JUNGLE_ANT+" "+LARVA_ANT, SNOW_ANT+" "+QUEEN_ANT, 
		SNOW_ANT+" "+DRONE_ANT, SNOW_ANT+" "+WORKER_ANT, SNOW_ANT+" "+SOLDIER_ANT, SNOW_ANT+" "+LARVA_ANT};*/
	
	public ItemAnt(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(antMeta[6] + typeMeta[4]);
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
		
            for(int i = 0; i < iconNames.length + 1; ++i){
            	
                    list.add(new ItemStack(itemID, 1, i));
                    
             }
            
     }
	
	@Override
	public Icon getIconFromDamage(int meta){
							
			return icons[meta];
		
	}

}
