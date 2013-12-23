package vivadaylight3.myrmecology.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.common.block.BlockInfuser;
import vivadaylight3.myrmecology.common.inventory.ContainerInfuser;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.InfuserRecipe;
import vivadaylight3.myrmecology.common.lib.InfuserRecipeRegistry;

public class TileEntityInfuser extends TileEntity implements IInventory {

    private ItemStack[] contents = new ItemStack[this.getInventorySize()];
    private String field_94045_s;

    public int getInventorySize() {

	return ContainerInfuser.INVENTORY_SIZE;

    }

    public ItemStack[] getContents() {

	return this.contents;

    }

    private boolean isEmpty() {

	for (int k = 0; k < this.getContents().length; k++) {

	    if (this.getStackInSlot(k) != null) {

		return false;

	    }

	}

	return true;

    }

    private boolean hasIngredient(ItemStack stack) {

	for (int k = 0; k < 8; k++) {

	    if (this.getStackInSlot(k).equals(stack))
		return true;

	}

	return false;

    }

    private ItemStack getRecipeOutput() {

	if (!this.isEmpty()) {

	    int amount = 0;

	    for (int k = 0; k < 8; k++) {

		if (this.getStackInSlot(k) != null) {

		    amount++;

		}

	    }

	    for (int i = 0; i > InfuserRecipeRegistry.getRecipes().size(); i++) {

		InfuserRecipe recipe = InfuserRecipeRegistry.getRecipes()
			.get(i);

		if (amount == recipe.getIngredients().length) {

		    if (recipe.isShapeless()) {

			for (int a = 0; a < recipe.getIngredients().length; a++) {

			    if (!this.hasIngredient(recipe.getIngredients()[a])) {

				return null;

			    }

			}

			return recipe.getOutput();

		    } else {

			for (int a = 0; a < recipe.getIngredients().length; a++) {

			    if (!this.getContents()[a].equals(recipe
				    .getIngredients()))
				return null;

			}

			return recipe.getOutput();

		    }

		}

	    }

	}

	return null;

    }

    @Override
    public void updateEntity() {

	super.updateEntity();

	if (BlockInfuser.isPowered(this.worldObj.getBlockMetadata(this.xCoord,
		this.yCoord, this.zCoord))) {

	    ItemStack output = this.getRecipeOutput();

	    if (output != null) {

		Environment.addItemStackToInventory(output, this.getContents(),
			64, this, this.getContents().length - 1);

		for (int k = 0; k < this.getContents().length; k++) {

		    this.decrStackSize(k, 1);

		}

	    }

	}

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
    public int getSizeInventory() {
	// TODO Auto-generated method stub
	return this.contents.length;
    }

    @Override
    public String getInvName() {
	// TODO Auto-generated method stub
	return "Pheromonic Infuser";
    }

    @Override
    public boolean isInvNameLocalized() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int getInventoryStackLimit() {
	// TODO Auto-generated method stub
	return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
	return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
		this.zCoord) != this ? false
		: entityplayer.getDistanceSq(this.xCoord + 0.5D,
			this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
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
	// TODO Auto-generated method stub
	return true;
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

}
