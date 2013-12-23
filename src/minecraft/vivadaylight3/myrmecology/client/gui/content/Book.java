package vivadaylight3.myrmecology.client.gui.content;

import java.util.HashMap;

import net.minecraft.util.ResourceLocation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Resources;

public class Book {

    public Page[] pages;
    Document doc;

    public static HashMap icons = new HashMap<String, ResourceLocation>();

    public Book(Document doc) {

	this.doc = doc;
	this.init();

    }

    public static void initIcons() {

	icons.put("myrmopaedia", new ResourceLocation(Resources.TEXTURE_PREFIX
		+ "textures/items/" + Reference.ITEM_MYRMOPAEDIA_NAME + ".png"));
	icons.put("recipeExtractor", new ResourceLocation(
		Resources.TEXTURE_PREFIX + "textures/recipes/"
			+ Reference.ITEM_EXTRACTOR_NAME + ".png"));
	icons.put("recipeSolarium", new ResourceLocation(
		Resources.TEXTURE_PREFIX + "textures/recipes/"
			+ Reference.BLOCK_INCUBATOR_NAME + ".png"));
	icons.put("recipeFormicarium", new ResourceLocation(
		Resources.TEXTURE_PREFIX + "textures/recipes/"
			+ Reference.BLOCK_ANTFARM_NAME + ".png"));
	icons.put("antWorker", new ResourceLocation(Resources.TEXTURE_PREFIX
		+ "textures/recipes/" + "ant"
		+ Reference.standardTypeNames[Metadata.getMetaWorker()]
		+ ".png"));

    }

    private void init() {

	pages = new Page[doc.getElementsByTagName("page").getLength()];

	for (int k = 0; k < pages.length; k++) {

	    pages[k] = new Page((Element) doc.getElementsByTagName("page")
		    .item(k));

	}

    }

}
