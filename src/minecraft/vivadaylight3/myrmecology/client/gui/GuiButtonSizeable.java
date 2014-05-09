package vivadaylight3.myrmecology.client.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiButtonSizeable extends GuiButton {

    public GuiButtonSizeable(int id, int posX, int posY, String string,
	    int parWidth, int parHeight) {
	super(id, posX, posY, string);

	this.width = parWidth;
	this.height = parHeight;
	this.enabled = true;
	this.id = id;
	this.xPosition = posX;
	this.yPosition = posY;
	this.displayString = string;

    }

    public GuiButtonSizeable(int par1, int par2, int par3, int par4, int par5,
	    String par6Str) {
	super(par1, par2, par3, par4, par5, par6Str);
    }

    public int getWidth() {

	return this.width;

    }

    public void setWidth(int par) {

	this.width = par;

    }

    public int getHeight() {

	return this.height;

    }

    public void setHeight(int par) {

	this.height = par;

    }

}
