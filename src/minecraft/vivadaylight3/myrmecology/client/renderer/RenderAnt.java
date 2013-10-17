package vivadaylight3.myrmecology.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntForest;
import vivadaylight3.myrmecology.common.lib.Resources;

public class RenderAnt extends RenderLiving {

    public RenderAnt(ModelBase par1ModelBase, float par2) {
	
	super(par1ModelBase, par2);
	
    }
    
    @Override
    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
	super.doRenderLiving((EntityAntForest)entityliving, d, d1, d2, f, f1);
    }
    
    @Override
    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
	super.doRenderLiving((EntityAnt)entity, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
		
	EntityAnt entity2 = (EntityAnt)entity;
	
	return entity2.getResource();
    }

}
