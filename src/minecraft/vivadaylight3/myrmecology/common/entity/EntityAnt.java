package vivadaylight3.myrmecology.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.ai.EntityAIAntBehaviour;

public class EntityAnt extends EntityCreature implements IEntityAnt {
    
    public EntityAnt(World par1World) {
	super(par1World);
	this.setSize(1.0F, 1.0F);
	this.getNavigator().setAvoidsWater(true);
	this.tasks.addTask(0, new EntityAIAntBehaviour(this, par1World, this.getNavigator()));
    }

    public ItemAnt getAnt() {
	return Register.antForest;
    }
    
    public ResourceLocation getResource(){
	
	return null;
	
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
