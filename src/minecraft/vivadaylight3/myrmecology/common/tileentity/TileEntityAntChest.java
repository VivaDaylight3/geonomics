package vivadaylight3.myrmecology.common.tileentity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.handler.PacketHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityAntChest extends TileEntity implements IInventory {
    private int ticksSinceSync = -1;
    public float prevLidAngle;
    public float lidAngle;
    private int numUsingPlayers;
    public ItemStack[] chestContents = new ItemStack[36];
    private ItemStack[] topStacks;
    private byte facing;
    private boolean inventoryTouched;
    private boolean hadStuff;

    public TileEntityAntChest() {
    }

    public ItemStack[] getContents() {
	return chestContents;
    }

    @Override
    public int getSizeInventory() {
	return getContents().length;
    }

    public byte getFacing() {
	return this.facing;
    }

    @Override
    public String getInvName() {
	return "Scavenging Chest";
    }

    @Override
    public ItemStack getStackInSlot(int i) {
	inventoryTouched = true;
	return chestContents[i];
    }

    @Override
    public void onInventoryChanged() {
	super.onInventoryChanged();
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
	if (chestContents[i] != null) {
	    if (chestContents[i].stackSize <= j) {
		ItemStack itemstack = chestContents[i];
		chestContents[i] = null;
		onInventoryChanged();
		return itemstack;
	    }
	    ItemStack itemstack1 = chestContents[i].splitStack(j);
	    if (chestContents[i].stackSize == 0) {
		chestContents[i] = null;
	    }
	    onInventoryChanged();
	    return itemstack1;
	} else {
	    return null;
	}
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
	chestContents[i] = itemstack;
	if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
	    itemstack.stackSize = getInventoryStackLimit();
	}
	onInventoryChanged();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
	super.readFromNBT(nbttagcompound);
	NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
	chestContents = new ItemStack[getSizeInventory()];
	for (int i = 0; i < nbttaglist.tagCount(); i++) {
	    NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
		    .tagAt(i);
	    int j = nbttagcompound1.getByte("Slot") & 0xff;
	    if (j >= 0 && j < chestContents.length) {
		chestContents[j] = ItemStack
			.loadItemStackFromNBT(nbttagcompound1);
	    }
	}
	facing = nbttagcompound.getByte("facing");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
	super.writeToNBT(nbttagcompound);
	NBTTagList nbttaglist = new NBTTagList();
	for (int i = 0; i < chestContents.length; i++) {
	    if (chestContents[i] != null) {
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setByte("Slot", (byte) i);
		chestContents[i].writeToNBT(nbttagcompound1);
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}

	nbttagcompound.setTag("Items", nbttaglist);
	nbttagcompound.setByte("facing", facing);
    }

    @Override
    public int getInventoryStackLimit() {
	return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
	if (worldObj == null) {
	    return true;
	}
	if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
	    return false;
	}
	return entityplayer.getDistanceSq((double) xCoord + 0.5D,
		(double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
    }

    @Override
    public void updateEntity() {
	super.updateEntity();
	// Resynchronize clients with the server state
	if (worldObj != null
		&& !this.worldObj.isRemote
		&& this.numUsingPlayers != 0
		&& (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
	    this.numUsingPlayers = 0;
	    float var1 = 5.0F;
	    List var2 = this.worldObj.getEntitiesWithinAABB(
		    EntityPlayer.class,
		    AxisAlignedBB.getAABBPool().getAABB(
			    (double) ((float) this.xCoord - var1),
			    (double) ((float) this.yCoord - var1),
			    (double) ((float) this.zCoord - var1),
			    (double) ((float) (this.xCoord + 1) + var1),
			    (double) ((float) (this.yCoord + 1) + var1),
			    (double) ((float) (this.zCoord + 1) + var1)));
	    Iterator var3 = var2.iterator();

	    while (var3.hasNext()) {
		EntityPlayer var4 = (EntityPlayer) var3.next();

		++this.numUsingPlayers;
	    }
	}

	if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0) {
	    worldObj.addBlockEvent(xCoord, yCoord, zCoord,
		    Register.blockAntChest.blockID, 3,
		    ((numUsingPlayers << 3) & 0xF8) | (facing & 0x7));
	}
	if (!worldObj.isRemote && inventoryTouched) {
	    inventoryTouched = false;
	}

	this.ticksSinceSync++;
	prevLidAngle = lidAngle;
	float f = 0.1F;
	if (numUsingPlayers > 0 && lidAngle == 0.0F) {
	    double d = (double) xCoord + 0.5D;
	    double d1 = (double) zCoord + 0.5D;
	    worldObj.playSoundEffect(d, (double) yCoord + 0.5D, d1,
		    "random.chestopen", 0.5F,
		    worldObj.rand.nextFloat() * 0.1F + 0.9F);
	}
	if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0
		&& lidAngle < 1.0F) {
	    float f1 = lidAngle;
	    if (numUsingPlayers > 0) {
		lidAngle += f;
	    } else {
		lidAngle -= f;
	    }
	    if (lidAngle > 1.0F) {
		lidAngle = 1.0F;
	    }
	    float f2 = 0.5F;
	    if (lidAngle < f2 && f1 >= f2) {
		double d2 = (double) xCoord + 0.5D;
		double d3 = (double) zCoord + 0.5D;
		worldObj.playSoundEffect(d2, (double) yCoord + 0.5D, d3,
			"random.chestclosed", 0.5F,
			worldObj.rand.nextFloat() * 0.1F + 0.9F);
	    }
	    if (lidAngle < 0.0F) {
		lidAngle = 0.0F;
	    }
	}
    }

    @Override
    public boolean receiveClientEvent(int i, int j) {
	if (i == 1) {
	    numUsingPlayers = j;
	} else if (i == 2) {
	    facing = (byte) j;
	} else if (i == 3) {
	    facing = (byte) (j & 0x7);
	    numUsingPlayers = (j & 0xF8) >> 3;
	}
	return true;
    }

    @Override
    public void openChest() {
	if (worldObj == null)
	    return;
	numUsingPlayers++;
	worldObj.addBlockEvent(xCoord, yCoord, zCoord,
		Register.blockAntChest.blockID, 1, numUsingPlayers);
    }

    @Override
    public void closeChest() {
	if (worldObj == null)
	    return;
	numUsingPlayers--;
	worldObj.addBlockEvent(xCoord, yCoord, zCoord,
		Register.blockAntChest.blockID, 1, numUsingPlayers);
    }

    public void setFacing(byte chestFacing) {
	this.facing = chestFacing;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
	if (this.chestContents[par1] != null) {
	    ItemStack var2 = this.chestContents[par1];
	    this.chestContents[par1] = null;
	    return var2;
	} else {
	    return null;
	}
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
	return true;
    }

    @Override
    public boolean isInvNameLocalized() {
	return false;
    }

    public void rotateAround(ForgeDirection axis) {
	setFacing((byte) ForgeDirection.getOrientation(facing)
		.getRotation(axis).ordinal());
	worldObj.addBlockEvent(xCoord, yCoord, zCoord,
		Register.blockAntChest.blockID, 2, getFacing());
    }
}
