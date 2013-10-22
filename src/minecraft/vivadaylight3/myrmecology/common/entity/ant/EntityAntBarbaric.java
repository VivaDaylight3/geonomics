package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntBarbaric extends EntityAnt {

    public EntityAntBarbaric(World par1World) {
	super(par1World);
    }
    
    @Override
    public ItemAnt getAnt() {
	return Register.antBarbaric;
    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_BARBARIC;

    }

}
