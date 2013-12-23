package vivadaylight3.myrmecology.common.inventory;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Nbt;

public class ContainerMyrmopaedia extends Container {

    public InventoryItem inventory;

    public ItemStack containerStack;

    private int slotID;

    public static int numRows = 3;
    public static int numColumns = 1;

    public int slotOffsetX = 10;
    public int slotOffsetY = 90;

    public final int antSlotX = (8 + 0 * 18) + slotOffsetX;
    public int antSlotY = (84 + 0 * 18) + slotOffsetY + (0 - 27);

    // public static int slotOffsetX = -2;//24;
    // public static int slotOffsetY = 47;//74;

    private ArrayList<ItemAnt> registerAntList = new ArrayList<ItemAnt>();

    public ContainerMyrmopaedia(InventoryItem inventoryItem, EntityPlayer player) {

	this.inventory = inventoryItem;
	this.containerStack = player.getHeldItem();

	Nbt.setTag(this.containerStack);

	int slotID = 0;

	/*
	 * 
	 * Object[] ants = Register.getAntList().toArray();
	 * 
	 * int[] antIDs = new int[ants.length];
	 * 
	 * for (int k = 0; k < ants.length; k++) {
	 * 
	 * antIDs[k] = ((ItemAnt) ants[k]).getAntID();
	 * 
	 * }
	 */

	addSlotToContainer(new SlotInventoryItem(this.inventory, slotID,
		(8 + 0 * 18) + slotOffsetX, (84 + 0 * 18) + slotOffsetY
			+ (0 - 27)));

	/*
	 * for (int i = 0; i < numRows; i++) { for (int j = 0; j < numColumns;
	 * j++) {
	 * 
	 * addSlotToContainer(new SlotInventoryItem(this.inventory, antIDs,
	 * slotID, 62 + j * 18, 17 + i * 18)); slotID++;
	 * 
	 * } }
	 */

	bindPlayerInventory(player.inventory);
    }

    public static int getInventorySize() {

	return 1;

    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 9; j++) {
		addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
			(8 + j * 18) + slotOffsetX, (84 + i * 18) + slotOffsetY
				+ (i - 5)));
	    }
	}

	for (int i = 0; i < 9; i++) {
	    addSlotToContainer(new Slot(inventoryPlayer, i, (8 + i * 18)
		    + slotOffsetX, 142 + (slotOffsetY - 1)));
	}
    }

    @Override
    public void detectAndSendChanges() {

	for (int i = 0; i < this.inventorySlots.size(); ++i) {
	    ItemStack itemstack = ((Slot) this.inventorySlots.get(i))
		    .getStack();
	    ItemStack itemstack1 = (ItemStack) this.inventoryItemStacks.get(i);

	    if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
		itemstack1 = itemstack == null ? null : itemstack.copy();
		this.inventoryItemStacks.set(i, itemstack1);

		for (int j = 0; j < this.crafters.size(); ++j) {
		    ((ICrafting) this.crafters.get(j)).sendSlotContents(this,
			    i, itemstack1);
		}
	    }
	}
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {

	player.addChatMessage("Slot: " + slot);
	ItemStack stack = null;
	Slot slotObject = (Slot) inventorySlots.get(slot);

	// null checks and checks if the item can be stacked (maxStackSize > 1)
	if (slotObject != null && slotObject.getHasStack()) {
	    ItemStack stackInSlot = slotObject.getStack();
	    stack = stackInSlot.copy();

	    // merges the item into player inventory since its in the tileEntity
	    if (slot < 9) {
		if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
		    return null;
		}
	    }
	    // places it into the tileEntity is possible since its in the player
	    // inventory
	    else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
		return null;
	    }

	    if (stackInSlot.stackSize == 0) {
		slotObject.putStack(null);
	    } else {
		slotObject.onSlotChanged();
	    }

	    if (stackInSlot.stackSize == stack.stackSize) {
		return null;
	    }
	    slotObject.onPickupFromSlot(player, stackInSlot);
	}
	return stack;
    }

    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2,
	    int par3, boolean par4) {
	boolean flag1 = false;
	int k = par2;

	if (par4) {
	    k = par3 - 1;
	}

	Slot slot;
	ItemStack itemstack1;

	if (par1ItemStack.isStackable()) {
	    while (par1ItemStack.stackSize > 0
		    && (!par4 && k < par3 || par4 && k >= par2)) {
		slot = (Slot) this.inventorySlots.get(k);
		itemstack1 = slot.getStack();

		if (itemstack1 != null
			&& itemstack1.itemID == par1ItemStack.itemID
			&& (!par1ItemStack.getHasSubtypes() || par1ItemStack
				.getItemDamage() == itemstack1.getItemDamage())
			&& ItemStack.areItemStackTagsEqual(par1ItemStack,
				itemstack1)) {
		    int l = itemstack1.stackSize + par1ItemStack.stackSize;

		    if (l <= par1ItemStack.getMaxStackSize()
			    && l <= slot.getSlotStackLimit()) {
			par1ItemStack.stackSize = 0;
			itemstack1.stackSize = l;
			this.inventory.onInventoryChanged();
			flag1 = true;
		    } else if (itemstack1.stackSize < par1ItemStack
			    .getMaxStackSize() && l < slot.getSlotStackLimit()) {
			par1ItemStack.stackSize -= par1ItemStack
				.getMaxStackSize() - itemstack1.stackSize;
			itemstack1.stackSize = par1ItemStack.getMaxStackSize();
			this.inventory.onInventoryChanged();
			flag1 = true;
		    }
		}

		if (par4) {
		    --k;
		} else {
		    ++k;
		}
	    }
	}

	if (par1ItemStack.stackSize > 0) {
	    if (par4) {
		k = par3 - 1;
	    } else {
		k = par2;
	    }

	    while (!par4 && k < par3 || par4 && k >= par2) {
		slot = (Slot) this.inventorySlots.get(k);
		itemstack1 = slot.getStack();

		if (itemstack1 == null) {
		    int l = par1ItemStack.stackSize;

		    if (l <= slot.getSlotStackLimit()) {
			slot.putStack(par1ItemStack.copy());
			par1ItemStack.stackSize = 0;
			this.inventory.onInventoryChanged();
			flag1 = true;
			break;
		    } else {
			this.putStackInSlot(
				k,
				new ItemStack(par1ItemStack.getItem(), slot
					.getSlotStackLimit()));
			par1ItemStack.stackSize -= slot.getSlotStackLimit();
			this.inventory.onInventoryChanged();
			flag1 = true;
		    }
		}

		if (par4) {
		    --k;
		} else {
		    ++k;
		}
	    }
	}

	return flag1;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
	// TODO Auto-generated method stub
	return true;
    }

    public void update() {

	this.inventory.update();

    }

    public void writeToNBT() {
	Nbt.setTag(containerStack);

	((InventoryItem) inventory).writeToNBT(this.containerStack
		.getTagCompound());
    }

}
