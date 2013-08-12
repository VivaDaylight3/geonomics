package vivadaylight3.myrmecology.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import vivadaylight3.myrmecology.common.entity.MobAnt;
import vivadaylight3.myrmecology.common.handler.MyrmecologyGuiHandler;
import vivadaylight3.myrmecology.common.lib.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.lib.Url;
import cpw.mods.fml.common.Loader;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Myrmecology main class
 * 
 * @author VivaDaylight3
 */

@Mod(modid = Myrmecology.MOD_ID, name = Myrmecology.MOD_NAME, version = Myrmecology.MOD_VERSION, dependencies = Myrmecology.MOD_DEPENDENCIES)
@NetworkMod(channels = Myrmecology.MOD_CHANNEL, clientSideRequired = true, serverSideRequired = false, packetHandler = MyrmecologyPacketHandler.class)
public class Myrmecology {
    
    public static final String MOD_CHANNEL = "Myrmecology";
    public static final String MOD_ID = "Myrmecology";
    public static final String MOD_ID_LOWER = "myrmecology";
    public static final String MOD_NAME = "Myrmecology";
    public static final String MOD_VERSION = "0.0.1";
    public static final String MOD_DEPENDENCIES = "";
    
    @SidedProxy(clientSide = "vivadaylight3.myrmecology.client.ClientProxy", serverSide = "vivadaylight3.myrmecology.common.CommonProxy")
    public static CommonProxy proxy;
    
    @Metadata
    public static ModMetadata meta;
    
    @Instance(MOD_NAME)
    public static Myrmecology instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	
	Configuration config = new Configuration(
		event.getSuggestedConfigurationFile());
	
	try {
	    System.out.println("###VERSION### = "+Url.getLatestVersion());
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
	
	Register.setConfig(config);
	
	Register.registerCreativeTab();
	
	Register.registerBlocks();
	
	Register.registerItems();
	
	Register.registerBreeding();
	
	Register.registerRecipes();
	
	Register.registerWorldGen();
	
	MyrmecologyGuiHandler guiHandler = new MyrmecologyGuiHandler();
	
	NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
	
    }
    
    @EventHandler
    public void mainInit(FMLInitializationEvent event) {
	
	proxy.registerRenderers();
	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
	
    }
    
}
