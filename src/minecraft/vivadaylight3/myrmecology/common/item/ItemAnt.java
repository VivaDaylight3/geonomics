package vivadaylight3.myrmecology.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.lib.Ants;
import vivadaylight3.myrmecology.common.lib.IAnt;
import vivadaylight3.myrmecology.common.lib.Maths;
import vivadaylight3.myrmecology.common.lib.Nbt;
import vivadaylight3.myrmecology.common.lib.Properties;
import vivadaylight3.myrmecology.common.lib.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.lib.Time;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Extend this class to create an ant
 * @author samueltebbs
 */

public class ItemAnt extends Item implements IAnt {
    
    /**
     * Holds the ant's icons
     */
    private Icon[] icons = new Icon[4];
    
    /**
     * Holds the mod ID of the mod that this ant belongs to.
     */
    private String modId;
    
    /**
     * Holds the complete set of names for this ant species.
     */
    private String[] names = this.getCompleteNames();

    public ItemAnt(int par1, String parmodID) {
	super(par1);
	
	setHasSubtypes(true);
	
	this.modId = parmodID;
	
	prepareItem();
	
	Register.addAnt(this);
	
    }
    
    private void prepareItem(){
	
	setUnlocalizedName(this.getSpeciesSubName());
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(64);
	func_111206_d(Resources.TEXTURE_PREFIX+this.getSpeciesSubName());
	
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World par3World, 
	    int par4, int par5, int par6, int par7, float par8, float par9, float par10){
	
	System.out.println("Time in ticks in: "+par3World.getWorldTime());
	
	return true;
	
    }
    
    /**
     * Returns the names variable
     * @return
     */
    public String[] getNames(){
	
	return this.names;
	
    }
    
    /**
     * Returns a String[] of complete names for the ant species
     * @return
     */
    private String[] getCompleteNames(){
	
	String[] result = new String[4];
	
	for(int k = 0; k < this.getTypeNames().length; k++){
	    
	    result[k] = this.getSpeciesName() + " " + this.getTypeNames()[k];
	    
	}
	
	return result;
	
    }
    
    public void registerIcons(IconRegister register){
	
	for (int k = 0; k < 4; k++) {
	    
	    icons[k] = 
		    register.registerIcon(
			    this.modId + 
			    this.getSpeciesSubName() + "_"
		    + this.getTypeNames()[k]);
	    
	}
	
    }
    
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
	
	Nbt.setTag(par1ItemStack);
	
	ItemAnt ant = (ItemAnt) par1ItemStack.getItem();
		
	Properties.setProperties(par1ItemStack, ant.getFertility(), ant.getMaturingTime(), ant.getWinged(), ant.getLifetime(), 0, false, ant.getNocturnal());
    }
    
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
	if( par1ItemStack.stackTagCompound == null )
		par1ItemStack.setTagCompound( new NBTTagCompound( ) );
	
	if(par1ItemStack.getItemDamage() == Ants.getMetaQueen()){
	
	    par3List.add("Lifetime left: "+(Properties.getLifetime(par1ItemStack) - Properties.getLifetimeComplete(par1ItemStack)));
    
	}
    }
        
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {
	
	for (int k = 0; k < 4; k++) {
	    
	    list.add(new ItemStack(itemID, 1, k));
	    
	}
	
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
	
	return this.getUnlocalizedName()
		+ getSpeciesName()+" "+getTypeNames()[itemStack.getItemDamage()];
	
    }
    
    public Icon getIconFromDamage(int par1)
    {
        return this.icons[par1];
    }
    
    @Override
    public int getMaturingTime(){
	
	return Time.getTicksFromMinutes(10);
	
    }
    
    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Default";
    }
    
    @Override
    public String getSpeciesSubName() {
	// TODO Auto-generated method stub
	return "default";
    }
    
    @Override
    public boolean isHillAnt() {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
    public int getFertility() {
	return 2;
    }
    
    @Override
    public int getFertilityChance() {
	return 0;
    }
    
    @Override
    public int getLifetime() {
	return 10;
    }
    
    @Override
    public boolean eatsSweet() {
	// TODO Auto-generated method stub
	return true;
    }
    
    @Override
    public boolean eatsSavoury() {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
    public boolean eatsMeat() {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
    public void performBehaviour(World world, int x, int y, int z) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Antus Defaultus";
    }
    
    @Override
    public BiomeGenBase[] getAntBiomes() {
	// TODO Auto-generated method stub
	return null;
    }
    
    @Override
    public String[] getTypeNames() {
	String[] result = new String[] {"Queen", "Drone", "Worker", "Larva"};
	return result;
    }

    @Override
    public boolean getWinged() {
	return false;
    }

    @Override
    public boolean getNocturnal() {
	return false;
    }
    
}
