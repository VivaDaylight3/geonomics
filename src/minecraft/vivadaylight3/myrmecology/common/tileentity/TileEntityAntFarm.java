package vivadaylight3.myrmecology.common.tileentity;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.AntProperties;
import vivadaylight3.myrmecology.api.Breeding;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.Metadata;
import vivadaylight3.myrmecology.common.inventory.ContainerAntFarm;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Time;
import vivadaylight3.myrmecology.common.lib.Variables;

public class TileEntityAntFarm extends TileEntity implements IInventory {

    private ItemStack[] contents = new ItemStack[this.getSizeInventory()];

    private String field_94045_s;

    public final Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();

    public int numPlayersUsing;

    public boolean isBreeding = false;

    private int stackLimit;

    private int fertility;

    public ItemStack[] getContents() {

	return this.contents;

    }

    @Override
    public int getSizeInventory() {

	return ContainerAntFarm.getInventorySize();

    }

    /**
     * Gets the drone in the drone slot
     * 
     * @return ItemStack
     */
    private ItemStack getDrone() {

	if (this.getContents()[getDroneSlot()] != null) {

	    if (this.getContents()[getDroneSlot()].getItem() instanceof ItemAnt
		    && this.getContents()[getDroneSlot()].getItemDamage() == Metadata
			    .getMetaDrone()) {

		return this.getContents()[getDroneSlot()];

	    }

	}

	return null;

    }

    /**
     * Gets the queen in the queen slot
     * 
     * @return ItemStack
     */
    private ItemStack getQueen() {

	if (this.getContents()[getQueenSlot()] != null) {

	    if (this.getContents()[getQueenSlot()].getItem() instanceof ItemAnt
		    && this.getContents()[getQueenSlot()].getItemDamage() == Metadata
			    .getMetaQueen()) {

		return this.getContents()[getQueenSlot()];

	    }

	}

	return null;

    }

    /**
     * Checks whether or not the ant can eat the food in the food slots
     * 
     * @param ItemAnt
     * @return boolean
     */
    /*
     * private boolean antCanEat(ItemAnt ant) {
     * 
     * for (int k = getFoodSlots()[0]; k <= getFoodSlots()[1]; k++) {
     * 
     * if (ant.eatsSweet()) {
     * 
     * System.out.println("Ant eats sweet");
     * 
     * for (int i = 0; i < AntProperties.getFoodSweet().length; k++) {
     * 
     * System.out.println("Looped Food: "+AntProperties.getFoodSweet()[i]);
     * System.out.println("Contents: "+getContents()[k]);
     * 
     * if ((AntProperties.getFoodSweet()[i] == this.getContents()[k]
     * .getItem().itemID)) {
     * 
     * return true;
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * if (ant.eatsSavoury()) {
     * 
     * System.out.println("Ant eats savoury");
     * 
     * for (int i = 0; i < AntProperties.getFoodSavoury().length; k++) {
     * 
     * if ((AntProperties.getFoodSavoury()[i] == this
     * .getContents()[k].getItem().itemID)) {
     * 
     * return true;
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * if (ant.eatsMeat()) {
     * 
     * System.out.println("Ant eats meat");
     * 
     * for (int i = 0; i < AntProperties.getFoodMeat().length; k++) {
     * 
     * if ((AntProperties.getFoodMeat()[i] == this.getContents()[k]
     * .getItem().itemID)) {
     * 
     * return true;
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * if (ant.eatsLarvae()) {
     * 
     * System.out.println("Ant eats larvae");
     * 
     * if (this.getContents()[k].getItemDamage() == 3) {
     * 
     * return true;
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * return false;
     * 
     * }
     */

    /**
     * Gets the breeding result from the ant farm's drone and queen
     * 
     * @return ItemStack
     */
    private ItemStack getBreedingResult() {

	return Breeding.getBreedingResult((ItemAnt) this.getQueen().getItem(),
		(ItemAnt) this.getDrone().getItem());

    }

    private boolean biomeCheck() {

	if (((ItemAnt) this.getQueen().getItem()).getAntBiomes() != null) {

	    for (int k = 0; k < ((ItemAnt) this.getQueen().getItem())
		    .getAntBiomes().length; k++) {

		if (Environment.getBiome(this.worldObj, this.xCoord,
			this.zCoord) == ((ItemAnt) this.getQueen().getItem())
			.getAntBiomes()[k]) {

		    return true;

		}

	    }

	    return false;

	}

	return true;

    }

    private boolean timeCheck() {

	if (((ItemAnt) this.getQueen().getItem()).getNocturnal()) {

	    return true;

	} else if (Time.toString(Time.getWorldTime(this.worldObj)) == "day") {

	    return true;

	}

	return false;

    }

    private boolean weatherCheck() {

	if (((ItemAnt) this.getQueen().getItem()).getWinged()) {

	    return true;

	} else if (!Environment.isRaining(this.worldObj)
		&& !Environment.isThundering(this.worldObj)) {

	    return true;

	}

	return false;

    }

    public boolean canBreed() {

	if (this.biomeCheck()) {

	    if (this.timeCheck()) {

		if (this.weatherCheck()) {

		    return true;

		}

	    }

	}

	return false;

    }

