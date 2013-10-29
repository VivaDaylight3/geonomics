package vivadaylight3.myrmecology.client.gui;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.common.lib.Resources;

public class GuiAntChest extends GuiChest {

    private int inventoryRows;

    public GuiAntChest(IInventory par1iInventory, IInventory par2iInventory) {
	super(par1iInventory, par2iInventory);
	this.inventoryRows = par2iInventory.getSizeInventory() / 9;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
	    int par3) {
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.getTextureManager().bindTexture(Resources.GUI_ANTCHEST);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize,
		this.inventoryRows * 18 + 17);
	this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126,
		this.xSize, 96);
    }

}
