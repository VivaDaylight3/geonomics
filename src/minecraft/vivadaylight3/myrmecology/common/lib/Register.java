package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.api.Breeding;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.block.BlockAntFarm;
import vivadaylight3.myrmecology.common.block.BlockFungi;
import vivadaylight3.myrmecology.common.block.BlockIncubator;
import vivadaylight3.myrmecology.common.block.anthill.AntHillForest;
import vivadaylight3.myrmecology.common.block.anthill.AntHillJungle;
import vivadaylight3.myrmecology.common.handler.MyrmecologyWorldGen;
import vivadaylight3.myrmecology.common.item.ToolExtractor;
import vivadaylight3.myrmecology.common.item.ant.AntCommon;
import vivadaylight3.myrmecology.common.item.ant.AntDesert;
import vivadaylight3.myrmecology.common.item.ant.AntForest;
import vivadaylight3.myrmecology.common.item.ant.AntSwamp;
import vivadaylight3.myrmecology.common.item.ant.AntWater;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Register {
    
    private static Configuration config;
    
    public static CreativeTabs tabMyrmecology;
        
    private static ArrayList<ItemAnt> antList = new ArrayList<ItemAnt>();
    private static ArrayList<BlockAntHill> hillList = new ArrayList<BlockAntHill>();
    
    public static final int ID_BLOCK = 200;
    public static final int ID_ITEM = 3853;
    
    public static int latestID = 0;
        
    public static final String BLOCK_ANTFARM_NAME = "antFarm";
    public static Block blockAntFarm;
    
    public static final String BLOCK_ANTHILL_NAME = "antHill";
    public static Block blockAntHill;
    
    public static final String BLOCK_INCUBATOR_NAME = "incubator";
    public static Block blockIncubator;
    
    public static final String BLOCK_FUNGI_NAME = "fungi";
    public static Block blockFungi;
    
    public static final String ITEM_EXTRACTOR_NAME = "extractor";
    public static Item itemExtractor;
                    
    public static AntHillForest hillForest;
    public static AntHillJungle hillJungle;
    
    public static AntForest antForest;
    public static AntDesert antDesert;
    public static AntCommon antCommon;
    public static AntWater antWater;
    public static AntSwamp antSwamp;
    
    public static void setConfig(Configuration parConfig) {
	
	config = parConfig;
	
    }
    
    public static int getNewID() {
	
	latestID += 1;
	
	return latestID;
	
    }
    
    public static void registerBlocks() {
	
	config.load();
	
	blockAntFarm = new BlockAntFarm(config.get(
		Configuration.CATEGORY_BLOCK, BLOCK_ANTFARM_NAME, ID_BLOCK)
		.getInt(), BLOCK_ANTFARM_NAME);
	
	blockIncubator = new BlockIncubator(config.get(
		Configuration.CATEGORY_BLOCK, BLOCK_INCUBATOR_NAME,
		ID_BLOCK + 2).getInt(), BLOCK_INCUBATOR_NAME);
	
	blockFungi = new BlockFungi(config.get(Configuration.CATEGORY_BLOCK,
		BLOCK_FUNGI_NAME, ID_BLOCK + 3).getInt());
	
	hillForest = new AntHillForest(config.get(Configuration.CATEGORY_BLOCK,
		"antHillForest", 
		ID_BLOCK + 4).getInt());
	
	hillJungle = new AntHillJungle(config.get(Configuration.CATEGORY_BLOCK,
		"antHillJungle", 
		ID_BLOCK + 5).getInt());
	
	config.save();
	
	addBlock(blockIncubator, "Ant Incubator", "antIncubator");
	addBlock(blockAntFarm, "Ant Farm", "antFarm");
	addBlock(blockFungi, "Agaricus Fungi", "antFungi");
	addBlock(hillForest, hillForest.getHillName(), hillForest.getHillSubName());
	addBlock(hillJungle, hillJungle.getHillName(), hillJungle.getHillSubName());
	//GameRegistry.registerBlock(blockIncubator, BLOCK_INCUBATOR_NAME);
	//GameRegistry.registerBlock(blockAntFarm, BLOCK_ANTFARM_NAME);
	//GameRegistry.registerBlock(blockFungi, BLOCK_FUNGI_NAME);
	//GameRegistry.registerBlock(hillForest, hillForest.getHillName());
	
	//LanguageRegistry.addName(blockAntFarm, BLOCK_ANTFARM_NAME_HUMAN);
	//LanguageRegistry.addName(blockIncubator, BLOCK_INCUBATOR_NAME_HUMAN);
	//LanguageRegistry.addName(blockFungi, BLOCK_FUNGI_NAME);
	//LanguageRegistry.addName(hillForest, hillForest.getHillName());
	
    }
    
    public static void registerItems() {
	
	config.load();
	
	itemExtractor = new ToolExtractor(config.get(
		Configuration.CATEGORY_ITEM, ITEM_EXTRACTOR_NAME, ID_ITEM)
		.getInt());
	
	antForest = new AntForest(config.get(Configuration.CATEGORY_ITEM,
		"antForest", ID_ITEM + getNewID()).getInt());
	
	antDesert = new AntDesert(config.get(Configuration.CATEGORY_ITEM,
		"antDesert", ID_ITEM + getNewID()).getInt());
	
	antCommon = new AntCommon(config.get(Configuration.CATEGORY_ITEM,
		"antCommon", ID_ITEM + getNewID()).getInt());
	
	antWater = new AntWater(config.get(Configuration.CATEGORY_ITEM,
		"antWater", ID_ITEM + getNewID()).getInt());
	
	antSwamp = new AntSwamp(config.get(Configuration.CATEGORY_ITEM,
		"antSwamp", ID_ITEM + getNewID()).getInt());
	
	config.save();
	
	addItem(itemExtractor, "Ant Extractor", "antExtractor");
	
	addItem(antForest, antForest.getNames(), Myrmecology.MOD_ID+antForest.getSpeciesSubName());
	
	addItem(antDesert, antDesert.getNames(), Myrmecology.MOD_ID+antDesert.getSpeciesSubName());
	
	addItem(antCommon, antCommon.getNames(), Myrmecology.MOD_ID+antCommon.getSpeciesSubName());
	
	addItem(antWater, antWater.getNames(), Myrmecology.MOD_ID+antWater.getSpeciesSubName());
	
	addItem(antSwamp, antSwamp.getNames(), Myrmecology.MOD_ID+antSwamp.getSpeciesSubName());
	
    }
    
    public static void registerCreativeTab() {
	
	tabMyrmecology = new CreativeTabs("tab" + Myrmecology.MOD_ID);
	
	LanguageRegistry.instance().addStringLocalization(
		"itemGroup." + "tab" + Myrmecology.MOD_ID, "en_US",
		Myrmecology.MOD_NAME);
	
    }
    
    public static void registerRecipes() {
	
	GameRegistry.addRecipe(new ItemStack(blockIncubator, 1), "wgw", "g g",
		"wrw", 'w', new ItemStack(Block.woodSingleSlab), 'g',
		new ItemStack(Block.glass), 'r', new ItemStack(
			Block.redstoneLampIdle));
	
	GameRegistry.addRecipe(new ItemStack(blockAntFarm, 1), "wgw", "gsg",
		"wgw", 'w', new ItemStack(Block.woodSingleSlab), 'g',
		new ItemStack(Block.glass), 's', new ItemStack(Block.sand));
	
    }
    
    // TODO
    public static void registerBreeding() {
	
	Breeding breeding = new Breeding();
	
	breeding.addBreeding(antForest, antForest, antDesert);
	
	System.out.println("###!BREEDING!### "
		+ Breeding.getBreedingResult(antForest, antCommon));
	
    }
    
    public static void registerWorldGen() {
	
	GameRegistry.registerWorldGenerator(new MyrmecologyWorldGen());
	
    }
    
    /**
     * Registers an item with the GameRegistry and LanguageRegistry
     * @param item
     * @param name
     * @param subName
     */
    public static void addItem(Item item, String name, String subName) {
	
	GameRegistry.registerItem(item, subName);
	LanguageRegistry.addName(item, name);
	
    }
    
    /**
     * Registers an item (with damage and multiple names) with the GameRegistry and LanguageRegistry
     * @param item
     * @param names[]
     * @param subName
     */
    public static void addItem(Item item, String[] names, String subName){
	
	GameRegistry.registerItem(item, subName);
	
	for(int k = 0; k < names.length; k++){
	    
	    LanguageRegistry.addName(new ItemStack(item, 1, k), names[k]);
	    
	}
	
    }
    
    /**
     * Registers a block with the GameRegistry and LanguageRegistry
     * @param item
     * @param name
     * @param subName
     */
    public static void addBlock(Block item, String name, String subName) {
	
	GameRegistry.registerBlock(item, subName);
	LanguageRegistry.addName(item, name);
	
    }
    
    /**
     * Registers an block (with damage and multiple names) with the GameRegistry and LanguageRegistry
     * @param block
     * @param names
     * @param subName
     */
    public static void addBlock(Block block, String[] names, String subName){
	
	GameRegistry.registerBlock(block, subName);
	
	for(int k = 0; k < names.length; k++){
	    
	    LanguageRegistry.addName(new ItemStack(block, 1, k), names[k]);
	    
	}
	
    }
    
    public static ItemStack getCreativeTabIcon() {
	
	return new ItemStack(blockAntHill, 1, 0);
	
    }
    
    public static void addAnt(ItemAnt ant) {
	
	getAntList().add(ant);
	
    }
    
    public static void addHill(BlockAntHill hill) {
	
	getHillList().add(hill);
	
    }
    
    public static ArrayList<ItemAnt> getAntList() {
	
	return antList;
	
    }
    
    public static ArrayList<BlockAntHill> getHillList() {
	
	return hillList;
	
    }
    
}
