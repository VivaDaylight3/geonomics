package vivadaylight3.myrmecology.common;

import net.minecraftforge.common.Configuration;
import vivadaylight3.myrmecology.common.handler.MyrmecologyGuiHandler;
import vivadaylight3.myrmecology.common.handler.MyrmecologyPacketHandler;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Myrmecology main class
 * 
 * @author VivaDaylight3
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
@NetworkMod(channels = { Reference.MOD_CHANNEL, Reference.MOD_CHANNEL_INCUBATOR }, clientSideRequired = true, serverSideRequired = false, packetHandler = MyrmecologyPacketHandler.class)
public class Myrmecology {

    @SidedProxy(clientSide = "vivadaylight3.myrmecology.client.ClientProxy", serverSide = "vivadaylight3.myrmecology.common.CommonProxy")
    public static CommonProxy proxy;

    @Metadata
    public static ModMetadata meta;

    @Instance(Reference.MOD_NAME)
    public static Myrmecology instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

	Configuration config = new Configuration(
		event.getSuggestedConfigurationFile());

	Register.setConfig(config);

	Register.registerCreativeTab();

	Register.registerBlocks();

	Register.registerItems();

	Register.registerBreeding();

	Register.registerRecipes();

	Register.registerTileEntities();

	Register.registerWorldGen();
	
	Register.registerEntities();

	MyrmecologyGuiHandler guiHandler = new MyrmecologyGuiHandler();
	NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

	MyrmecologyPacketHandler packetHandler = new MyrmecologyPacketHandler();
	NetworkRegistry.instance().registerChannel(packetHandler,
		Reference.MOD_CHANNEL);
	
	TileEntityIncubator packetHandler2 = new TileEntityIncubator();
	NetworkRegistry.instance().registerChannel(packetHandler2,
		Reference.MOD_CHANNEL_INCUBATOR);

    }

    @EventHandler
    public void mainInit(FMLInitializationEvent event) {

	proxy.registerRenderers();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
