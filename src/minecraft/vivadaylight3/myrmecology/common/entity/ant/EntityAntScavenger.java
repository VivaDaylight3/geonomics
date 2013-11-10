package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ai.AntBehaviourScavenger;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntScavenger extends EntityAnt {

    public EntityAntScavenger(World par1World) {
	super(par1World);
	this.tasks
		.addTask(
			1,
			new AntBehaviourScavenger(this, par1World, this
				.getNavigator()));

    }

    @Override
    public ItemAnt getAnt() {
	return Register.antScavenger;
    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_SCAVENGER;

    }

    @Override
    protected int getDropItemId() {

	if (this.inventory[0] == null) {

	    return 0;

	}

	return this.inventory[0].getItem().itemID;
    }

}
