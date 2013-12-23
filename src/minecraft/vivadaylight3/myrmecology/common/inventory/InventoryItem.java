package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.lib.Nbt;

public class InventoryItem implements IInventory {

    public ItemStack[] inventory = new ItemStack[1];
    private ItemStack stack;
    public static String invName = "Myrmopedia";

    public InventoryItem(ItemStack itemStack) {

	Nbt.setTag(itemStack);
	stack = itemStack;
	readFromNBT(itemStack.getTagCompound());

    }

    @Override
    public int getSizeInventory() {
	// TODO Auto-generated method stub
	return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
	return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

	ItemStack stack = getStackInSlot(slot);

	if (stack != null) {

	    if (stack.stackSize > amount) {

		stack = stack.splitStack(amount);

		if (stack.stackSize == 0) {

		    setInventorySlotContents(slot, null);

		}

	    } else {

		setInventorySlotContents(slot, null);

	    }

	    this.onInventoryChanged();

	}

	return stack;

    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

	return this.getStackInSlot(i);

    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {

	this.inventory[slot] = itemstack;

	if (itemstack != null
		&& itemstack.stackSize > this.getInventoryStackLimit()) {
	    itemstack.stackSize = this.getInventoryStackLimit();
	}

	this.onInventoryChanged();

    }

    @Override
    public String getInvName() {

	return invName;

    }

    @Override
    public boolean isInvNameLocalized() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int getInventoryStackLimit() {

	return 1;

    }

    @Override
    public void onInventoryChanged() {

	for (int i = 0; i < this.getSizeInventory(); ++i) {
	    if (this.getStackInSlot(i) != null
		    && this.getStackInSlot(i).stackSize == 0)
		this.setInventorySlotContents(i, null);
	}

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public void openChest() {
	// TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

	// return itemstack.getItem() instanceof ItemAnt;

	return true;

    }

    public void readFromNBT(NBTTagCompound tagcompound) {

	NBTTagList nbttaglist = tagcompound.getTagList("ItemInventory");

	for (int i = 0; i < nbttaglist.tagCount(); ++i) {
	    NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
		    .tagAt(i);
	    int b0 = nbttagcompound1.getInteger("Slot");

	    // Just double-checking that the saved slot index is within our
	    // inventory array bounds
	    if (b0 >= 0 && b0 < this.getSizeInventory()) {
		this.setInventorySlotContents(b0,
			ItemStack.loadItemStackFromNBT(nbttagcompound1));
	    }
	}

    }

    public void update() {

	this.writeToNBT(stack.stackTagCompound);
	this.readFromNBT(stack.stackTagCompound);

    }

    public void writeToNBT(NBTTagCompound tagcompound) {
	// Create a new NBT Tag List to store itemstacks as NBT Tags
	NBTTagList nbttaglist = new NBTTagList();

	for (int i = 0; i < this.getSizeInventory(); ++i) {
	    // Only write stacks that contain items
	    if (this.getStackInSlot(i) != null) {
		// Make a new NBT Tag Compound to write the itemstack and slot
		// index to
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setInteger("Slot", i);
		// Writes the itemstack in slot(i) to the Tag Compound we just
		// made
		this.getStackInSlot(i).writeToNBT(nbttagcompound1);

		// add the tag compound to our tag list
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}
	// Add the TagList to the ItemStack's Tag Compound with the name
	// "ItemInventory"
	tagcompound.setTag("ItemInventory", nbttaglist);
    }

}
