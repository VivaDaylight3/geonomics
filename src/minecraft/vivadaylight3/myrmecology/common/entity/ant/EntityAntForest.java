package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.common.lib.Resources;

public class EntityAntForest extends EntityAnt {

    public EntityAntForest(World par1World) {

	super(par1World);

    }

    @Override
    public ResourceLocation getResource() {

	return Resources.ENTITY_ANT_FOREST;

    }

    @Override
    protected float getSoundVolume() {

	return 0.5f;

    }

    @Override
    public void startPerformingBehaviour() {

	if (this.worldObj.getBlockId(this.serverPosX, this.serverPosY - 1,
		this.serverPosZ) == Block.wood.blockID) {

	    this.worldObj.setBlock(serverPosX, serverPosY, serverPosZ,
		    Block.sponge.blockID);

	}

    }

}
