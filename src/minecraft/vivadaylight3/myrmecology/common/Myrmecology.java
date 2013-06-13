package vivadaylight3.myrmecology.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import scala.collection.generic.BitOperations.Int;
import vivadaylight3.myrmecology.common.block.BlockAntFarm;
import vivadaylight3.myrmecology.common.block.BlockAntHill;
import vivadaylight3.myrmecology.common.handler.MyrmecologyGuiHandler;
import vivadaylight3.myrmecology.common.handler.MyrmecologyWorldGen;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.item.ItemExtractor;
import vivadaylight3.myrmecology.common.itemblock.ItemBlockAntHill;
import vivadaylight3.myrmecology.common.lib.Variables;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Myrmecology main class
 * @author VivaDaylight3
 */

@Mod(modid=Myrmecology.MOD_ID, name=Myrmecology.MOD_NAME, version=Myrmecology.MOD_VERSION, dependencies = Myrmecology.MOD_DEPENDENCIES)
@NetworkMod(channels = Myrmecology.MOD_CHANNEL, clientSideRequired=true, serverSideRequired=false, packetHandler = MyrmecologyPacketHandler.class)
public class Myrmecology
{
	
	public static final int ID_BLOCK = 1551;
	public static final int ID_ITEM = 3853;
	
	public static final String MOD_CHANNEL = "Myrmecology";
	public static final String MOD_ID = "Myrmecology";
	public static final String MOD_ID_LOWER = "myrmecology";
	public static final String MOD_NAME = "Myrmecology";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_DEPENDENCIES = "";
	
	public final static String[] antNames = {"Black Ant", "Hillside Ant", "Desert Ant", "Argentine Ant", "Field Ant", 
		"Red Ant", "Hibernus Ant", "Amber Ant"};
	
	public final static String[] typeNames = {"Queen", "Drone", "Worker", "Larva"};
		
	public final static String[] biomeSubNames = {"forest", "hills", "desert", "swamp", "plains", "jungle", "snow", 
		"rock"};
	
	public final static String[] biomeNames = {"Forest", "Hillside", "Desert", "Swamp", "Flatland", "Jungle", "Snowy", 
		"Amber"};
	
	public final static int[] typeMeta = {0, 1, 2, 3};
	
	public final static int[] antMeta = Variables.antMeta();
	
	public static Block blockAntFarm;
	public static int blockAntFarmID;
	public static final String BLOCK_ANTFARM_NAME = "antFarm";
	public static final String BLOCK_ANTFARM_NAME_HUMAN = "Ant Farm";
	
	public static Block blockAntHill;
	public static int blockAntHillID;
	public static final String BLOCK_ANTHILL_NAME = "antHill";
	public static final String BLOCK_ANTHILL_NAME_HUMAN = "Ant Hill";
	
	public static Item itemExtractor;
	public static int itemExtractorID;
	public static final String ITEM_EXTRACTOR_NAME = "extractor";
	public static final String ITEM_EXTRACTOR_NAME_HUMAN = "Ant Extractor";
	
	public static Item itemAnt;
	public static int itemAntID;
	public static final String ITEM_ANT_NAME = "ant";
		
	@SidedProxy(clientSide = "vivadaylight3.myrmecology.client.ClientProxy", serverSide = "vivadaylight3.myrmecology.common.CommonProxy")		
	public static CommonProxy proxy;
	
	@Metadata
	public static ModMetadata meta;
	
	public static final String RESOURCE_PATH = "/mods/" + Myrmecology.MOD_ID_LOWER + "/";
	public static final String TEXTURE_PATH = RESOURCE_PATH + "textures/";
	public static final String BLOCK_PATH = TEXTURE_PATH + "blocks/";
	public static final String ITEM_PATH = TEXTURE_PATH + "items/";
	public static final String GUI_PATH = TEXTURE_PATH + "gui/";
	public static final String LANG_PATH = RESOURCE_PATH + "langauges/";
	public static final String ANT_PATH = "ants/";
	public static final String HILL_PATH = "hills/";
	public static final String TEXTURE_PREFIX = Myrmecology.MOD_ID_LOWER + ":";
	
