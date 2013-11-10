package vivadaylight3.myrmecology.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.entity.EntityAnt;

public class RenderAnt extends RenderLiving {
    
    @SideOnly(Side.CLIENT)
    public RenderAnt(ResourceLocation parResLoc, ModelBase par1ModelBase,
	    float par2) {
	super(par1ModelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

	return ((EntityAnt) entity).getResource();
    }

}
