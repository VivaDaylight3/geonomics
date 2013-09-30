package vivadaylight3.myrmecology.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;

public class EntityAnt extends EntityCreature implements IEntityAnt {

    public EntityAnt(World par1World) {
	super(par1World);
    }

    @Override
    public ItemAnt getAnt() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean canPerformBehaviour() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void startPerformingBehaviour() {
	// TODO Auto-generated method stub

    }

    @Override
    public void updateBehaviour() {
	// TODO Auto-generated method stub

    }

    @Override
    public void resetBehaviour() {
	// TODO Auto-generated method stub

    }

}
