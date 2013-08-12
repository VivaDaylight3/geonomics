package vivadaylight3.myrmecology.common.tileentity;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.lib.Breeding;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Properties;
import vivadaylight3.myrmecology.common.lib.Time;
import vivadaylight3.myrmecology.common.lib.Variables;

public class TileEntityAntFarm extends TileEntity implements IInventory {
    
    private ItemStack[] contents = new ItemStack[this.getSizeInventory()];
    
    private String field_94045_s;
    
    public final Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();
    
    public int numPlayersUsing;
    
    public boolean isBreeding = false;
    
    private int stackLimit;
            
    public ItemStack[] getContents() {
	
	return this.contents;
	
    }
    
    public int getSizeInventory() {
	
	return 19;
	
    }
    
    /**
     * Checks whether is breeding at the moment
     * @return boolean
     */
    public boolean isBreeding() {
	
	if(this.getQueen() != null && this.getDrone() != null){
	    
	    if(Properties.getMated(this.getQueen())){
		
		if(this.canBreed()){
		
		    return true;
		
		}
		
	    }
	    
	}
	
	return false;
	
    }
    
    /**
     * Gets the drone in the drone slot
     * @return ItemStack
     */
    private ItemStack getDrone(){
	
	return this.getContents()[getDroneSlot()];
	
    }
    
    /**
     * Gets the queen in the queen slot
     * @return ItemStack
     */
    private ItemStack getQueen(){
	
	return this.getContents()[getQueenSlot()];
	
    }
    
    /**
     * Checks whether or not the ant farm can breed given certain circumstances
     * @return boolean
     */
    private boolean canBreed(){
	
	if((getQueen() != null && getDrone() != null) && !Properties.getMated(getQueen())){
	
	    if(getQueen().getItem() instanceof ItemAnt && getDrone().getItem() instanceof ItemAnt){
						
		if(this.getBreedingResult() != null){
						
		    if(Environment.inventoryCanHold(this.getBreedingResult(), this.getContents(), 
			    this.getMaxStackSize())){

			if(antCanEat((ItemAnt) getQueen().getItem()) && antCanEat((ItemAnt) getDrone().getItem())){
			    
			    if(Properties.getNocturnal(this.getQueen()) && Properties.getNocturnal(this.getDrone())){
				
				if(finalCheck()){
				    
				    return true;
				    
				}
				
			    }else if(Time.getWorldTimeString(worldObj) == "day"){
				
				if(finalCheck()){
				    
				    return true;
				    
				}
				
			    }
			    
			}
			
		    }
		    
		}
	    
	    }
	
	}
	
	return false;
	
    }
    
    private boolean finalCheck(){
	
	if((Environment.isRaining(worldObj) || Environment.isThundering(worldObj)) && (!Properties.getWinged(getQueen()) && !Properties.getWinged(getQueen()))){
	    
	    return false;
	    
	}else{
	    
	    BiomeGenBase biome = Environment.getBiome(worldObj, xCoord, zCoord);
	    
	    if(((ItemAnt) this.getQueen().getItem()).getAntBiomes() == null && ((ItemAnt) this.getQueen().getItem()).getAntBiomes() == null){
		
		return true;
		
	    }
	    
	    for(int k = 0; k < ((ItemAnt) this.getQueen().getItem()).getAntBiomes().length; k++){
		
		for(int i = 0; i < ((ItemAnt) this.getDrone().getItem()).getAntBiomes().length; i++){
		    
		    if(((ItemAnt) this.getQueen().getItem()).getAntBiomes()[k] == biome && ((ItemAnt) this.getDrone().getItem()).getAntBiomes()[i] == biome){
			
			return true;
			
		    }
		    
		}
		
	    }
	    
	}
	
	return false;
	
    }
    
    /**
     * Checks whether or not the ant can eat the food in the food slots
     * @param ItemAnt
     * @return boolean
     */
    private boolean antCanEat(ItemAnt ant){
	
	for(int k = 17; k < 18; k = 18){
	    
	    if(ant.eatsSweet()){
	    
		for(int i = 0; i < Properties.getFoodSweet().length; k++){
		
		    if((Properties.getFoodSweet()[i] == this.getContents()[k].getItem().itemID)){
		    
			return true;
		    
		    }
		    
		}
	    
	    }
	    
	    if(ant.eatsSavoury()){
		    
		for(int i = 0; i < Properties.getFoodSavoury().length; k++){
		
		    if((Properties.getFoodSavoury()[i] == this.getContents()[k].getItem().itemID)){
		    
			return true;
		    
		    }
		    
		}
	    
	    }
	    
	    if(ant.eatsMeat()){
		    
		for(int i = 0; i < Properties.getFoodMeat().length; k++){
		
		    if((Properties.getFoodMeat()[i] == this.getContents()[k].getItem().itemID)){
		    
			return true;
		    
		    }
		    
		}
	    
	    }
	    
	    if(ant.eatsLarvae()){
		
		if(this.getContents()[k].getItemDamage() == 3){
		    
		    return true;
		    
		}
		
	    }
	    
	}
	
	return false;
	
    }
        
    /**
     * Gets the breeding result from the ant farm's drone and queen
     * @return ItemStack
     */
    private ItemStack getBreedingResult(){
	
	return Breeding.getBreedingResult((ItemAnt)this.getQueen().getItem()
		, (ItemAnt)this.getDrone().getItem());
	
    }
    
    // TODO
    @Override
    public void updateEntity() {
	
	if(this.isBreeding()){
	    
	    if(this.getLifetimeComplete() < this.getLifetimeTotal()){
		
		increaseLifetimeComplete();
		this.isBreeding = true;
		
	    }else{
		
		Environment.addItemStackToInventory(getBreedingResult(), getContents(), getMaxStackSize(), this);
		
		clearLifetimeComplete();
		
		decrStackSize(getQueenSlot(), 1);
		decrStackSize(getDroneSlot(), 1);
		this.isBreeding = false;
		
	    }
	    
	}else{
	    
	    if(this.canBreed()){
		
		Properties.setMated(this.getQueen(), true);
		
	    }
	    
	}
	
    }
    
    private void increaseLifetimeComplete(){
		
	Properties.setLifetimeComplete(this.getQueen(), getLifetimeComplete() + 1);
	
    }
    
    private void clearLifetimeComplete(){
		
	Properties.setLifetimeComplete(this.getContents()[getQueenSlot()], 0);
	
    }
    
    private int getLifetimeComplete() {
	
	return Properties.getLifetimeComplete(this.getContents()[getQueenSlot()]);
	
    }
    
    private int getLifetimeTotal() {
	
	return Properties.getLifetime(this.getContents()[getQueenSlot()]);
	
    }
    
    public int getLifetimeComplete(int scale) {
	
	int require = (int) Math.ceil((this.getLifetimeTotal(Variables.getAntFromItemStack(this.getContents()[getQueenSlot()]))
		/ scale));
	
	int progress = (int) Math.floor(this.getLifetimeComplete() / require);
	
	return progress;
		
    }
    
    private int getLifetimeTotal(ItemAnt ant){
		
	return ant.getLifetime();
	
    }
    
    public int getMaxStackSize() {
	
	return this.getInventoryStackLimit();
	
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
    
    private static int getDroneSlot() {
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
    
    public int getQueenSlot() {
	
	return 16;
	
    }
    
    public boolean isFoodSlot(int slot) {
	
	if (slot == 17 || slot == 18) {
	    
	    return true;
	    
	} else {
	    
	    return false;
	    
	}
	
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
	// TODO Auto-generated method stub
	return false;
    }
    
}
