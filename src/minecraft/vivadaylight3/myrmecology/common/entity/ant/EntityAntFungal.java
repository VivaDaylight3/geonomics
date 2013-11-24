package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntFungal extends EntityAnt {

    public EntityAntFungal(World par1World) {
	super(par1World);
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antFungal;
    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_FUNGAL;

    }

}
