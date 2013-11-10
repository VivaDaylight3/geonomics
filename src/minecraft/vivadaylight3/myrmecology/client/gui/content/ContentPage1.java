package vivadaylight3.myrmecology.client.gui.content;

import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class ContentPage1 extends BookContent {

    public ContentPage1(GuiScreen parGui) {
	super(parGui);
    }

    @Override
    public void draw(FontRenderer parRenderer, int par2, int par3, int par4) {

	String s1 = "The book of " + Reference.MOD_ID;
	renderer.drawSplitString(s1, par2, par3, par4, 0);
	// drawAfter(par2, par3, par4, s1,
	// Register.itemAntopedia.getIconFromDamage(0));

    }

}
