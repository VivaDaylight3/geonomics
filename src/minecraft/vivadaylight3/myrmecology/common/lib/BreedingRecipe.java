package vivadaylight3.myrmecology.common.lib;

import vivadaylight3.myrmecology.common.item.ItemAnt;

public class BreedingRecipe {
    
    private ItemAnt ant1;
    private ItemAnt ant2;
    private ItemAnt antOutput;
    
    public BreedingRecipe(ItemAnt par1, ItemAnt par2, ItemAnt par3) {
	
	this.ant1 = par1;
	this.ant2 = par2;
	this.antOutput = par3;
	
    }
    
    public ItemAnt getAnt1() {
	
	return this.ant1;
	
    }
    
    public ItemAnt getAnt2() {
	
	return this.ant2;
	
    }
    
    public ItemAnt getAntOutput() {
	
	return this.antOutput;
	
    }
    
}
