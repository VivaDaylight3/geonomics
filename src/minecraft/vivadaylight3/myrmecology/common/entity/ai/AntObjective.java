package vivadaylight3.myrmecology.common.entity.ai;

import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntObjective {
    
    private int targetX;
    private int targetY;
    private int targetZ;
    
    private String targetName;
    
    private IEntityAnt theAnt;
    private int distance;

    public AntObjective(IEntityAnt ant, int x, int y, int z, String name, int distance) {
	
	setTargetX(x);
	setTargetY(y);
	setTargetZ(z);
	setTargetName(name);
	this.distance = distance;
	
    }
    
    public boolean isAtObjective(int distance){
	
	return Environment.coordinateIsCloseTo(theAnt.getPosX(), theAnt.getPosY(), theAnt.getPosZ(), this.getTargetX(), this.getTargetY(), this.getTargetZ(), distance);
	
    }
    
    private void setAnt(IEntityAnt ant){
	
	this.theAnt = ant;
	
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public int getTargetZ() {
        return targetZ;
    }

    public void setTargetZ(int targetZ) {
        this.targetZ = targetZ;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

}
