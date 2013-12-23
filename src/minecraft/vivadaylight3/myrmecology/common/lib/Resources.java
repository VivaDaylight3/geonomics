package vivadaylight3.myrmecology.common.lib;

import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.Reference;

public class Resources {

    public static final String FORMAT = ".png";

    public static final String TEXTURE_PREFIX = Reference.MOD_ID.toLowerCase()
	    + ":";

    public static final String ASSETS_LOCATION = "assets/"
	    + Reference.MOD_ID.toLowerCase() + "/";
    public static final String TEXTURE_LOCATION = "textures/";
    public static final String SOUND_LOCATION = "sound/";
    public static final String LANG_LOCATION = "lang/";
    public static final String BOOK_LOCATION = "/" + ASSETS_LOCATION + "book/";

    public static final String MODEL_LOCATION = TEXTURE_LOCATION + "models/";
    public static final String ARMOUR_LOCATION = TEXTURE_LOCATION + "armour/";
    public static final String GUI_LOCATION = TEXTURE_LOCATION + "gui/";
    public static final String EFFECT_LOCATION = TEXTURE_LOCATION + "effects/";
    public static final String ENTITY_LOCATION = TEXTURE_LOCATION + "entity/";
    public static final String ANT_LOCATION = "ant/";
    public static final String LOGO_PATH = TEXTURE_LOCATION + "logo/logo.png";

    public static final ResourceLocation SOUND_ANT_LIVING = getResourceLocation(SOUND_LOCATION
	    + "antLiving.ogg");

    public static final ResourceLocation ENTITY_ANT_FOREST = getResourceLocation(ENTITY_LOCATION
	    + "antForest.png");
    public static final ResourceLocation ENTITY_ANT_CARPENTER = getResourceLocation(ENTITY_LOCATION
	    + "antCarpenter.png");
    public static final ResourceLocation ENTITY_ANT_BARBARIC = getResourceLocation(ENTITY_LOCATION
	    + "antBarbaric.png");
    public static final ResourceLocation ENTITY_ANT_ODOUROUS = getResourceLocation(ENTITY_LOCATION
	    + "antOdourous.png");
    public static final ResourceLocation ENTITY_ANT_SCAVENGER = getResourceLocation(ENTITY_LOCATION
	    + "antScavenger.png");
    public static final ResourceLocation ENTITY_ANT_DREDGER = getResourceLocation(ENTITY_LOCATION
	    + "antDredger.png");

    public static final ResourceLocation ENTITY_ANT_FUNGAL = getResourceLocation(ENTITY_LOCATION
	    + Reference.ANT_FUNGAL_NAME + FORMAT);

    public static final ResourceLocation ENTITY_ANT_MOUND = getResourceLocation(ENTITY_LOCATION
	    + "antMound.png");

    public static final ResourceLocation ENTITY_ANT_SPROUTER = getResourceLocation(ENTITY_LOCATION
	    + Reference.ANT_SPROUTER_NAME + FORMAT);

    public static final ResourceLocation BLOCK_ANTCHEST = getResourceLocation(MODEL_LOCATION
	    + Reference.BLOCK_ANTCHEST_NAME + ".png");
    public static final ResourceLocation BLOCK_ANTCHEST_DOUBLE = getResourceLocation(MODEL_LOCATION
	    + Reference.BLOCK_ANTCHEST_NAME + "_double.png");

    public static final ResourceLocation GUI_ANTFARM = getResourceLocation(GUI_LOCATION
	    + Reference.BLOCK_ANTFARM_NAME + FORMAT);

    public static final ResourceLocation GUI_INCUBATOR = getResourceLocation(GUI_LOCATION
	    + Reference.BLOCK_INCUBATOR_NAME + FORMAT);

    public static final ResourceLocation GUI_ANTOPEDIA = getResourceLocation(GUI_LOCATION
	    + Reference.ITEM_MYRMOPAEDIA_NAME + FORMAT);

    public static final ResourceLocation GUI_ANTCHEST = getResourceLocation(GUI_LOCATION
	    + Reference.BLOCK_ANTCHEST_NAME + FORMAT);

    public static final ResourceLocation GUI_ANTBOOK = getResourceLocation(GUI_LOCATION
	    + Reference.ITEM_ANTBOOK_NAME + FORMAT);

    public static final ResourceLocation GUI_INFUSER = getResourceLocation(GUI_LOCATION
	    + Reference.BLOCK_INFUSER_NAME + FORMAT);

    private static ResourceLocation getResourceLocation(String path) {

	return new ResourceLocation(Reference.MOD_ID.toLowerCase(), path);

    }

}