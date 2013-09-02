package vivadaylight3.myrmecology.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.common.inventory.ContainerAntopedia;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.lib.Resources;

public class GuiAntopedia extends GuiContainer {

    private InventoryItem inventory;
    
    private GuiButtonSizeable button1Names;
    private GuiButtonSizeable button2Info;
    private GuiButtonSizeable button3Breeding;
    
    private int infoButtonWidth = 76;
    private int infoButtonHeight = 20;

    public GuiAntopedia(ContainerAntopedia container, EntityPlayer player) {

	super(new ContainerAntopedia(container.inventory, player));
	this.inventory = container.inventory;

    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        
        this.buttonList.clear();
        this.buttonList.add(this.button1Names = new GuiButtonSizeable(0, this.width / 3 - 150, 47, "Names", infoButtonWidth, infoButtonHeight));
        this.buttonList.add(this.button2Info = new GuiButtonSizeable(1, this.width / 3 - 150, 70, "Info", infoButtonWidth, infoButtonHeight));
        this.buttonList.add(this.button3Breeding = new GuiButtonSizeable(2, this.width / 3 - 150, 93, "Breeding", infoButtonWidth, infoButtonHeight));
        
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
	super.drawScreen(par1, par2, par3);
	this.xSize = (int) par1;
	this.ySize = (int) par2;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	String s = this.inventory.isInvNameLocalized() ? this.inventory
		.getInvName() : I18n.func_135053_a(this.inventory.getInvName());
	this.fontRenderer.drawString(s,
		this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 0,
		4210752);
	this.fontRenderer.drawString(I18n.func_135053_a("container.inventory"),
		26, this.ySize - 96 + 4, 4210752);

    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();

        
        
    }

    private void drawString(String string, int posX, int posY) {

	this.fontRenderer.drawString(string, posX, posY, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
	    int par3) {
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.func_110434_K().func_110577_a(Resources.GUI_ANTOPEDIA);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	int i1;    
	
    }

}
