package vivadaylight3.myrmecology.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.inventory.ContainerAntopedia;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.lib.Nbt;
import vivadaylight3.myrmecology.common.lib.Resources;

public class GuiAntopedia extends GuiContainer {

    private InventoryItem inventory;
    private ContainerAntopedia container;
    private ItemStack antopedia;
    
    public static final String REGISTERED_ANTS_KEY = "registeredAnts";
    
    private GuiButtonSizeable button1Names;
    private GuiButtonSizeable button2Info;
    private GuiButtonSizeable button3Breeding;
    private GuiButtonSizeable button4Ants;
    
    private ArrayList<ItemAnt> registeredAnts = new ArrayList<ItemAnt>();
    private ArrayList<GuiButtonAnt> antButtonsList = new ArrayList<GuiButtonAnt>();
    
    private String selectedScreen = "ants";
        
    private int infoButtonWidth = 76;
    private int infoButtonHeight = 20;
    
    private int buttonID;

    public GuiAntopedia(ContainerAntopedia parcontainer, EntityPlayer player) {

	super(new ContainerAntopedia(parcontainer.inventory, player));
	this.inventory = parcontainer.inventory;
	this.container = parcontainer;
	this.antopedia = parcontainer.containerStack;

    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        
        this.buttonList.clear();
        this.buttonList.add(this.button1Names = new GuiButtonSizeable(0, this.width / 3 - 150, 47, "Names", infoButtonWidth, infoButtonHeight));
        this.buttonList.add(this.button2Info = new GuiButtonSizeable(1, this.width / 3 - 150, 70, "Info", infoButtonWidth, infoButtonHeight));
        this.buttonList.add(this.button3Breeding = new GuiButtonSizeable(2, this.width / 3 - 150, 93, "Breeding", infoButtonWidth, infoButtonHeight));
        this.buttonList.add(this.button4Ants = new GuiButtonSizeable(3, this.width / 3 - 150, 116, "Ants", infoButtonWidth, infoButtonHeight));

        this.buttonID = 3;
        
        for(int k = 0; k < Register.getAntList().toArray().length; k++){
            
            if(Nbt.hasKey(antopedia, ((ItemAnt) Register.getAntList().toArray()[k]).getSpeciesSubName())){
        	
        	this.registeredAnts.add((ItemAnt) Register.getAntList().toArray()[k]);
        	
            }
            
        }
        
        int offset = 23;
        
        for(int i = 0; i < this.registeredAnts.toArray().length; i++){
                        
            this.buttonList.add(new GuiButtonAnt(this.buttonID, this.width / 3, 24 + offset, 
        	    ((ItemAnt) this.registeredAnts.toArray()[i]).getSpeciesName(), 100, 20, 
        	    (ItemAnt) this.registeredAnts.toArray()[i]));
            this.buttonID++;
            
            offset += 23;
            
        }
        
    }
    
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
	
	if(par1GuiButton == this.button1Names){
	    
	    this.selectedScreen = "names";
	    
	}else if(par1GuiButton == this.button2Info){
	    
	    this.selectedScreen = "info";
	    
	}else if(par1GuiButton == this.button3Breeding){
	    
	    this.selectedScreen = "breeding";
	    
	}else{
	    
	    this.selectedScreen = "ants";
	    
	}
	
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

        if(this.getAnt() != null){
            
            Nbt.setTag(this.container.containerStack);
            
            if(!Nbt.hasKey(this.antopedia,  this.getAnt().getSpeciesSubName())){
        	
        	Nbt.set(this.antopedia, this.getAnt().getSpeciesSubName(), true);
        	
            }
            
            this.inventory.decrStackSize(0, 1);
            
        }
        
    }

    private void drawString(String string, int posX, int posY) {

	this.fontRenderer.drawString(string, posX, posY, 4210752);

    }
    
    private ItemAnt getAnt(){
	
	if(this.inventory.getStackInSlot(0) != null){
	    
	    if(this.inventory.getStackInSlot(0).getItem() instanceof ItemAnt){
		
		return (ItemAnt) this.inventory.getStackInSlot(0).getItem();
		
	    }
	    
	}
	
	return null;
	
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
