package vivadaylight3.myrmecology.common.tileentity;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.common.container.ContainerAntFarm;
import vivadaylight3.myrmecology.common.lib.Ants;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Properties;
import vivadaylight3.myrmecology.common.lib.Register;

public class TileEntityIncubator extends TileEntity {
    
    private ItemStack[] contents = new ItemStack[this.getSizeInventory()];
    
    private String field_94045_s;
    
    public final Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();
    
    public int numPlayersUsing;
    
    private int lifetimeComplete = 0;
    
    private int lifetimeTotal = 0;
    
    private boolean isBreeding = false;
    
    private int stackLimit;
    
    Ants ants = new Ants();
    
    public int getSizeInventory() {
	return 19;
    }
    
    public boolean isBreeding() {
	return this.isBreeding;
    }
    
    @Override
    public void updateEntity() {
	
    }
    
    public void setIsBreeding(boolean bool) {
	
	this.isBreeding = bool;
	
    }
    
    public int getMaxStackSize() {
	
	return this.getInventoryStackLimit();
	
    }
    
    /**
     * Returns an integer between 0 and the passed value representing how much
     * burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means
     * that the item is fresh
     */
    public int getRemainingLifetime() {
	
	if (this.lifetimeTotal == 0) {
	    
	    return 0;
	    
	}
	
	return this.lifetimeTotal - this.lifetimeComplete;
	
    }
    
    public ItemStack getStackInSlot(int par1) {
	return this.contents[par1];
    }
    
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
    
    public ItemStack getStackInSlotOnClosing(int par1) {
	if (this.contents[par1] != null) {
	    ItemStack itemstack = this.contents[par1];
	    this.contents[par1] = null;
	    return itemstack;
	} else {
	    return null;
	}
    }
    
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
	this.contents[par1] = par2ItemStack;
	
	if (par2ItemStack != null
		&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
	    par2ItemStack.stackSize = this.getInventoryStackLimit();
	}
	
	this.onInventoryChanged();
    }
    
    public String getInvName() {
	return "Ant Farm";
    }
    
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
	
	this.lifetimeComplete = par1NBTTagCompound.getInteger("TimeComplete");
	
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
    
    public int getInventoryStackLimit() {
	return 64;
    }
    
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
	return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
		this.zCoord) != this ? false
		: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,
			this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }
    
    private int getDroneSlot() {
	return 16;
    }
    
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
	if (par1 == 1) {
	    this.numPlayersUsing = par2;
	    return true;
	} else {
	    return super.receiveClientEvent(par1, par2);
	}
    }
    
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
    
}
