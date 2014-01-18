package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ai.AntBehaviourMound;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntMound extends EntityAnt {

    public EntityAntMound(World par1World) {
	super(par1World);
	this.tasks
		.addTask(
			1,
			new AntBehaviourMound(this, this.worldObj, this
				.getNavigator()));
    }

    @Override
    public boolean isAIEnabled() {

	return true;

    }

    @Override
    public ItemAnt getAnt() {
	return Register.antMound;
    }

    @Override
    protected int getDropItemId() {

	if (this.inventory[0] == null) {

	    return 0;

	}

	return this.inventory[0].getItem().itemID;
    }

}
