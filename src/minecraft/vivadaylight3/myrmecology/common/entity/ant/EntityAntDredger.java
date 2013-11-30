package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ai.AntBehaviourDredger;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntDredger extends EntityAnt {

    public EntityAntDredger(World par1World) {
	super(par1World);
	this.tasks.addTask(1,
		new AntBehaviourDredger(this, par1World, this.getNavigator()));
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antDredger;
    }

    @Override
    public boolean isAIEnabled() {

	return false;

    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_DREDGER;

    }

}
