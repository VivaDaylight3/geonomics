package vivadaylight3.myrmecology.client.gui.content;

import net.minecraft.util.ResourceLocation;

import org.w3c.dom.Element;

public class Page {

    private Element[] textEl;
    private Element[] iconsEl;

    public String[] text;
    public ResourceLocation[] icons;

    public Page(Element el) {

	textEl = new Element[el.getElementsByTagName("text").getLength()];

	for (int k = 0; k < el.getElementsByTagName("text").getLength(); k++) {

	    textEl[k] = (Element) el.getElementsByTagName("text").item(k);

	}
	this.text = this.getText();

	iconsEl = new Element[el.getElementsByTagName("icon").getLength()];

	for (int k = 0; k < el.getElementsByTagName("icon").getLength(); k++) {

	    iconsEl[k] = (Element) el.getElementsByTagName("icon").item(k);

	}
	this.icons = this.getIconItemStacks();

    }

    public String[] getText() {

	String[] result = new String[textEl.length];

	for (int k = 0; k < textEl.length; k++) {

	    result[k] = textEl[k].getTextContent();

	}

	return result;

    }

    public ResourceLocation[] getIconItemStacks() {

	ResourceLocation[] stacks = new ResourceLocation[iconsEl.length];

	int k = 0;
	for (Element el : iconsEl) {

	    if (Book.icons.containsKey(el.getTextContent())) {

		stacks[k] = (ResourceLocation) Book.icons.get(el
			.getTextContent());

	    }

	    k++;

	}

	return stacks;

    }

}
