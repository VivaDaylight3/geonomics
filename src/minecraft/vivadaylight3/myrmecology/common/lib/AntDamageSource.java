package vivadaylight3.myrmecology.common.lib;

import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AntDamageSource extends DamageSource{

    public static final AntDamageSource antBite = ((AntDamageSource) new AntDamageSource(
	    "antBite")).setDeathMessage("%1$s angered some ants!");
    
    protected AntDamageSource(String par1Str) {
	
	super(par1Str);
	
    }
    
    public AntDamageSource setDeathMessage(String deathMessage)
    {
	LanguageRegistry.instance().addStringLocalization("death.attack." + this.damageType, deathMessage);
	return this;
    }
    
    
    
}
