package vivadaylight3.myrmecology.common.entity.ant;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ai.EntityAIAntBehaviour;

public class EntityAntForest extends EntityAnt {

    public EntityAntForest(World par1World) {

	super(par1World);
	this.setSize(1.0F, 1.0F);
	this.getNavigator().setAvoidsWater(true);
	this.tasks.addTask(0, new EntityAIAntBehaviour(this, par1World, this.getNavigator()));

    }

    @Override
    protected float getSoundVolume() {

	return 0.5f;
	
    }

    @Override
    public void startPerformingBehaviour() {
	
	if(this.worldObj.getBlockId(this.serverPosX, this.serverPosY - 1, this.serverPosZ) == Block.wood.blockID){
	    
	    this.worldObj.setBlock(serverPosX, serverPosY, serverPosZ, Block.sponge.blockID);
	    
	}

    }

}
