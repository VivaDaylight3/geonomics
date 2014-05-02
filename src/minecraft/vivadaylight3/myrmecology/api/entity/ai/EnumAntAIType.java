package vivadaylight3.myrmecology.api.entity.ai;

import java.util.ArrayList;

/**
 * Each ant AI must be designated to an AI type
 * 
 * @author samueltebbs
 * 
 */
public enum EnumAntAIType {

    DAMAGE("Damaging"), DESTRUCTION("Destructive"), FARMING("Farming"), SCAVENGING(
	    "Scavenging");

    ArrayList<EnumAntAIType> typeList = new ArrayList<EnumAntAIType>();
    private String name;
    public boolean enabled;

    private EnumAntAIType(String name) {

	typeList.add(this);
	this.name = name;

    }

}
