package vivadaylight3.myrmecology.api;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

/**
 * Extend this class to create a new breeding chamber used in ant breeding
 * @author samueltebbs
 *
 */
public class ItemBreedingChamber extends Item {

    public ItemBreedingChamber(int par1) {
	super(par1);
	prepareItem();
    }
    
    private void prepareItem() {

	setUnlocalizedName(this.getChamberAntSpeciesName()+Reference.ITEM_CHAMBER_NAME);
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(64);
	//func_111206_d(Resources.TEXTURE_PREFIX + "ant_"
		//+ this.getSpeciesSubName());

    }
    
    @Override
    public void registerIcons(IconRegister register){
	
	this.itemIcon = register.registerIcon(Resources.TEXTURE_PREFIX + Reference.ITEM_CHAMBER_NAME);
	
    }
    
    /**
     * Gets the ant that this chamber is used to breed
     * @return ItemAnt (null if no ants use it...)
     */
    public ItemAnt getAnt(){
	
	return null;
	
    }
    
    /**
     * Used in prepareItem() to get the name of the ant species which this chamber is used for, should end with a space
     * @return String
     */
    public String getChamberAntSpeciesName(){
	
	return "";
	
    }

}
