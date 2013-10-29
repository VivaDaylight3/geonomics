package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.common.lib.BlockEntry;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntBehaviourCarpenter extends EntityAIAntBehaviour {

    public AntBehaviourCarpenter(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);

    }

    @Override
    public void startExecuting() {

	if (this.theAnt.getHasGoneTo()) {

	    ArrayList<BlockEntry> list = Environment.getBlocksInRadius(world,
		    (int) this.theAnt.getPosX(), (int) this.theAnt.getPosY(),
		    (int) this.theAnt.getPosZ(), 10, Block.wood.blockID);

	    if (list.size() > 1 && this.theAnt.getTicks() / 20 == 1) {

		int meta = this.world.getBlockMetadata(list.get(0).xCoord,
			list.get(0).yCoord, list.get(0).zCoord);

		world.setBlockToAir(list.get(0).xCoord, list.get(0).yCoord,
			list.get(0).zCoord);

		ItemStack stack = new ItemStack(Block.wood, 1, meta);
		this.theAnt.dropItemStack(stack);

		// Environment.addItemStackToInventory(new ItemStack(Block.wood,
		// meta, 1), this.theAnt.inventory, 64, null);

	    } else {

		this.theAnt.setShouldGoTo(true);
		this.theAnt.setHasGoneTo(false);
		this.theAnt.setGoToX((int) this.theAnt.getHomeX());
		this.theAnt.setGoToY((int) this.theAnt.getHomeY());
		this.theAnt.setGoToZ((int) this.theAnt.getHomeZ());

	    }

	} else {

	    ArrayList<BlockEntry> list = Environment.getBlocksInRadius(world,
		    (int) this.theAnt.getPosX(), (int) this.theAnt.getPosY(),
		    (int) this.theAnt.getPosZ(), 10, Block.wood.blockID);

	    if (list != null) {

		if (list.size() > 0) {
		    /*
		     * int[] array = Environment.getBlocksFrom("y", 10,
		     * this.world, list.get(0).xCoord, list.get(0).yCoord,
		     * list.get(0).zCoord);
		     * 
		     * int yCoord = 0;
		     * 
		     * for(int k = 1; k < array.length; k++){
		     * 
		     * if(array[k] != Block.wood.blockID){
		     * 
		     * yCoord = list.get(0).yCoord - k;
		     * 
		     * }
		     * 
		     * }
		     */

		    this.theAnt.setShouldGoTo(true);
		    this.theAnt.setHasGoneTo(false);
		    this.theAnt.setGoToX(list.get(0).xCoord);
		    this.theAnt.setGoToY(list.get(0).yCoord);
		    this.theAnt.setGoToZ(list.get(0).zCoord);

		}

	    }

	}

    }

    @Override
    public void updateTask() {

	this.startExecuting();

    }

}
