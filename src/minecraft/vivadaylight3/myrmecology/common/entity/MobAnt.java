package vivadaylight3.myrmecology.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;

public class MobAnt extends EntityTameable {

    private float moveSpeed;

    public MobAnt(World par1World) {
	super(par1World);
	this.moveSpeed = 0.25F;
	this.tasks.addTask(0, new EntityAISwimming(this));
	this.tasks.addTask(1, new EntityAIFollowOwner(this, field_110152_bk,
		40, 2));
	this.tasks.addTask(4, new EntityAILeapAtTarget(this, moveSpeed));
	this.tasks.addTask(7, new EntityAIMoveThroughVillage(this, moveSpeed,
		true));
	this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this,
		field_110152_bk, moveSpeed));
	this.tasks.addTask(2, new EntityAIOwnerHurtByTarget(this));
	this.tasks.addTask(3, new EntityAIOwnerHurtTarget(this));
	this.tasks.addTask(6, new EntityAISit(this));
	this.tasks.addTask(5, new EntityAIWatchClosest(this,
		EntityPlayer.class, moveSpeed));
    }

    @Override
    public void onLivingUpdate() {
	
	this.performBehaviour(worldObj, this.posX, this.posY, this.posZ);
	
	if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
	    float var1 = this.getBrightness(1.0F);

	    if (var1 > 0.5F
		    && this.worldObj.canBlockSeeTheSky(
			    MathHelper.floor_double(this.posX),
			    MathHelper.floor_double(this.posY),
			    MathHelper.floor_double(this.posZ))
		    && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
		
		if(this.burnsFromSun()){
		
		    this.setFire(8);
		
		}
	    }
	}

	super.onLivingUpdate();
    }

    private boolean burnsFromSun() {
	return false;
    }

    @Override
    protected int getDropItemId() {
	return -1;
    }

    @Override
    protected void dropRareDrop(int par1) {
	// this.dropItem(Item.cake.itemID, 1);
    }

    @Override
    protected String getLivingSound() {
	return "mob.zombie.say";
    }

    @Override
    protected String getHurtSound() {
	return "mob.zombie.hurt";
    }

    @Override
    protected String getDeathSound() {
	return "mob.zombie.death";
    }

    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4) {
	this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
	return null;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
	return EnumCreatureAttribute.ARTHROPOD;
    }

    public int getAttackStrength(Entity par1Entity) {
	return 4;
    }

    @Override
    protected boolean isAIEnabled() {
	return true;
    }

    public String getTexture() {

	return "/Tutorial/Tutorialmob.png";

    }

    @Override
    public int getTotalArmorValue() {

	return 2;

    }
    
    public ItemAnt getAnt(){
	
	return Register.antForest;
	
    }
    
    public void performBehaviour(World world, double x, double y, double z){
	
	
	
    }

}
