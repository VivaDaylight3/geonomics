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
import vivadaylight3.myrmecology.api.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;

public class EntityAnt extends EntityCreature implements IEntityAnt {

    public EntityAnt(World par1World) {
	super(par1World);
	this.setSize(1.0F, 1.0F);
	this.getNavigator().setAvoidsWater(true);
    this.tasks.addTask(2, new EntityAISwimming(this));
    this.tasks.addTask(3, new EntityAIWander(this, 0.1D));
    this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(5, new EntityAILookIdle(this));
    }
    
    @Override
    public void moveEntityTo(int x, int y, int z){
	
	this.getNavigator().tryMoveToXYZ(x, y, z, 0.3D);
	
    }

    public ItemAnt getAnt() {
	return Register.antForest;
    }
    
    @Override
    public double getPosX(){
	
	return this.posX;
	
    }
    
    @Override
    public double getPosY(){
	
	return this.posY;
	
    }
    
    @Override
    public double getPosZ(){
	
	return this.posZ;
	
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

}
