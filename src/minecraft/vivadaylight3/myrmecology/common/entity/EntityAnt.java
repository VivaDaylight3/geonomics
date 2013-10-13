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

    public static ItemAnt getAnt() {
	return Register.antForest;
    }

    @Override
    protected String getLivingSound() {
	return "ant." + this.getAnt().getSpeciesSubName() + ".say";
    }

    @Override
    protected String getHurtSound() {
	return "ant." + this.getAnt().getSpeciesSubName() + ".hurt";
    }

    @Override
    protected String getDeathSound() {
	return "ant." + this.getAnt().getSpeciesSubName() + ".death";
    }

    @Override
    public boolean canPerformBehaviour() {
	System.out.println("canPerformBehaviour");	
	
	return true;
    }

    @Override
    public void startPerformingBehaviour() {

	System.out.println("startPerformingBehaviour");
	
    }

    @Override
    public void updateBehaviour() {
	
	System.out.println("updateBehaviour");

    }

    @Override
    public void resetBehaviour() {

	System.out.println("resetBehaviour");
	
    }

}