    // TODO
    @Override
    public void updateEntity() {

	if (this.getQueen() != null) {

	    if (AntProperties.getMated(getQueen())) {

		if (this.canBreed()) {

		    if (this.getLifetimeComplete() < this.getLifetimeTotal()) {

			this.increaseLifetimeComplete();

		    } else if (this.getLifetimeComplete() >= this
			    .getLifetimeTotal()) {

			this.finishBreeding();

		    }

		}

	    } else {

		if (this.getQueen() != null && this.getDrone() != null) {

		    if (this.getBreedingResult() != null) {

			this.fertility = ((ItemAnt) this.getQueen().getItem())
				.getFertility();

			ItemStack result = this.getBreedingResult();
			result.stackSize = 1;
			result.setItemDamage(Metadata.getMetaQueen());
			AntProperties.setProperties(result, true, 0);

			this.setInventorySlotContents(getQueenSlot(), result);
			this.decrStackSize(getDroneSlot(), 1);

		    }

		}

	    }

	}

    }

    private void finishBreeding() {

	ItemStack result = new ItemStack(this.getQueen().getItem(),
		this.fertility, Metadata.getMetaLarva());

	AntProperties.setProperties(result, false, 0);

	// Environment.addItemStackToInventory(result, getContents(),
	// stackLimit, this);

	this.getContents()[7] = result;

	this.decrStackSize(getQueenSlot(), 1);

    }

    private void increaseLifetimeComplete() {

	AntProperties.setLifetimeComplete(this.getQueen(),
		getLifetimeComplete() + 1);

    }

    private void clearLifetimeComplete() {

	AntProperties
		.setLifetimeComplete(this.getContents()[getQueenSlot()], 0);

    }

    private int getLifetimeComplete() {

	return AntProperties.getLifetimeComplete(this.getQueen());

    }

    private int getLifetimeTotal() {

	// return ((ItemAnt) this.getQueen().getItem()).getLifetime();
	return 100;

    }

    public int getMaxStackSize() {

	return this.getInventoryStackLimit();

    }

    @Override
    public ItemStack getStackInSlot(int par1) {
	return this.contents[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
	if (this.contents[par1] != null) {
	    ItemStack itemstack;

	    if (this.contents[par1].stackSize <= par2) {
		itemstack = this.contents[par1];
		this.contents[par1] = null;
		this.onInventoryChanged();
		return itemstack;
	    } else {
		itemstack = this.contents[par1].splitStack(par2);

		if (this.contents[par1].stackSize == 0) {
		    this.contents[par1] = null;
		}

		this.onInventoryChanged();
		return itemstack;
	    }
	} else {
	    return null;
	}
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
	if (this.contents[par1] != null) {
	    ItemStack itemstack = this.contents[par1];
	    this.contents[par1] = null;
	    return itemstack;
	} else {
	    return null;
	}
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
	this.contents[par1] = par2ItemStack;

	if (par2ItemStack != null
		&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
	    par2ItemStack.stackSize = this.getInventoryStackLimit();
	}

	this.onInventoryChanged();
    }

    @Override
    public String getInvName() {
	return "Ant Farm";
    }

    @Override
    public boolean isInvNameLocalized() {
	return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
	super.readFromNBT(par1NBTTagCompound);
	NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	this.contents = new ItemStack[this.getSizeInventory()];

	if (par1NBTTagCompound.hasKey("CustomName")) {
	    this.field_94045_s = par1NBTTagCompound.getString("CustomName");
	}

	for (int i = 0; i < nbttaglist.tagCount(); ++i) {
	    NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
		    .tagAt(i);
	    int j = nbttagcompound1.getByte("Slot") & 255;

	    if (j >= 0 && j < this.contents.length) {
		this.contents[j] = ItemStack
			.loadItemStackFromNBT(nbttagcompound1);
	    }
	}
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
	super.writeToNBT(par1NBTTagCompound);
	NBTTagList nbttaglist = new NBTTagList();

	for (int i = 0; i < this.contents.length; ++i) {
	    if (this.contents[i] != null) {
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setByte("Slot", (byte) i);
		this.contents[i].writeToNBT(nbttagcompound1);
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}

	par1NBTTagCompound.setTag("Items", nbttaglist);

	if (this.isInvNameLocalized()) {
	    par1NBTTagCompound.setString("CustomName", this.field_94045_s);
	}
    }

    @Override
    public int getInventoryStackLimit() {
	return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
	return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
		this.zCoord) != this ? false
		: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,
			this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    private static int getDroneSlot() {
	return 0;
    }

    /*
     * private static int[] getFoodSlots(){
     * 
     * int[] result = new int[] {2, 3};
     * 
     * return result;
     * 
     * }
     */

    @Override
    public boolean receiveClientEvent(int par1, int par2) {
	if (par1 == 1) {
	    this.numPlayersUsing = par2;
	    return true;
	} else {
	    return super.receiveClientEvent(par1, par2);
	}
    }

    @Override
    public void openChest() {

	if (numPlayersUsing < 0) {

	    numPlayersUsing = 0;

	}

	++this.numPlayersUsing;

	this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
		this.getBlockType().blockID, 1, this.numPlayersUsing);
	this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord,
		this.zCoord, this.getBlockType().blockID);
	this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
		this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
    }

    @Override
    public void closeChest() {
	if (this.getBlockType() != null
		&& this.getBlockType() instanceof BlockChest) {
	    --this.numPlayersUsing;
	    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
		    this.getBlockType().blockID, 1, this.numPlayersUsing);
	    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
		    this.yCoord, this.zCoord, this.getBlockType().blockID);
	    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
		    this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
	}
    }

    public int getQueenSlot() {

	return 1;

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
	// TODO Auto-generated method stub
	return true;
    }

}
