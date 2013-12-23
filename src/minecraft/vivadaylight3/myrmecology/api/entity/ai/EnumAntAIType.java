package vivadaylight3.myrmecology.api.entity.ai;

import java.util.ArrayList;

import net.minecraftforge.common.Configuration;

import vivadaylight3.myrmecology.common.Register;

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

	Register.config.load();
	this.enabled = Register.config.get(Configuration.CATEGORY_GENERAL,
		this.name + " ant AI enabled", true).getBoolean(true);
	Register.config.save();

    }

}
