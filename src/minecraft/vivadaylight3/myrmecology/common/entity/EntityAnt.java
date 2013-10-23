package vivadaylight3.myrmecology.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
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
    this.tasks.addTask(1, new EntityAISwimming(this));
    this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
    this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
    this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(5, new EntityAILookIdle(this));
	this.tasks.addTask(6, new EntityAIAntBehaviour(this, par1World, this.getNavigator()));
    }

    public ItemAnt getAnt() {
	return Register.antForest;
    }
    
    @Override
    public boolean isAIEnabled() { return true; }

    public ResourceLocation getResource() {

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
