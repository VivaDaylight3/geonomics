package vivadaylight3.myrmecology.api;

public class AntEntry {
    
    private ItemAnt ant;
    private ItemBreedingChamber chamber;
    private IEntityAnt entity;

    public AntEntry(ItemAnt parAnt, ItemBreedingChamber parChamber, IEntityAnt parEntity) {
	
	ant = parAnt;
	chamber = parChamber;
	entity = parEntity;
	
    }
    
    public ItemAnt getAnt(){
	
	return ant;
	
    }
    
    public ItemBreedingChamber getBreedingChamber(){
	
	return chamber;
	
    }
    
    public IEntityAnt getEntity(){
	
	return entity;
	
    }

}
