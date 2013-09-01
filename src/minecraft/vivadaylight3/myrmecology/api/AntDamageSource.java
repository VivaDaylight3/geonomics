package vivadaylight3.myrmecology.api;

import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Use this class to cause damage by ants
 * 
 * @author samueltebbs
 * 
 */
public class AntDamageSource extends DamageSource {

    public static final AntDamageSource antBite = (new AntDamageSource(
	    "antBite", "%1$s angered some ants!"));

    protected AntDamageSource(String par1Str, String message) {

	super(par1Str);
	setDeathMessage(message);

    }

    public AntDamageSource setDeathMessage(String message) {
	LanguageRegistry.instance().addStringLocalization(
		"death.attack." + this.damageType, message);
	return this;
    }

}
