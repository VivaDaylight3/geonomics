package vivadaylight3.myrmecology.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.common.Myrmecology;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemAnt extends Item
{
	
	private final static String NAME = Myrmecology.ITEM_ANT_NAME;
		
	private Icon forestQueen;
	private Icon forestDrone;
	private Icon forestWorker;
	private Icon forestSoldier;
	private Icon forestLarva;
	
	private Icon hillsQueen;
	private Icon hillsDrone;
	private Icon hillsWorker;
	private Icon hillsSoldier;
	private Icon hillsLarva;
	
	private Icon desertQueen;
	private Icon desertDrone;
	private Icon desertWorker;
	private Icon desertSoldier;
	private Icon desertLarva;
	
	private Icon swampQueen;
	private Icon swampDrone;
	private Icon swampWorker;
	private Icon swampSoldier;
	private Icon swampLarva;
	
	private Icon plainsQueen;
	private Icon plainsDrone;
	private Icon plainsWorker;
	private Icon plainsSoldier;
	private Icon plainsLarva;
	
	private Icon jungleQueen;
	private Icon jungleDrone;
	private Icon jungleWorker;
	private Icon jungleSoldier;
	private Icon jungleLarva;
	
	public final static String FOREST_ANT = "Carpenter Ant";
	public final static String HILLS_ANT = "Hillside Ant";
	public final static String DESERT_ANT = "Desert Ant";
	public final static String SWAMP_ANT = "Argentine Ant";
	public final static String PLAINS_ANT = "Red Ant";
	public final static String JUNGLE_ANT = "Fire Ant";
	
	public final static String QUEEN_ANT = "Queen";
	public final static String DRONE_ANT = "Drone";
	public final static String WORKER_ANT = "Worker";
	public final static String SOLDIER_ANT = "Soldier";
	public final static String LARVA_ANT = "Larva";
	
	public final static int FOREST_METADATA = 0;
	public final static int HILLS_METADATA = 5;
	public final static int DESERT_METADATA = 10;
	public final static int SWAMP_METADATA = 15;
	public final static int PLAINS_METADATA = 20;
	public final static int JUNGLE_METADATA = 25;
	
	public final static int QUEEN_METADATA = 0;
	public final static int DRONE_METADATA = 1;
	public final static int WORKER_METADATA = 2;
	public final static int SOLDIER_METADATA = 3;
	public final static int LARVA_METADATA = 4;
	
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
		ANT_PATH+NAME+"_jungleLarva"};

	private Icon[] icons;
	
	public ItemAnt(int par1, String name, Item item)
	{
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);
		LanguageRegistry.addName(item, name);
		GameRegistry.registerItem(item, name);
		setCreativeTab(Myrmecology.tabMyrmecology);
		setMaxStackSize(1);
	}
	
	public void registerIcons(IconRegister iconRegister){
		
		this.icons = Myrmecology.iconsToArray(iconRegister, iconNames);
		
		/*
		int length = iconNames.length + 1;
		
		for(int k = 0; k < length; k++){
			
			icons[k] = iconRegister.registerIcon(iconNames[k]);
			
		}
		*/
		
	}
	
	@Override
	public Icon getIconFromDamage(int meta){
							
			return icons[meta];
			
		/*
		int forest = FOREST_METADATA;
		int hills = HILLS_METADATA;
		int desert = DESERT_METADATA;
		int swamp = SWAMP_METADATA;
		int plains = PLAINS_METADATA;
		int jungle = JUNGLE_METADATA;
		
		int queen = QUEEN_METADATA;
		int drone = DRONE_METADATA;
		int worker = WORKER_METADATA;
		int soldier = SOLDIER_METADATA;
		int larva = LARVA_METADATA;
		
		if(meta == forest + queen){
			
			return forestQueen;
			
		}else if(meta == forest + drone){
			
			return forestDrone;
			
		}else if(meta == forest + worker){
			
			return forestWorker;
			
		}else if(meta == forest + soldier){
			
			return forestSoldier;
			
		}else if(meta == forest + larva){
			
			return forestLarva;
			
		}else if(meta == hills + queen){
			
			return hillsDrone;
			
		}else if(meta == hills + drone){
			
			return hillsDrone;
			
		}else if(meta == hills + worker){
			
			return hillsWorker;
			
		}else if(meta == hills + soldier){
			
			return hillsSoldier;
			
		}else if(meta == hills + larva){
			
			return hillsLarva;
			
		}else if(meta == desert + queen){
			
			return desertQueen;
			
		}else if(meta == desert + drone){
			
			return desertDrone;
			
		}else if(meta == desert + worker){
			
			return desertWorker;
			
		}else if(meta == desert + soldier){
			
			return desertSoldier;
			
		}else if(meta == desert + larva){
			
			return desertLarva;
			
		}else if(meta == swamp + queen){
			
			return swampQueen;
			
		}else if(meta == swamp + drone){
			
			return swampDrone;
			
		}else if(meta == swamp + worker){
			
			return swampWorker;
			
		}else if(meta == swamp + soldier){
			
			return swampSoldier;
			
		}else if(meta == swamp + larva){
			
			return swampLarva;
			
		}else if(meta == plains + queen){
			
			return plainsQueen;
			
		}else if(meta == plains + drone){
			
			return plainsDrone;
			
		}else if(meta == plains + worker){
			
			return plainsWorker;
			
		}else if(meta == plains + soldier){
			
			return plainsSoldier;
			
		}else if(meta == plains + larva){
			
			return plainsLarva;
			
		}else if(meta == jungle + queen){
			
			return jungleQueen;
			
		}else if(meta == jungle + drone){
			
			return jungleDrone;
			
		}else if(meta == jungle + worker){
			
			return jungleWorker;
			
		}else if(meta == jungle + soldier){
			
			return jungleSoldier;
			
		}else{
			
			return jungleLarva;
			
		}*/
		
		
	}

}
