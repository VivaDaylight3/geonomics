package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ai.AntBehaviourOdourous;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntOdourous extends EntityAnt {

    public EntityAntOdourous(World par1World) {
	super(par1World);
	this.tasks.addTask(1,
		new AntBehaviourOdourous(this, par1World, this.getNavigator()));
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antOdourous;
    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_ODOUROUS;

    }

}
