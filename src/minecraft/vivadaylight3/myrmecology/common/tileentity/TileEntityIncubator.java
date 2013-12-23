package vivadaylight3.myrmecology.common.tileentity;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.util.AntProperties;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.block.BlockIncubator;
import vivadaylight3.myrmecology.common.handler.PacketHandler;
import vivadaylight3.myrmecology.common.inventory.ContainerIncubator;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.item.ItemUpgrade;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class TileEntityIncubator extends TileEntity implements IInventory,
	IPacketHandler {

    private ItemStack[] contents = new ItemStack[this.getSizeInventory()];

    private String field_94045_s;

    private static final String RESULT_ANT_META_KEY = "ResultAntMeta";

    private int resultAntMeta = -1;

    public void setResultAntMeta(int meta) {

	this.resultAntMeta = meta;

    }

    // Set by setResultAntMeta()
    public int getResultAntMeta() {

	return this.resultAntMeta;

    }

    @Override
    public Packet getDescriptionPacket() {
	return PacketHandler.getTileEntityPacket(this);
    }

    public void handlePacket(ItemStack[] stacks) {

	for (int k = 0; k < stacks.length; k++) {

	    this.contents[k] = stacks[k];

	}

    }

    @Override
    public void updateEntity() {

	if (this.canIncubate()) {

	    if (this.getMaturingTimeComplete() < this.getMaturingTime()) {

		this.increaseMaturingTime();

	    } else if (this.getMaturingTimeComplete() >= this.getMaturingTime()) {

		this.finishIncubation();

	    }

	}
    }

    private ItemStack getMaturingResult() {

	ItemStack result = new ItemStack(this.getLarva().getItem(),
		((ItemAnt) this.getLarva().getItem()).getFertility(),
		this.getResultAntMeta());
	result.setItemDamage(this.getResultAntMeta());

	return result;

    }

    public int getUpgradeSlot() {

	return 17;

    }

    private int getMaturingTime() {

	if (this.getLarva() != null) {

	    if (this.getLarva().getItem() instanceof ItemAnt) {

		int time = ((ItemAnt) this.getLarva().getItem())
			.getMaturingTime();

		if (this.getStackInSlot(this.getUpgradeSlot()) != null
			&& this.getStackInSlot(this.getUpgradeSlot()).getItem() instanceof ItemUpgrade
			&& this.getStackInSlot(this.getUpgradeSlot())
				.getItemDamage() == 0) {

		    return time / 2;

		}

		return time;

	    }

	}

	return 1;

    }

    public int getMaturingTimeComplete() {

	return AntProperties.getMaturingTimeComplete(getLarva());

    }

    private boolean canIncubate() {

	if (this.getContents()[ContainerIncubator.getFoodSlot()] != null) {

	    this.setResultAntMeta(getResultAntMetaFromInput());

	}

	if (this.getLarva() != null) {

	    if (this.getLarva().getItem() instanceof ItemAnt) {

		if (this.getResultAntMeta() != -1) {

		    return (Environment.blockIsPowered(this.worldObj,
			    this.xCoord, this.yCoord, this.zCoord) && Environment
			    .inventoryCanHold(this.getMaturingResult(),
				    this.getContents(),
				    this.getInventoryStackLimit()));

		}

	    }

	}

	return false;

    }

    private void increaseMaturingTime() {

	int complete = AntProperties.getMaturingTimeComplete(getLarva());
	AntProperties.setMaturingTime(getLarva(), complete + 1);

    }

    // turns the larvae into a mature ant
    private void finishIncubation() {

	ItemStack result = new ItemStack(this.getLarva().getItem(),
		((ItemAnt) this.getLarva().getItem()).getFertility(),
		this.getResultAntMeta());

	Environment.addItemStackToInventory(result, getContents(),
		getMaxStackSize(), this);

	this.decrStackSize(ContainerIncubator.getLarvaSlot(), 1);
	this.decrStackSize(ContainerIncubator.getFoodSlot(), 1);

	this.setResultAntMeta(-1);

    }

    private ItemStack getLarva() {

	return this.getContents()[ContainerIncubator.getLarvaSlot()];

    }

    public ItemStack[] getContents() {

	return this.contents;

    }

    public int getMaxStackSize() {
	return this.getInventoryStackLimit();
    }

    public ItemStack getStackInSlot(int index) {
	return this.contents[index];
    }

    public ItemStack decrStackSize(int index, int amount) {
	if (this.contents[index] != null) {
	    ItemStack itemstack;

	    if (this.contents[index].stackSize <= amount) {
		itemstack = this.contents[index];
		this.contents[index] = null;
		this.onInventoryChanged();
		return itemstack;
	    } else {
		itemstack = this.contents[index].splitStack(amount);

		if (this.contents[index].stackSize == 0) {
		    this.contents[index] = null;
		}

		this.onInventoryChanged();
		return itemstack;
	    }
	} else {
	    return null;
	}
    }

    public ItemStack getStackInSlotOnClosing(int index) {
	if (this.contents[index] != null) {
	    ItemStack itemstack = this.contents[index];
	    this.contents[index] = null;
	    return itemstack;
	} else {
	    return null;
	}
    }

    public void setInventorySlotContents(int index, ItemStack itemStack) {
	this.contents[index] = itemStack;

	if (itemStack != null
		&& itemStack.stackSize > this.getInventoryStackLimit()) {
	    itemStack.stackSize = this.getInventoryStackLimit();
	}

	this.onInventoryChanged();
    }

    public String getInvName() {
	return "Solarium";
    }

    public boolean isInvNameLocalized() {
	return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
	super.readFromNBT(par1NBTTagCompound);
	NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	this.contents = new ItemStack[this.getSizeInventory()];

	if (par1NBTTagCompound.hasKey(RESULT_ANT_META_KEY)) {
	    this.setResultAntMeta(par1NBTTagCompound.getInteger("CustomName"));
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

	par1NBTTagCompound.setInteger(RESULT_ANT_META_KEY,
		this.getResultAntMeta());

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

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
	return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
		this.zCoord) != this ? false
		: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,
			this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemstack) {
	return false;
    }

    @Override
    public int getSizeInventory() {
	return ContainerIncubator.getInventorySize();
    }

    @Override
    public void openChest() {
	// do nothing
    }

    @Override
    public void closeChest() {
	// do nothing
    }

    @Override
    public int getInventoryStackLimit() {
	return ContainerIncubator.stackLimit;
    }

    public int getResultAntMetaFromInput() {

	Item input = this.getContents()[ContainerIncubator.getFoodSlot()]
		.getItem();

	for (int k = 0; k < Metadata.breedingItems.length; k++) {

	    if (input == Metadata.breedingItems[k]) {

		return k;

	    }

	}

	return -1;

	/*
	 * 
	 * if (input == Item.stick.itemID) {
	 * 
	 * return Metadata.getMetaDrone();
	 * 
	 * } else if (input == Register.blockFungi.blockID) {
	 * 
	 * return Metadata.getMetaWorker();
	 * 
	 * } else if (input == Item.goldNugget.itemID) {
	 * 
	 * return Metadata.getMetaQueen();
	 * 
	 * } else {
	 * 
	 * return -1;
	 * 
	 * }
	 */

    }

    @Override
    public void onPacketData(INetworkManager manager,
	    Packet250CustomPayload packet, Player player) {
	// TODO Auto-generated method stub

    }
}