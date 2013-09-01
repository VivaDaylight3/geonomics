package vivadaylight3.myrmecology.common.lib;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;

public class Resources {

    public static final String FORMAT = ".png";

    public static final String TEXTURE_PREFIX = Myrmecology.MOD_ID_LOWER + ":";

    public static final String TEXTURE_LOCATION = "textures/";

    public static final String MODEL_LOCATION = TEXTURE_LOCATION + "models/";
    public static final String ARMOUR_LOCATION = TEXTURE_LOCATION + "armor/";
    public static final String GUI_LOCATION = TEXTURE_LOCATION + "gui/";
    public static final String EFFECT_LOCATION = TEXTURE_LOCATION + "effects/";
    public static final String ANT_LOCATION = "ant/";

    public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.field_110575_b;
    public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.field_110576_c;

    public static final ResourceLocation GUI_ANTFARM = getResourceLocation(GUI_LOCATION
	    + Register.BLOCK_ANTFARM_NAME + FORMAT);
    public static final ResourceLocation GUI_INCUBATOR = getResourceLocation(GUI_LOCATION
	    + Register.BLOCK_INCUBATOR_NAME + FORMAT);

    public static final ResourceLocation GUI_ANTOPEDIA = getResourceLocation(GUI_LOCATION
	    + Register.ITEM_ANTOPEDIA_NAME + FORMAT);

    private static ResourceLocation getResourceLocation(String path) {

	return new ResourceLocation(Myrmecology.MOD_ID_LOWER, path);

    }

}