package vivadaylight3.myrmecology.common;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;

public class CommonProxy {

    public void addAntRenderer(Class<? extends EntityLiving> parClass,
	    RenderLiving render) {

    }

    public void registerKeyBindings() {

    }

    public void registerRenderers() {

    }

    public String getCurrentLanguage() {

	return null;

    }
    
    public World getClientWorld()
    {
        return null;
    }

}