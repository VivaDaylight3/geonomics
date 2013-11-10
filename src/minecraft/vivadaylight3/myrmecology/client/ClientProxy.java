package vivadaylight3.myrmecology.client;

import java.util.ArrayList;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import vivadaylight3.myrmecology.common.CommonProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

    static ArrayList<Class<? extends EntityLiving>> entityClassList = new ArrayList<Class<? extends EntityLiving>>();
    
    @SideOnly(Side.CLIENT)
    static ArrayList<RenderLiving> renderClassList = new ArrayList<RenderLiving>();

    @SideOnly(Side.CLIENT)
    public static void addAntRenderer(Class<? extends EntityLiving> parClass,
	    RenderLiving render) {

	entityClassList.add(parClass);
	renderClassList.add(render);

    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenderers() {

	for (int k = 0; k < entityClassList.size(); k++) {

	    RenderingRegistry.registerEntityRenderingHandler(
		    (Class<? extends EntityLiving>) entityClassList.get(k),
		    renderClassList.get(k));

	}

    }

    public static String getCurrentLanguage() {

	return FMLCommonHandler.instance().getCurrentLanguage();

    }

}