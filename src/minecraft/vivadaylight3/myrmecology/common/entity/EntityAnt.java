package vivadaylight3.myrmecology.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;

public class EntityAnt extends EntityCreature implements IEntityAnt {

    int gotoX = 0;
    int gotoY = 0;
    int gotoZ = 0;

    int homeX;
    int homeY;
    int homeZ;

    boolean shouldGoTo = false;
    boolean hasGoneTo = false;

    public TileEntity homeTileEntity;

    public String behaviourErrorMessage = "Hello sir";

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

	this.homeTileEntity = par1World.getBlockTileEntity((int) this.posX,
		(int) this.posY - 1, (int) this.posZ);

    }

    public void dropInventory() {

	if (this.inventory[0] != null) {

	    this.dropItemStack(this.inventory[0]);
	    this.inventory[0] = null;

	}

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
    public void moveEntityTo(double x, double y, double z) {

	this.getNavigator().tryMoveToXYZ(x, y, z, 0.2D);

    }

    @Override
    public ItemAnt getAnt() {
	return null;
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
	return "";
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
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {

	par1NBTTagCompound.setFloat("HealF", this.getHealth());
	par1NBTTagCompound.setShort("Health",
		(short) ((int) Math.ceil((double) this.getHealth())));
	par1NBTTagCompound.setShort("HurtTime", (short) this.hurtTime);
	par1NBTTagCompound.setShort("DeathTime", (short) this.deathTime);
	par1NBTTagCompound.setShort("AttackTime", (short) this.attackTime);
	par1NBTTagCompound.setFloat("AbsorptionAmount",
		this.getAbsorptionAmount());
	ItemStack[] aitemstack = this.getLastActiveItems();
	int i = aitemstack.length;
	int j;
	ItemStack itemstack;

	for (j = 0; j < i; ++j) {
	    itemstack = aitemstack[j];
	}

	par1NBTTagCompound.setTag("Attributes",
		SharedMonsterAttributes.func_111257_a(this.getAttributeMap()));
	aitemstack = this.getLastActiveItems();
	i = aitemstack.length;

	for (j = 0; j < i; ++j) {
	    itemstack = aitemstack[j];
	}
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
	this.setAbsorptionAmount(par1NBTTagCompound
		.getFloat("AbsorptionAmount"));

	if (par1NBTTagCompound.hasKey("Attributes") && this.worldObj != null
		&& !this.worldObj.isRemote) {
	    SharedMonsterAttributes.func_111260_a(
		    this.getAttributeMap(),
		    par1NBTTagCompound.getTagList("Attributes"),
		    this.worldObj == null ? null : this.worldObj
			    .getWorldLogAgent());
	}

	if (par1NBTTagCompound.hasKey("ActiveEffects")) {
	    NBTTagList nbttaglist = par1NBTTagCompound
		    .getTagList("ActiveEffects");

	    for (int i = 0; i < nbttaglist.tagCount(); ++i) {
		NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
			.tagAt(i);
		PotionEffect potioneffect = PotionEffect
			.readCustomPotionEffectFromNBT(nbttagcompound1);
	    }
	}

	if (par1NBTTagCompound.hasKey("HealF")) {
	    this.setHealth(par1NBTTagCompound.getFloat("HealF"));
	} else {
	    NBTBase nbtbase = par1NBTTagCompound.getTag("Health");

	    if (nbtbase == null) {
		this.setHealth(this.getMaxHealth());
	    } else if (nbtbase.getId() == 5) {
		this.setHealth(((NBTTagFloat) nbtbase).data);
	    } else if (nbtbase.getId() == 2) {
		this.setHealth((float) ((NBTTagShort) nbtbase).data);
	    }
	}

	this.hurtTime = par1NBTTagCompound.getShort("HurtTime");
	this.deathTime = par1NBTTagCompound.getShort("DeathTime");
	this.attackTime = par1NBTTagCompound.getShort("AttackTime");
    }

    @Override
    public void sendBehaviourErrorMessage(EntityPlayer player) {

	if (!this.worldObj.isRemote) {

	    if (behaviourErrorMessage != null) {

		player.addChatMessage(behaviourErrorMessage);

	    } else {

		player.addChatMessage("Don't mind me sir, I'm just minding my own business");

	    }

	}

    }

}
