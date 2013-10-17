package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntCarpenter extends EntityAnt {

    public EntityAntCarpenter(World par1World) {
	super(par1World);
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antCarpenter;
    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_CARPENTER;

    }

}
