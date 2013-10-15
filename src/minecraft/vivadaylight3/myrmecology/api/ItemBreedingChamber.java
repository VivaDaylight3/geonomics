package vivadaylight3.myrmecology.api;

import net.minecraft.item.Item;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;

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

	setUnlocalizedName(this.getChamberSpeciesName()+Reference.ITEM_CHAMBER_NAME);
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(64);
	//func_111206_d(Resources.TEXTURE_PREFIX + "ant_"
		//+ this.getSpeciesSubName());

    }
    
    public static ItemAnt getAnt(){
	
	return null;
	
    }
    
    public static String getChamberSpeciesName(){
	
	return "";
	
    }

}
