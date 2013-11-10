package vivadaylight3.myrmecology.client.gui.content;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;

public class BookContent {

    protected FontRenderer renderer;
    protected GuiScreen gui;

    public BookContent(GuiScreen parGui) {
	gui = parGui;

    }

    /**
     * Draws the page
     * 
     * @param xPos
     * @param yPos
     * @param lineWidth
     */
    public void drawPage(FontRenderer parRenderer, int par2, int par3, int par4) {

	renderer = parRenderer;
	this.draw(parRenderer, par2, par3, par4);

    }

    protected void draw(FontRenderer parRenderer, int par2, int par3, int par4) {

    }

}
