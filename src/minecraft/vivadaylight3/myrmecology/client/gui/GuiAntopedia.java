package vivadaylight3.myrmecology.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.BiomeGenBase;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.inventory.ContainerAntopedia;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.lib.Resources;

public class GuiAntopedia extends GuiContainer {

    private InventoryItem inventory;

    public GuiAntopedia(ContainerAntopedia container, EntityPlayer player) {

	super(new ContainerAntopedia(container.inventory, player));
	this.inventory = container.inventory;

    }

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
	
	if(this.inventory.getStackInSlot(0) != null){

	if (this.inventory.getStackInSlot(0).getItem() != null) {

	    if (this.inventory.getStackInSlot(0).getItem() != null
		    && this.inventory.getStackInSlot(0).getItem() instanceof ItemAnt) {

		ItemAnt ant = (ItemAnt) this.inventory.getStackInSlot(0)
			.getItem();

		String nameSpecies = ant.getSpeciesName();

		String nameBinomial = ant.getSpeciesBinomialName();

		int maturingTime = ant.getMaturingTime();

		boolean isHillAnt = ant.isHillAnt();

		int fertility = ant.getFertility();

		int lifetime = ant.getLifetime();

		ArrayList<String> dietList = new ArrayList<String>();

		if (ant.eatsSweet()) {

		    dietList.add("Sweet ");

		}

		if (ant.eatsSavoury()) {

		    dietList.add("Savoury ");

		}

		if (ant.eatsMeat()) {

		    dietList.add("Meat ");

		}

		if (ant.eatsLarvae()) {

		    dietList.add("Larvae ");

		}

		String[] dietArray = (String[]) dietList.toArray();

		BiomeGenBase[] biomeArray = ant.getAntBiomes();

		String[] biomeNames = new String[biomeArray.length];

		for (int k = 0; k < biomeArray.length; k++) {

		    biomeNames[k] = biomeArray[k].biomeName;

		}

		boolean isWinged = ant.getWinged();

		boolean isNocturnal = ant.getNocturnal();

		this.drawString(nameSpecies, 26, this.ySize - 120);

		this.inventory.decrStackSize(0, 1);

	    }

	}
	
	}

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
	// drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSize,
	// (float)(l + 75 - 50) - this.ySize, this.mc.thePlayer);
    }

}
