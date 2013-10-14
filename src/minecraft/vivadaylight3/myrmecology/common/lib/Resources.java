package vivadaylight3.myrmecology.common.lib;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntForest;

public class Resources {

    public static final String FORMAT = ".png";

    public static final String TEXTURE_PREFIX = Reference.MOD_ID.toLowerCase()
	    + ":";

    public static final String TEXTURE_LOCATION = "textures/";
    public static final String LANG_LOCATION = "lang/";

    public static final String MODEL_LOCATION = TEXTURE_LOCATION + "models/";
    public static final String ARMOUR_LOCATION = TEXTURE_LOCATION + "armour/";
    public static final String GUI_LOCATION = TEXTURE_LOCATION + "gui/";
    public static final String EFFECT_LOCATION = TEXTURE_LOCATION + "effects/";
    public static final String ENTITY_LOCATION = TEXTURE_LOCATION + "entity/";
    public static final String ANT_LOCATION = "ant/";
    public static final String LOGO_PATH = TEXTURE_LOCATION + "logo/logo.png";

    public static final ResourceLocation ENTITY_ANT_FOREST = getResourceLocation(ENTITY_LOCATION
	    +"antForest.png");//+ Register.antForest.getSpeciesSubName() + FORMAT);
    
    
    public static final ResourceLocation GUI_ANTFARM = getResourceLocation(GUI_LOCATION
	    + Reference.BLOCK_ANTFARM_NAME + FORMAT);

    public static final ResourceLocation GUI_INCUBATOR = getResourceLocation(GUI_LOCATION
	    + Reference.BLOCK_INCUBATOR_NAME + FORMAT);

    public static final ResourceLocation GUI_ANTOPEDIA = getResourceLocation(GUI_LOCATION
	    + Reference.ITEM_MYRMOPAEDIA_NAME + FORMAT);

    private static ResourceLocation getResourceLocation(String path) {

	return new ResourceLocation(Reference.MOD_ID.toLowerCase(), path);

    }

}