	private static Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), MOD_ID + ".cfg"));
	
	@Instance(MOD_NAME)
	public static Myrmecology instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
	
		// Config
		config.load();
		
		blockAntFarmID = config.get(Configuration.CATEGORY_BLOCK, BLOCK_ANTFARM_NAME, ID_BLOCK).getInt();
		blockAntHillID = config.get(Configuration.CATEGORY_BLOCK, BLOCK_ANTHILL_NAME, ID_BLOCK + 1).getInt();
		
		itemExtractorID = config.get(Configuration.CATEGORY_ITEM, ITEM_EXTRACTOR_NAME, ID_ITEM).getInt();
		itemAntID = config.get(Configuration.CATEGORY_ITEM, ITEM_ANT_NAME, ID_ITEM+2).getInt();
		
		config.save();
		
		blockAntFarm = new BlockAntFarm(blockAntFarmID, BLOCK_ANTFARM_NAME);
		blockAntHill = new BlockAntHill(blockAntHillID);
		
		itemExtractor = new ItemExtractor(itemExtractorID, ITEM_EXTRACTOR_NAME);
		itemAnt = new ItemAnt(itemAntID);
				
	}
	
	public static CreativeTabs tabMyrmecology = new CreativeTabs("tab" + MOD_ID){
        
        @Override
		public ItemStack getIconItemStack() {
        	
                return new ItemStack(Item.paper, 1, 0);
                
        }
	};
	
	@Init
	public void mainInit(FMLInitializationEvent event){
		
		meta.modId = Myrmecology.MOD_ID;
		meta.name = Myrmecology.MOD_NAME;
		meta.description = "Allows you to breed and cultivate ants.";
		meta.url = "https://github.com/VivaDaylight3/myrmecology";

		meta.logoFile = Myrmecology.TEXTURE_PATH + "Myrmecology_Banner.png";
		meta.version = Myrmecology.MOD_VERSION;
		meta.authorList = Arrays.asList(new String[] {"VivaDaylight3"});
		meta.credits = "The Minecraft Forge team and community for their fantastic work.";
		meta.autogenerated = false;
				
		proxy.registerRenderers();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabMyrmecology", "en_US", MOD_NAME);
				
		GameRegistry.registerBlock(blockAntHill, ItemBlockAntHill.class, "antHill");
		GameRegistry.registerItem(itemAnt, "itemAnt");
		
		LanguageRegistry.addName(blockAntFarm, BLOCK_ANTFARM_NAME_HUMAN);
		LanguageRegistry.addName(itemExtractor, ITEM_EXTRACTOR_NAME_HUMAN);
		
		for (int k = 0; k < Variables.getLastInt(Myrmecology.antMeta) + Variables.getLastInt(Myrmecology.typeMeta) + 1; k++){
										
			ItemStack ant = new ItemStack(itemAnt, 1, k);
			
			LanguageRegistry.addName(ant, ItemAnt.names[k]);
						
		}
		
		for (int k = 0; k < Variables.getLastInt(BlockAntHill.hillMeta) + 1; k++){
			
			ItemStack hill = new ItemStack(blockAntHill, 1, k);
			
			LanguageRegistry.addName(hill, BlockAntHill.hillNames[k]);
						
		}
		
		/*
		ItemBlockAntHill itemBlockAntHill = new ItemBlockAntHill(0, blockAntHill);
			
		ItemStack hill = new ItemStack(itemBlockAntHill, 1, 0);
							
		LanguageRegistry.addName(hill, BlockAntHill.hillNames[0]);
		*/
				
		GameRegistry.registerBlock(blockAntFarm, BLOCK_ANTFARM_NAME);
		GameRegistry.registerItem(itemExtractor, ITEM_EXTRACTOR_NAME);
		
		GameRegistry.registerWorldGenerator(new MyrmecologyWorldGen());
		
		MyrmecologyGuiHandler guiHandler = new MyrmecologyGuiHandler();
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
				
	}
	
}
