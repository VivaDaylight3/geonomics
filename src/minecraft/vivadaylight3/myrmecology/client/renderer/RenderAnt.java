package vivadaylight3.myrmecology.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderAnt extends RenderLiving {

    private ResourceLocation textureLocation;

    @SideOnly(Side.CLIENT)
    public RenderAnt(ResourceLocation parResLoc, ModelBase par1ModelBase,
	    float par2) {
	super(par1ModelBase, par2);
	textureLocation = parResLoc;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

	return textureLocation;
    }

}
