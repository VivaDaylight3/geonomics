package vivadaylight3.myrmecology.common;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.block.BlockAntHill;
import vivadaylight3.myrmecology.api.breeding.Breeding;
import vivadaylight3.myrmecology.api.entity.ai.EnumAntAIType;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.item.ItemBreedingChamber;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.block.BlockAntChest;
import vivadaylight3.myrmecology.common.block.BlockAntFarm;
import vivadaylight3.myrmecology.common.block.BlockFungi;
import vivadaylight3.myrmecology.common.block.BlockIncubator;
import vivadaylight3.myrmecology.common.block.BlockInfuser;
import vivadaylight3.myrmecology.common.block.BlockPheromone;
import vivadaylight3.myrmecology.common.block.anthill.AntHillDesert;
import vivadaylight3.myrmecology.common.block.anthill.AntHillForest;
import vivadaylight3.myrmecology.common.block.anthill.AntHillJungle;
import vivadaylight3.myrmecology.common.block.anthill.AntHillPlains;
import vivadaylight3.myrmecology.common.block.anthill.AntHillSnow;
import vivadaylight3.myrmecology.common.block.anthill.AntHillStone;
import vivadaylight3.myrmecology.common.block.anthill.AntHillSwamp;
import vivadaylight3.myrmecology.common.block.anthill.AntHillWater;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntCarpenter;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntDredger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntFungal;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntMound;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntOdourous;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntScavenger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntSprouter;
import vivadaylight3.myrmecology.common.handler.WorldGen;
import vivadaylight3.myrmecology.common.item.ItemAntBag;
import vivadaylight3.myrmecology.common.item.ItemMyrmopaedia;
import vivadaylight3.myrmecology.common.item.ItemPheromone;
import vivadaylight3.myrmecology.common.item.ItemUpgrade;
import vivadaylight3.myrmecology.common.item.ToolExtractor;
import vivadaylight3.myrmecology.common.item.ant.AntBarbaric;
import vivadaylight3.myrmecology.common.item.ant.AntCarpenter;
import vivadaylight3.myrmecology.common.item.ant.AntCommon;
import vivadaylight3.myrmecology.common.item.ant.AntCultivator;
import vivadaylight3.myrmecology.common.item.ant.AntDesert;
import vivadaylight3.myrmecology.common.item.ant.AntDredger;
import vivadaylight3.myrmecology.common.item.ant.AntForest;
import vivadaylight3.myrmecology.common.item.ant.AntFungal;
import vivadaylight3.myrmecology.common.item.ant.AntHarvester;
import vivadaylight3.myrmecology.common.item.ant.AntHostile;
import vivadaylight3.myrmecology.common.item.ant.AntJungle;
import vivadaylight3.myrmecology.common.item.ant.AntMound;
import vivadaylight3.myrmecology.common.item.ant.AntOdourous;
import vivadaylight3.myrmecology.common.item.ant.AntPlains;
import vivadaylight3.myrmecology.common.item.ant.AntPlentiful;
import vivadaylight3.myrmecology.common.item.ant.AntScavenger;
import vivadaylight3.myrmecology.common.item.ant.AntSnow;
import vivadaylight3.myrmecology.common.item.ant.AntSprouter;
import vivadaylight3.myrmecology.common.item.ant.AntStone;
import vivadaylight3.myrmecology.common.item.ant.AntSwamp;
import vivadaylight3.myrmecology.common.item.ant.AntWater;
import vivadaylight3.myrmecology.common.item.chamber.ChamberBarbaric;
import vivadaylight3.myrmecology.common.item.chamber.ChamberCarpenter;
import vivadaylight3.myrmecology.common.item.chamber.ChamberCommon;
import vivadaylight3.myrmecology.common.item.chamber.ChamberCultivator;
import vivadaylight3.myrmecology.common.item.chamber.ChamberDredger;
import vivadaylight3.myrmecology.common.item.chamber.ChamberFungal;
import vivadaylight3.myrmecology.common.item.chamber.ChamberHarvester;
import vivadaylight3.myrmecology.common.item.chamber.ChamberHostile;
import vivadaylight3.myrmecology.common.item.chamber.ChamberMound;
import vivadaylight3.myrmecology.common.item.chamber.ChamberOdourous;
import vivadaylight3.myrmecology.common.item.chamber.ChamberPlentiful;
import vivadaylight3.myrmecology.common.item.chamber.ChamberScavenger;
import vivadaylight3.myrmecology.common.item.chamber.ChamberSprouter;
import vivadaylight3.myrmecology.common.lib.InfuserRecipe;
import vivadaylight3.myrmecology.common.lib.InfuserRecipeRegistry;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.lib.TreeDictionary;
import vivadaylight3.myrmecology.common.lib.Url;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Register {

    public static Configuration config;

    public static CreativeTabs tabMyrmecology;
    public static CreativeTabs tabAnts;
    public static final Material antHill = new Material(MapColor.dirtColor);

    private static ArrayList<ItemAnt> antList = new ArrayList<ItemAnt>();
    private static ArrayList<BlockAntHill> hillList = new ArrayList<BlockAntHill>();
    private static ArrayList<Class<? extends IEntityAnt>> entityAntList = new ArrayList<Class<? extends IEntityAnt>>();
    private static ArrayList<ItemBreedingChamber> chamberList = new ArrayList<ItemBreedingChamber>();
    private static ArrayList<Achievement> achievementList = new ArrayList<Achievement>();

    public static boolean checkForUpdates = true;
    public static int incubatorLarvaRenderDistance = 10;

    EnumAntAIType type;

    public static String language;

    public static final int ID_BLOCK = 600; // 12
    public static final int ID_ITEM = 3000; // 23

    static int id = 1;

    public static Achievement achieveReadBook;
    public static Achievement achieveExtractAnts;
    public static Achievement achieveIncubateAnts;
    public static Achievement achieveBreedAnts;
    public static Achievement achieveSpawnAnts;
    public static Achievement achieveAntDimension;
    public static Achievement achieveUpgrade;
    public static Achievement achieveInfuser;

    public static int entityAntForestID;
    public static int entityAntCarpenterID;
    public static int entityAntOdourousID;
    public static int entityAntScavengerID;
    private static int entityAntDredgerID;
    private static int entityAntFungalID;
    private static int entityAntMoundID;
    private static int entityAntSprouterID;

    public static final int GUI_ID_ANTFARM = 1;
    public static final int GUI_ID_MYRMOPAEDIA = 2;
    public static final int GUI_ID_INCUBATOR = 3;
    public static final int GUI_ID_ANTCHEST = 4;
    public static final int GUI_ID_INFUSER = 5;

    public static int latestItemID = 0;
    public static int latestBlockID = 0;

    public static Block blockAntFarm;
    public static Block blockAntHill;
    public static Block blockIncubator;
    public static Block blockFungi;
    public static Block blockAntChest;
    public static Block blockPheromone;
    public static Block blockInfuser;

    // TODO
    public static ToolExtractor itemExtractor;
    public static ItemMyrmopaedia itemAntopedia;
    public static ItemBreedingChamber itemBreedingChamber;
    public static ItemPheromone itemPheromoneBottle;
    public static ItemUpgrade itemUpgrade;
    public static ItemAntBag itemAntBag;

    public static ChamberCommon chamberCommon;
    public static ChamberHarvester chamberHarvester;
    public static ChamberCarpenter chamberCarpenter;
    public static ChamberMound chamberMound;
    public static ChamberBarbaric chamberBarbaric;
    public static ChamberOdourous chamberOdourous;
    public static ChamberHostile chamberHostile;
    public static ChamberPlentiful chamberPlentiful;
    public static ChamberDredger chamberDredger;
    public static ChamberScavenger chamberScavenger;
    public static ChamberCultivator chamberCultivator;
    public static ChamberSprouter chamberSprouter;
    public static ChamberFungal chamberFungal;

    public static AntHillForest hillForest;
    public static AntHillJungle hillJungle;
    public static AntHillDesert hillDesert;
    public static AntHillPlains hillPlains;
    public static AntHillSnow hillSnow;
    public static AntHillStone hillStone;
    public static AntHillSwamp hillSwamp;
    public static AntHillWater hillWater;

    public static AntForest antForest;
    public static AntDesert antDesert;
    public static AntCommon antCommon;
    public static AntWater antWater;
    public static AntSwamp antSwamp;
    public static AntJungle antJungle;
    public static AntPlains antPlains;
    public static AntSnow antSnow;
    public static AntStone antStone;
    public static AntHarvester antHarvester;
    public static AntCarpenter antCarpenter;
    public static AntMound antMound;
    public static AntBarbaric antBarbaric;
    public static AntOdourous antOdourous;
    public static AntHostile antHostile;
    public static AntPlentiful antPlentiful;
    public static AntDredger antDredger;
    public static AntScavenger antScavenger;
    public static AntCultivator antCultivator;
    public static AntSprouter antSprouter;
    public static AntFungal antFungal;

    public static AchievementPage achievementPage;

    // TODO
    public static void setConfig(Configuration parConfig) {

	config = parConfig;

    }

    public static int getNewItemID() {

	latestItemID += 1;

	return latestItemID + ID_ITEM;

    }

    public static int getNewBlockID() {

	latestBlockID += 1;

	return latestBlockID + ID_BLOCK;

    }

    public static void registerTreeDictionary() {

	TreeDictionary.registerVanillaTrees();
	TreeDictionary.registerTreesFromOreDict(false);

    }

    public static void registerAchievements() {

	achieveReadBook = addAchievement(27, "Myrmecologist",
		"Read an myrmopaedia", 0, 0, null, new ItemStack(itemAntopedia));
	achieveExtractAnts = addAchievement(28, "Extraction Completion",
		"Extract some ants from an ant hill!", 2, 0, achieveReadBook,
		new ItemStack(itemExtractor));
	achieveIncubateAnts = addAchievement(29, "Nature or Nurture?",
		"Start incubating some ants!", 2, 2, achieveExtractAnts,
		new ItemStack(blockIncubator));
	achieveBreedAnts = addAchievement(30, "Breeding, just the beginning",
		"Start breeding some ants!", 0, 2, achieveIncubateAnts,
		new ItemStack(blockAntFarm));
	achieveSpawnAnts = addAchievement(
		31,
		"Ant Enslavement",
		"Spawn an ant "
			+ Reference.standardTypeNames[Metadata.getMetaWorker()]
			+ " and make it do your bidding", -2, 2,
		achieveBreedAnts,
		new ItemStack(antForest, 1, Metadata.getMetaWorker()));

	achieveUpgrade = addAchievement(33, "Myrmecological Upgrades",
		"Place and use a formicarium or solarium upgrade", 2, 4,
		achieveIncubateAnts, new ItemStack(itemUpgrade, 1, 0));
	
	achieveInfuser = addAchievement(34, "Pheromonic Infuser",
		"Place and use a pheromonic infuser", 0, 4, achieveBreedAnts,
		new ItemStack(blockInfuser));

	achieveAntDimension = addAchievement(32, "Holiday Destination",
		"CLASSIFIED", -2, 0, achieveSpawnAnts, new ItemStack(
			blockAntChest));
	achievementPage = new AchievementPage("Myrmecology", achieveReadBook);

	for (int k = 0; k < achievementList.size(); k++) {

	    if (achievementList.get(k) != achieveReadBook) {

		achievementPage.getAchievements().add(achievementList.get(k));

	    }

	}

	AchievementPage.registerAchievementPage(achievementPage);

    }

    public static void registerLanguages() {

	String path = Resources.LANG_LOCATION;

	String files;
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++) {

	    if (listOfFiles[i].isFile()) {
		files = listOfFiles[i].getName();
		if (files.endsWith(".txt") || files.endsWith(".TXT")) {
		}
	    }
	}
    }

    public static void registerBlocks() {

	config.load();

	blockPheromone = new BlockPheromone(config.get(
		Configuration.CATEGORY_BLOCK, "pheromone", getNewBlockID())
		.getInt());

	blockAntChest = new BlockAntChest(config.get(
		Configuration.CATEGORY_BLOCK, Reference.BLOCK_ANTCHEST_NAME,
		getNewBlockID()).getInt());

	blockFungi = new BlockFungi(config.get(Configuration.CATEGORY_BLOCK,
		Reference.BLOCK_FUNGI_NAME, getNewBlockID()).getInt(),
		Reference.BLOCK_FUNGI_NAME);

	blockAntFarm = new BlockAntFarm(config.get(
		Configuration.CATEGORY_BLOCK, Reference.BLOCK_ANTFARM_NAME,
		getNewBlockID()).getInt(), Reference.BLOCK_ANTFARM_NAME);

	blockIncubator = new BlockIncubator(config.get(
		Configuration.CATEGORY_BLOCK, Reference.BLOCK_INCUBATOR_NAME,
		getNewBlockID()).getInt(), Reference.BLOCK_INCUBATOR_NAME);

	blockInfuser = new BlockInfuser(config.get(
		Configuration.CATEGORY_BLOCK, Reference.BLOCK_INFUSER_NAME,
		getNewBlockID()).getInt(), Reference.BLOCK_INFUSER_NAME);

	hillForest = new AntHillForest(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_FOREST_NAME, getNewBlockID()).getInt());

	hillDesert = new AntHillDesert(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_DESERT_NAME, getNewBlockID()).getInt());

	hillJungle = new AntHillJungle(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_JUNGLE_NAME, getNewBlockID()).getInt());

	hillPlains = new AntHillPlains(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_PLAINS_NAME, getNewBlockID()).getInt(),
		Material.ground);

	hillSnow = new AntHillSnow(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_SNOW_NAME, getNewBlockID()).getInt(),
		Material.ground);

	hillStone = new AntHillStone(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_STONE_NAME, getNewBlockID()).getInt(),
		Material.ground);

	hillSwamp = new AntHillSwamp(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_SWAMP_NAME, getNewBlockID()).getInt(),
		Material.ground);

	hillWater = new AntHillWater(config.get(Configuration.CATEGORY_BLOCK,
		Reference.HILL_WATER_NAME, getNewBlockID()).getInt(),
		Material.ground);

	config.save();

	addBlock(blockInfuser, "Pheromonic Infuser",
		Reference.BLOCK_INFUSER_NAME);

	addBlock(blockPheromone, "Pheromone Block", "blockPheromone");

	addBlock(blockAntChest, "Scavenging Chest",
		Reference.BLOCK_ANTCHEST_NAME);

	addBlock(blockFungi, "Agaricus Fungi Block", Reference.BLOCK_FUNGI_NAME);
	addBlock(blockIncubator, "Solarium", Reference.BLOCK_INCUBATOR_NAME);
	addBlock(blockAntFarm, "Formicarium", Reference.BLOCK_ANTFARM_NAME);
	addBlock(hillForest, hillForest.getHillName(),
		hillForest.getHillSubName());
	addBlock(hillDesert, hillDesert.getHillName(),
		hillDesert.getHillSubName());
	addBlock(hillJungle, hillJungle.getHillName(),
		hillJungle.getHillSubName());
	addBlock(hillPlains, hillPlains.getHillName(),
		hillPlains.getHillSubName());
	addBlock(hillSnow, hillSnow.getHillName(), hillSnow.getHillSubName());
	addBlock(hillStone, hillStone.getHillName(), hillStone.getHillSubName());
	addBlock(hillSwamp, hillSwamp.getHillName(), hillSwamp.getHillSubName());
	addBlock(hillWater, hillWater.getHillName(), hillWater.getHillSubName());

    }

    public static boolean checkForUpdates(Url url) {

	config.load();

	checkForUpdates = config.get(Configuration.CATEGORY_GENERAL,
		"check for updates", false).getBoolean(false);

	config.save();

	if (checkForUpdates) {

	    return url.updateIsAvailable();

	} else {

	    return false;

	}

    }

    public static void getConfigSettings() {

	config.load();

	incubatorLarvaRenderDistance = config.get(
		Configuration.CATEGORY_GENERAL, "Render solarium larva within",
		10).getInt();

	config.save();

    }

    public static void registerItems() {

	config.load();

	itemExtractor = new ToolExtractor(config.get(
		Configuration.CATEGORY_ITEM, Reference.ITEM_EXTRACTOR_NAME,
		getNewItemID()).getInt());

	itemAntopedia = new ItemMyrmopaedia(config.get(
		Configuration.CATEGORY_ITEM, Reference.ITEM_MYRMOPAEDIA_NAME,
		getNewItemID()).getInt());

	// Chambers
	itemBreedingChamber = new ItemBreedingChamber(config.get(
		Configuration.CATEGORY_ITEM, Reference.ITEM_CHAMBER_NAME,
		getNewItemID()).getInt());

	chamberCommon = new ChamberCommon(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_COMMON_NAME,
		getNewItemID()).getInt());

	chamberHarvester = new ChamberHarvester(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_HARVESTER_NAME,
		getNewItemID()).getInt());

	chamberBarbaric = new ChamberBarbaric(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_BARBARIC_NAME,
		getNewItemID()).getInt());

	chamberPlentiful = new ChamberPlentiful(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_PLENTIFUL_NAME,
		getNewItemID()).getInt());

	chamberCultivator = new ChamberCultivator(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_CULTIVATOR_NAME,
		getNewItemID()).getInt());

	chamberCarpenter = new ChamberCarpenter(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_CARPENTER_NAME,
		getNewItemID()).getInt());

	chamberMound = new ChamberMound(config.get(Configuration.CATEGORY_ITEM,
		Reference.CHAMBER_MOUND_NAME, getNewItemID()).getInt());

	chamberOdourous = new ChamberOdourous(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_ODOUROUS_NAME,
		getNewItemID()).getInt());

	chamberHostile = new ChamberHostile(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_HOSTILE_NAME,
		getNewItemID()).getInt());

	chamberDredger = new ChamberDredger(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_DREDGER_NAME,
		getNewItemID()).getInt());

	chamberScavenger = new ChamberScavenger(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_SCAVENGER_NAME,
		getNewItemID()).getInt());

	chamberSprouter = new ChamberSprouter(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_SPROUTER_NAME,
		getNewItemID()).getInt());

	chamberFungal = new ChamberFungal(config.get(
		Configuration.CATEGORY_ITEM, Reference.CHAMBER_FUNGAL_NAME,
		getNewItemID()).getInt());

	// Ants
	antForest = new AntForest(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_FOREST_NAME, getNewItemID()).getInt());

	antDesert = new AntDesert(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_DESERT_NAME, getNewItemID()).getInt());

	antWater = new AntWater(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_WATER_NAME, getNewItemID()).getInt());

	antSwamp = new AntSwamp(config.get(Configuration.CATEGORY_ITEM,
		"antSwamp", getNewItemID()).getInt());

	antJungle = new AntJungle(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_JUNGLE_NAME, getNewItemID()).getInt());

	antPlains = new AntPlains(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_PLAINS_NAME, getNewItemID()).getInt());

	antSnow = new AntSnow(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_SNOW_NAME, getNewItemID()).getInt());

	antStone = new AntStone(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_STONE_NAME, getNewItemID()).getInt());

	antCommon = new AntCommon(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_COMMON_NAME, getNewItemID()).getInt());

	antHarvester = new AntHarvester(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_HARVESTER_NAME, getNewItemID()).getInt());

	antBarbaric = new AntBarbaric(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_BARBARIC_NAME, getNewItemID()).getInt());

	antPlentiful = new AntPlentiful(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_PLENTIFUL_NAME, getNewItemID()).getInt());

	antCultivator = new AntCultivator(config.get(
		Configuration.CATEGORY_ITEM, Reference.ANT_CULTIVATOR_NAME,
		getNewItemID()).getInt());

	antCarpenter = new AntCarpenter(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_CARPENTER_NAME, getNewItemID()).getInt());

	antMound = new AntMound(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_MOUND_NAME, getNewItemID()).getInt());

	antOdourous = new AntOdourous(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_ODOUROUS_NAME, getNewItemID()).getInt());

	antHostile = new AntHostile(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_HOSTILE_NAME, getNewItemID()).getInt());

	antDredger = new AntDredger(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_DREDGER_NAME, getNewItemID()).getInt());

	antScavenger = new AntScavenger(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_SCAVENGER_NAME, getNewItemID()).getInt());

	antSprouter = new AntSprouter(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_SPROUTER_NAME, getNewItemID()).getInt());

	antFungal = new AntFungal(config.get(Configuration.CATEGORY_ITEM,
		Reference.ANT_FUNGAL_NAME, getNewItemID()).getInt());

	itemPheromoneBottle = new ItemPheromone(config.get(
		Configuration.CATEGORY_ITEM, Reference.ITEM_PHEROMONE_NAME,
		getNewItemID()).getInt(), Reference.ITEM_PHEROMONE_NAME);

	itemUpgrade = new ItemUpgrade(config.get(Configuration.CATEGORY_ITEM,
		Reference.ITEM_UPGRADE_NAME, getNewItemID()).getInt(),
		Reference.ITEM_UPGRADE_NAME);

	itemAntBag = new ItemAntBag(config.get(Configuration.CATEGORY_ITEM,
		Reference.ITEM_ANTBAG_NAME, getNewItemID()).getInt(),
		Reference.ITEM_ANTBAG_NAME);

	config.save();

	// TODO
	addItem(itemAntBag, "Ant Canister", Reference.ITEM_ANTBAG_NAME);

	addItem(itemExtractor, "Ant Extractor", Reference.ITEM_EXTRACTOR_NAME);

	addItem(itemAntopedia, "Myrmopaedia", Reference.ITEM_MYRMOPAEDIA_NAME);

	addItem(itemBreedingChamber, "Breeding Chamber",
		Reference.ITEM_CHAMBER_NAME);

	addItem(chamberCommon, chamberCommon.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.CHAMBER_COMMON_NAME);

	addItem(chamberHarvester, chamberHarvester.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_HARVESTER_NAME);

	addItem(chamberCarpenter, chamberCarpenter.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_CARPENTER_NAME);

	addItem(chamberMound, chamberMound.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_MOUND_NAME);

	addItem(chamberBarbaric, chamberBarbaric.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_BARBARIC_NAME);

	addItem(chamberOdourous, chamberOdourous.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_ODOUROUS_NAME);

	addItem(chamberHostile, chamberHostile.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_HOSTILE_NAME);

	addItem(chamberPlentiful, chamberPlentiful.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_PLENTIFUL_NAME);

	addItem(chamberDredger, chamberDredger.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_DREDGER_NAME);

	addItem(chamberScavenger, chamberScavenger.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_SCAVENGER_NAME);

	addItem(chamberCultivator, chamberCultivator.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_CULTIVATOR_NAME);

	addItem(chamberSprouter, chamberSprouter.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_SPROUTER_NAME);

	addItem(chamberFungal, chamberFungal.getChamberAntSpeciesName()
		+ "Breeding Chamber", Reference.MOD_ID
		+ Reference.CHAMBER_FUNGAL_NAME);

	// Ants
	addItem(antForest, antForest.getNames(),
		Reference.MOD_ID + antForest.getSpeciesSubName());

	addItem(antDesert, antDesert.getNames(),
		Reference.MOD_ID + antDesert.getSpeciesSubName());

	addItem(antCommon, antCommon.getNames(),
		Reference.MOD_ID + antCommon.getSpeciesSubName());

	addItem(antWater, antWater.getNames(),
		Reference.MOD_ID + antWater.getSpeciesSubName());

	addItem(antSwamp, antSwamp.getNames(),
		Reference.MOD_ID + antSwamp.getSpeciesSubName());

	addItem(antJungle, antJungle.getNames(),
		Reference.MOD_ID + antJungle.getSpeciesSubName());

	addItem(antPlains, antPlains.getNames(),
		Reference.MOD_ID + antPlains.getSpeciesSubName());

	addItem(antSnow, antSnow.getNames(),
		Reference.MOD_ID + antSnow.getSpeciesSubName());

	addItem(antStone, antStone.getNames(),
		Reference.MOD_ID + antStone.getSpeciesSubName());

	addItem(antHarvester, antHarvester.getNames(), Reference.MOD_ID
		+ antHarvester.getSpeciesSubName());

	addItem(antCarpenter, antCarpenter.getNames(), Reference.MOD_ID
		+ antCarpenter.getSpeciesSubName());

	addItem(antMound, antMound.getNames(),
		Reference.MOD_ID + antMound.getSpeciesSubName());

	addItem(antBarbaric, antBarbaric.getNames(), Reference.MOD_ID
		+ antBarbaric.getSpeciesSubName());

	addItem(antOdourous, antOdourous.getNames(), Reference.MOD_ID
		+ antOdourous.getSpeciesSubName());

	addItem(antHostile, antHostile.getNames(), Reference.MOD_ID
		+ antHostile.getSpeciesSubName());

	addItem(antPlentiful, antPlentiful.getNames(), Reference.MOD_ID
		+ antPlentiful.getSpeciesSubName());

	addItem(antDredger, antDredger.getNames(), Reference.MOD_ID
		+ antDredger.getSpeciesSubName());

	addItem(antScavenger, antScavenger.getNames(), Reference.MOD_ID
		+ antScavenger.getSpeciesSubName());

	addItem(antCultivator, antCultivator.getNames(), Reference.MOD_ID
		+ antCultivator.getSpeciesSubName());

	addItem(antSprouter, antSprouter.getNames(), Reference.MOD_ID
		+ antSprouter.getSpeciesSubName());

	addItem(antFungal, antFungal.getNames(),
		Reference.MOD_ID + antFungal.getSpeciesSubName());

	addItem(itemUpgrade, new String[] { "Solarium Upgrade",
		"Formicarium Upgrade" }, Reference.MOD_ID
		+ Reference.ITEM_UPGRADE_NAME);

    }

    // TODO
    public static void registerBreeding() {

	Breeding breeding = new Breeding();

	breeding.addBreeding(antCommon, antForest, antHarvester);
	breeding.addBreeding(antCommon, antPlains, antHarvester);
	breeding.addBreeding(antHarvester, antForest, antCarpenter);
	breeding.addBreeding(antHarvester, antPlains, antMound);

	breeding.addBreeding(antCommon, antJungle, antBarbaric);
	breeding.addBreeding(antCommon, antDesert, antBarbaric);
	breeding.addBreeding(antBarbaric, antDesert, antOdourous);
	breeding.addBreeding(antBarbaric, antJungle, antHostile);

	breeding.addBreeding(antCommon, antSnow, antPlentiful);
	breeding.addBreeding(antCommon, antStone, antPlentiful);
	breeding.addBreeding(antPlentiful, antStone, antDredger);
	breeding.addBreeding(antPlentiful, antSnow, antScavenger);

	breeding.addBreeding(antCommon, antSwamp, antCultivator);
	breeding.addBreeding(antCommon, antWater, antCultivator);
	breeding.addBreeding(antCultivator, antSwamp, antFungal);
	breeding.addBreeding(antCultivator, antWater, antSprouter);

    }

    public static void registerCreativeTab() {

	tabMyrmecology = new CreativeTabs("tab" + Reference.MOD_ID) {

	    public ItemStack getIconItemStack() {
		return new ItemStack(itemAntopedia, 1, 0);
	    }

	};

	LanguageRegistry.instance().addStringLocalization(
		"itemGroup." + "tab" + Reference.MOD_ID, "en_US",
		Reference.MOD_ID);

	tabAnts = new CreativeTabs("tab" + Reference.MOD_ID + "Ants") {

	    public ItemStack getIconItemStack() {
		return new ItemStack(antForest, 1, 0);
	    }

	};

	LanguageRegistry.instance().addStringLocalization(
		"itemGroup." + "tab" + Reference.MOD_ID + "Ants", "en_US",
		Reference.MOD_ID + " Ants");

    }

    // TODO
    public static void registerEntities() {

	addEntityAnt(EntityAntScavenger.class, antScavenger.getSpeciesName(),
		entityAntScavengerID, 100, 20, true);
	//
	addEntityAnt(EntityAntCarpenter.class, antCarpenter.getSpeciesName(),
		entityAntCarpenterID, 100, 20, true);
	//
	addEntityAnt(EntityAntOdourous.class, antOdourous.getSpeciesName(),
		entityAntOdourousID, 100, 20, true);

	addEntityAnt(EntityAntDredger.class, antDredger.getSpeciesName(),
		entityAntDredgerID, 100, 20, true);

	addEntityAnt(EntityAntFungal.class, antFungal.getSpeciesName(),
		entityAntFungalID, 100, 20, true);

	addEntityAnt(EntityAntMound.class, antMound.getSpeciesName(),
		entityAntMoundID, 100, 20, true);

	addEntityAnt(EntityAntSprouter.class, antSprouter.getSpeciesName(),
		entityAntSprouterID, 100, 20, true);

    }

    public static void registerRecipes() {

	InfuserRecipeRegistry
		.addRecipe(new InfuserRecipe(new ItemStack(blockInfuser), true,
			new ItemStack[] { new ItemStack(itemPheromoneBottle),
				new ItemStack(Block.dirt) }));

	GameRegistry.addRecipe(new ItemStack(blockIncubator, 1), "wgw", "g g",
		"wrw", 'w', new ItemStack(Block.woodSingleSlab), 'g',
		new ItemStack(Block.glass), 'r', new ItemStack(
			Block.redstoneLampIdle));

	GameRegistry.addRecipe(new ItemStack(blockAntFarm, 1), "wgw", "gsg",
		"wgw", 'w', new ItemStack(Block.woodSingleSlab), 'g',
		new ItemStack(Block.glass), 's', new ItemStack(Block.sand));

	GameRegistry.addRecipe(new ItemStack(itemBreedingChamber), "sws",
		"w w", "sws", 's', new ItemStack(Item.stick), 'w',
		new ItemStack(Block.cloth));

	GameRegistry.addRecipe(new ItemStack(itemAntopedia, 1), "ggg", "rir",
		"ggg", 'g', new ItemStack(Block.thinGlass), 'i', new ItemStack(
			Item.dyePowder), 'r', new ItemStack(Item.redstone));

	GameRegistry.addRecipe(new ItemStack(itemExtractor), " s ", "did",
		" d ", 's', new ItemStack(Item.shovelIron), 'd', new ItemStack(
			Item.dyePowder, 1, 2), 'i', new ItemStack(
			Item.ingotIron));

	GameRegistry
		.addRecipe(new ItemStack(itemUpgrade, 1, 0), "rgr", "gpg",
			"rgr", 'r', Item.redstone, 'g', Item.ingotGold, 'p',
			Item.paper);
	GameRegistry.addRecipe(new ItemStack(itemUpgrade, 1, 1), "sgs", "gpg",
		"sgs", 's', Item.sugar, 'g', Item.ingotGold, 'p', Item.paper);

	GameRegistry.addShapelessRecipe(new ItemStack(blockPheromone),
		itemPheromoneBottle, Block.dirt);

	for (int k = 0; k < chamberList.size(); k++) {

	    if (chamberList.get(k) != itemBreedingChamber
		    && chamberList.get(k).getCraftingIngredient() != null) {

		GameRegistry.addShapelessRecipe(
			new ItemStack(chamberList.get(k)), new ItemStack(
				itemBreedingChamber), chamberList.get(k)
				.getCraftingIngredient());

	    }

	}

    }

    public static void registerTileEntities() {

	GameRegistry.registerTileEntity(TileEntityAntFarm.class,
		Reference.BLOCK_ANTFARM_NAME);

	GameRegistry.registerTileEntity(TileEntityIncubator.class,
		Reference.BLOCK_INCUBATOR_NAME);

	GameRegistry.registerTileEntity(TileEntityAntChest.class,
		Reference.BLOCK_ANTCHEST_NAME);

	// TODO

    }

    public static void registerWorldGen() {

	WorldGen worldGen = new WorldGen();

	GameRegistry.registerWorldGenerator(worldGen);

    }

    /**
     * Registers an item with the GameRegistry and LanguageRegistry
     * 
     * @param item
     * @param name
     * @param subName
     */
    public static void addItem(Item item, String name, String subName) {

	GameRegistry.registerItem(item, subName);
	LanguageRegistry.addName(item, name);

    }

    /**
     * Registers an item (with damage and multiple names) with the GameRegistry
     * and LanguageRegistry
     * 
     * @param item
     * @param names
     *            []
     * @param subName
     */
    public static void addItem(Item item, String[] names, String subName) {

	GameRegistry.registerItem(item, subName);

	for (int k = 0; k < names.length; k++) {

	    LanguageRegistry.addName(new ItemStack(item, 1, k), names[k]);

	}

    }

    /**
     * Registers a block with the GameRegistry and LanguageRegistry
     * 
     * @param item
     * @param name
     * @param subName
     */
    public static void addBlock(Block item, String name, String subName) {

	GameRegistry.registerBlock(item, subName);
	LanguageRegistry.addName(item, name);

    }

    /**
     * Registers an block (with damage and multiple names) with the GameRegistry
     * and LanguageRegistry
     * 
     * @param block
     * @param names
     * @param subName
     */
    public static void addBlock(Block block, String[] names, String subName) {

	GameRegistry.registerBlock(block, subName);

	for (int k = 0; k < names.length; k++) {

	    LanguageRegistry.addName(new ItemStack(block, 1, k), names[k]);

	}

    }

    public static ItemStack getCreativeTabIcon() {

	return new ItemStack(blockAntHill, 1, 0);

    }

    public static void addHill(BlockAntHill hill) {

	getHillList().add(hill);

    }

    public static void addEntityAnt(Class<? extends EntityLiving> class1,
	    String antName, int ID, int trackingRange, int updateFrequency,
	    boolean sendsVelocityUpdates) {

	getEntityAntList().add((Class<? extends IEntityAnt>) class1);

	EntityRegistry.registerModEntity(class1, antName, id,
		Myrmecology.instance, trackingRange, updateFrequency,
		sendsVelocityUpdates);

	EntityRegistry.registerGlobalEntityID(class1, antName, id);

	LanguageRegistry.instance().addStringLocalization(
		"entity." + Reference.MOD_ID + "." + antName + ".name",
		"en_US", antName);

	id++;

    }

    public static ArrayList<ItemAnt> getAntList() {

	return antList;

    }

    public static void addAnt(ItemAnt ant) {

	getAntList().add(ant);

    }

    public static Achievement addAchievement(int parID, String parName,
	    String parDesc, int posX, int posY, Achievement parParent,
	    ItemStack parIcon) {

	Achievement ach = new Achievement(parID, parName, posX, posY, parIcon,
		parParent);
	ach.registerAchievement();
	LanguageRegistry.instance().addStringLocalization(
		"achievement." + parName, "en_US", parName);
	LanguageRegistry.instance().addStringLocalization(
		"achievement." + parName + ".desc", "en_US", parDesc);

	achievementList.add(ach);

	return ach;

    }

    public static void addChamber(ItemBreedingChamber chamber) {

	chamberList.add(chamber);

    }

    public static ArrayList<BlockAntHill> getHillList() {

	return hillList;

    }

    public static ArrayList<Class<? extends IEntityAnt>> getEntityAntList() {

	return entityAntList;

    }
}
