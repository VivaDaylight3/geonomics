package vivadaylight3.myrmecology.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.Metadata;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.inventory.ContainerIncubator;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIncubator extends GuiContainer {
    
    private GuiButtonSizeable buttonQueen;
    private GuiButtonSizeable buttonDrone;
    private GuiButtonSizeable buttonWorker;
    
    private int buttonWidth = 10;
    private int buttonHeight = 15;
    
    public TileEntityIncubator tile;
    
    private ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();

    public GuiIncubator(EntityPlayer parPlayer, InventoryPlayer inventory,
	    TileEntityIncubator tileEntity, World world, int x, int y, int z) {
	super(new ContainerIncubator(inventory, tileEntity));
	this.tile = tileEntity;
	this.players.add(parPlayer);
    }
    
    @Override
    public void initGui(){
	
	super.initGui();
	this.buttonList.clear();
	
	this.buttonList.add(this.buttonQueen = new GuiButtonSizeable(2,
		this.width / 2 - 80, this.height / 2 - 65, "Q",
		buttonWidth + 8, buttonHeight));
	this.buttonList.add(this.buttonDrone = new GuiButtonSizeable(2,
		this.width / 2 - 83 + (buttonWidth * 2), this.height / 2 - 65, "D",
		buttonWidth + 8, buttonHeight));
	
	this.buttonList.add(this.buttonWorker = new GuiButtonSizeable(2,
		this.width / 2 - 65 + (buttonWidth * 2), this.height / 2 - 65, "W",
		buttonWidth + 8, buttonHeight));
	
    }
    
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {

	if (par1GuiButton == this.buttonQueen) {

	    this.tile.setResultAntMeta(Metadata.getMetaQueen());
	    
	    if(this.players.size() > 0){
		
		for(int k = 0; k < this.players.toArray().length; k++){
			
			((EntityPlayer) this.players.toArray()[k]).addChatMessage("This incubator will produce a "+Reference.standardTypeNames[tile.getResultAntMeta()]);
			
		    }
	   
	    }

	} else if (par1GuiButton == this.buttonDrone) {

	    this.tile.setResultAntMeta(Metadata.getMetaDrone());
	    if(this.players.size() > 0){
		
		for(int k = 0; k < this.players.toArray().length; k++){
			
			((EntityPlayer) this.players.toArray()[k]).addChatMessage("This incubator will produce a "+Reference.standardTypeNames[tile.getResultAntMeta()]);
			
		    }
	   
	    }

	} else if (par1GuiButton == this.buttonWorker) {

	    this.tile.setResultAntMeta(Metadata.getMetaWorker());
	    if(this.players.size() > 0){
		
		for(int k = 0; k < this.players.toArray().length; k++){
			
			((EntityPlayer) this.players.toArray()[k]).addChatMessage("This incubator will produce a "+Reference.standardTypeNames[tile.getResultAntMeta()]);
			
		    }
	   
	    }

	}

    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	String s = this.tile.isInvNameLocalized() ? this.tile.getInvName()
		: StatCollector.translateToLocal(this.tile.getInvName());
	this.fontRenderer.drawString(s,
		this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6,
		4210752);
	this.fontRenderer.drawString(
		StatCollector.translateToLocal("container.inventory"), 8,
		this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
	    int par3) {
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.renderEngine.func_110577_a(Resources.GUI_INCUBATOR);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
    
    @Override
    public void onGuiClosed()
    {
        if (this.mc.thePlayer != null)
        {
            this.inventorySlots.onContainerClosed(this.mc.thePlayer);
            this.players.remove(this.mc.thePlayer);
        }
    }
}
