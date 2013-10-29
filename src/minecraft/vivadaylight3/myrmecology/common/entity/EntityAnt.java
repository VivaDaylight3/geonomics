package vivadaylight3.myrmecology.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Maths;

public class EntityAnt extends EntityCreature implements IEntityAnt {

    int gotoX = 0;
    int gotoY = 0;
    int gotoZ = 0;

    int homeX;
    int homeY;
    int homeZ;

    boolean shouldGoTo = false;
    boolean hasGoneTo = false;

    public int ticksPassed;

    public EntityAnt(World par1World) {
	super(par1World);
	this.setSize(0.5F, 0.5F);
	this.getNavigator().setAvoidsWater(true);
	this.tasks.addTask(3, new EntityAISwimming(this));
	this.tasks.addTask(4, new EntityAIWander(this, 0.1D));
	this.tasks.addTask(5, new EntityAIWatchClosest(this,
		EntityPlayer.class, 8.0F));
	this.tasks.addTask(6, new EntityAILookIdle(this));

	this.setHomeX((int) getPosX());
	this.setHomeY((int) getPosY());
	this.setHomeZ((int) getPosZ());

    }

    @Override
    public void dropItemStack(ItemStack stack) {

	this.entityDropItem(stack, 0.0F);

    }

    @Override
    public void onUpdate() {

	super.onUpdate();

	ticksPassed++;

	if (ticksPassed > 20) {

	    ticksPassed = 0;

	}

    }

    @Override
    public int getTicks() {

	return ticksPassed;

    }

    @Override
    public void moveEntityTo(int x, int y, int z) {

	this.getNavigator().tryMoveToXYZ(x, y, z, 0.3D);

    }

    public ItemAnt getAnt() {
	return Register.antForest;
    }

    @Override
    public double getPosX() {

	return this.posX;

    }

    @Override
    public double getPosY() {

	return this.posY;

    }

    @Override
    public double getPosZ() {

	return this.posZ;

    }

    @Override
    public boolean isAIEnabled() {
	return true;
    }

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
    public int getGoToX() {
	// TODO Auto-generated method stub
	return gotoX;
    }

    @Override
    public int getGoToY() {
	// TODO Auto-generated method stub
	return gotoY;
    }

    @Override
    public int getGoToZ() {
	// TODO Auto-generated method stub
	return gotoZ;
    }

    @Override
    public boolean getShouldGoTo() {
	// TODO Auto-generated method stub
	return shouldGoTo;
    }

    @Override
    public boolean getHasGoneTo() {
	// TODO Auto-generated method stub
	return hasGoneTo;
    }

    @Override
    public void setShouldGoTo(boolean bool) {

	shouldGoTo = bool;

    }

    @Override
    public void setHasGoneTo(boolean bool) {

	hasGoneTo = bool;

    }

    @Override
    public void setGoToX(int x) {

	gotoX = x;

    }

    @Override
    public void setGoToY(int y) {

	gotoY = y;

    }

    @Override
    public void setGoToZ(int z) {

	gotoZ = z;

    }

    @Override
    public double getHomeX() {
	// TODO Auto-generated method stub
	return homeX;
    }

    @Override
    public double getHomeY() {
	// TODO Auto-generated method stub
	return homeY;
    }

    @Override
    public double getHomeZ() {
	// TODO Auto-generated method stub
	return homeZ;
    }

    @Override
    public void setHomeX(int x) {

	homeX = x;

    }

    @Override
    public void setHomeY(int y) {

	homeY = y;

    }

    @Override
    public void setHomeZ(int z) {

	homeZ = z;

    }

}
