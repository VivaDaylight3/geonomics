package vivadaylight3.myrmecology.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.Breeding;
import vivadaylight3.myrmecology.api.BreedingRecipe;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.inventory.ContainerAntopedia;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.lib.Nbt;
import vivadaylight3.myrmecology.common.lib.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

    private int infoButtonWidth = 20;
    private int infoButtonHeight = 15;
    
    private int buttonID;

    private ItemAnt selectedAnt;

    public GuiAntopedia(ContainerAntopedia parcontainer, EntityPlayer player) {

	super(new ContainerAntopedia(parcontainer.inventory, player));
	this.inventory = parcontainer.inventory;
	this.container = parcontainer;
	this.antopedia = parcontainer.containerStack;
	this.selectedScreen = "ants";
	selectedAnt = null;
	
	xSize = 248;
        ySize = 255;
	
    }

    @Override
    public void initGui() {
	super.initGui();

	this.buttonList.clear();
	this.buttonList.add(this.button1Names = new GuiButtonSizeable(0, 277,
		64, "1", infoButtonWidth, infoButtonHeight));
	this.buttonList.add(this.button2Info = new GuiButtonSizeable(1, 277,
		80, "2", infoButtonWidth, infoButtonHeight));
	this.buttonList.add(this.button3Breeding = new GuiButtonSizeable(2,
		277, 96, "3", infoButtonWidth, infoButtonHeight));

	this.buttonID = 2;

    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {

	if (par1GuiButton == this.button1Names) {

	    this.selectedScreen = "names";

	} else if (par1GuiButton == this.button2Info) {

	    this.selectedScreen = "info";

	} else if (par1GuiButton == this.button3Breeding) {

	    this.selectedScreen = "breeding";

	} else if (par1GuiButton instanceof GuiButtonAnt) {

	    this.selectedAnt = ((GuiButtonAnt) par1GuiButton).getAnt();

	}

    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
	super.drawScreen(par1, par2, par3);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {

	this.drawString(EnumChatFormatting.WHITE + "Selected Screen: "
		+ selectedScreen, 13, 15);

	if (this.selectedScreen == "names" && this.selectedAnt != null) {

	    this.drawString(EnumChatFormatting.WHITE + "Species: "
		    + this.selectedAnt.getSpeciesName(), 13, 30);
	    this.drawString(EnumChatFormatting.WHITE + "Binomial: "
		    + this.selectedAnt.getSpeciesBinomialName(), 13, 45);

	} else if (this.selectedScreen == "info" && this.selectedAnt != null) {

	    this.drawString(EnumChatFormatting.WHITE + "Lifetime: "
		    + this.selectedAnt.getLifetime(), 13, 30);
	    this.drawString(EnumChatFormatting.WHITE + "Maturing Time: "
		    + this.selectedAnt.getMaturingTime(), 13, 45);
	    this.drawString(EnumChatFormatting.WHITE + "Fertility: "
		    + this.selectedAnt.getMaturingTime(), 13, 60);

	} else if (this.selectedScreen == "breeding"
		&& this.selectedAnt != null) {

	    ArrayList<BreedingRecipe> breedingList = Breeding
		    .getAntBreedingOptions(selectedAnt);

	    int offset = 0;

	    int control = 0;

	    for (int k = 0; k < breedingList.toArray().length; k++) {

		if (control == 0) {

		    ItemAnt partner;

		    if (((BreedingRecipe) breedingList.toArray()[k]).getAnt1() == this.selectedAnt) {

			partner = ((BreedingRecipe) breedingList.toArray()[k])
				.getAnt2();

		    } else {

			partner = ((BreedingRecipe) breedingList.toArray()[k])
				.getAnt1();

		    }

		    this.drawString(
			    EnumChatFormatting.GREEN
				    + this.selectedAnt.getSpeciesName().replaceAll(" Ant", "")
				    + EnumChatFormatting.WHITE
				    + " + "
				    + EnumChatFormatting.BLUE
				    + partner.getSpeciesName().replaceAll(" Ant", "")
				    + EnumChatFormatting.WHITE
				    + " = "
				    + EnumChatFormatting.YELLOW
				    + ((BreedingRecipe) breedingList.toArray()[k])
					    .getAntOutput().getSpeciesName().replaceAll(" Ant", ""),
			    13, 30 + offset);

		    control = 1;

		    offset += 15;

		}else{
		    
		    control = 0;
		    
		}

	    }

	}

	/*
	 * String s = this.inventory.isInvNameLocalized() ? this.inventory
	 * .getInvName() : I18n.func_135053_a(this.inventory.getInvName());
	 * this.fontRenderer.drawString(s, this.xSize / 2 -
	 * this.fontRenderer.getStringWidth(s) / 2, 0, 4210752);
	 * 
	 * this.fontRenderer.drawString(I18n.func_135053_a("container.inventory")
	 * , 26, this.ySize - 96 + 4, 4210752);
	 */

    }

    @Override
    public void updateScreen() {
	super.updateScreen();

	if (this.getAnt() != null) {

	    Nbt.setTag(this.container.containerStack);

	    this.selectedAnt = this.getAnt();

	    if (!Nbt.hasKey(this.antopedia, this.getAnt().getSpeciesSubName())) {

		Nbt.set(this.antopedia, this.getAnt().getSpeciesSubName(), true);

	    }
	    
	    this.container.inventory.decrStackSize(0, 1);

	}

	for (int k = 0; k < Register.getAntList().toArray().length; k++) {

	    if (Nbt.hasKey(antopedia, ((ItemAnt) Register.getAntList()
		    .toArray()[k]).getSpeciesSubName())) {

		this.registeredAnts.add((ItemAnt) Register.getAntList()
			.toArray()[k]);

	    }

	}

	int offset = 23;

	if (this.selectedScreen == "ants") {

	    for (int i = 0; i < this.registeredAnts.toArray().length; i++) {

		this.buttonList.add(new GuiButtonAnt(this.buttonID,
			this.width / 3, 24 + offset,
			((ItemAnt) this.registeredAnts.toArray()[i])
				.getSpeciesName(), 100, 20,
			(ItemAnt) this.registeredAnts.toArray()[i]));
		this.buttonID++;

		offset += 23;

	    }

	}

    }

    private void drawString(String string, int posX, int posY) {

	this.fontRenderer.drawString(string, posX, posY, 4210752);

    }

    private void drawString(String string, int posX, int posY, int colour,
	    boolean shadow) {

	this.fontRenderer.drawString(string, posX, posY, colour, shadow);

    }

    private ItemAnt getAnt() {

	if (this.inventory.getStackInSlot(0) != null) {

	    if (this.inventory.getStackInSlot(0).getItem() instanceof ItemAnt) {

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
