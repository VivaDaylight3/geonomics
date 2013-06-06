package vivadaylight3.geoponics.common;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=Geoponics.MOD_ID, name=Geoponics.MOD_NAME, version=Geoponics.MOD_VERSION, dependencies = Geoponics.MOD_DEPENDENCIES)
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels =Geoponics.MOD_CHANNEL, packetHandler = GeoponicsPacketHandler.class)
public class Geoponics
{
	
	public static final String MOD_ID = "Geoponics";
	public static final String MOD_NAME = "Geoponics";
	public static final String MOD_NAME_LOWER = "geoponics";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_DEPENDENCIES = "";
	
	@SidedProxy(clientSide = "vivadaylight3." + Geoponics.MOD_NAME_LOWER + ".client.ClientProxy", serverSide = "vivadaylight3." + Geoponics.MOD_NAME_LOWER + ".common.CommonProxy")
	public static final String MOD_CHANNEL = Geoponics.MOD_ID;
	
	public static final int ID_BLOCK = 1551;
	public static final int ID_ITEM = 3853;
	
	@Metadata(Geoponics.MOD_ID)
	public static ModMetadata meta;
	
	public static final String RESOURCE_PATH = "/mods/" + Geoponics.MOD_NAME_LOWER + "/";
	public static final String TEXTURE_PATH = RESOURCE_PATH + "textures/";
	public static final String BLOCK_PATH = TEXTURE_PATH + "blocks/";
	public static final String ITEM_PATH = TEXTURE_PATH + "items/";
	public static final String GUI_PATH = TEXTURE_PATH + "gui/";
	public static final String LANG_PATH = RESOURCE_PATH + "langauges/";
	public static final String TEXTURE_PREFIX = Geoponics.MOD_NAME_LOWER + ":";
	
	private static Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), MOD_ID + ".cfg"));
	
	@Instance(MOD_NAME)
	public static Geoponics instance;
	
	// Items
	

}
