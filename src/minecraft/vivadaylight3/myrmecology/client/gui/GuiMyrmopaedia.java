package vivadaylight3.myrmecology.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.breeding.Breeding;
import vivadaylight3.myrmecology.api.breeding.BreedingRecipe;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.api.util.MyrmopaediaProperties;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.inventory.ContainerMyrmopaedia;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.lib.Strings;
import vivadaylight3.myrmecology.common.lib.Time;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiMyrmopaedia extends GuiContainer {

    private InventoryItem inventory;
    private ContainerMyrmopaedia container;
    private ItemStack myrmopaedia;

    private Icon antIcon;

    private Player player;

    private static final EnumChatFormatting KEY_CHAT_FORMAT = EnumChatFormatting.BLUE;
    private static final EnumChatFormatting VALUE_CHAT_FORMAT = EnumChatFormatting.WHITE;

    private int infoButtonWidth = 45;
    private int infoButtonHeight = 15;

    private GuiButtonSizeable button1Names;
    private GuiButtonSizeable button2Info;
    private GuiButtonSizeable button3Breeding;
    private GuiButtonSizeable button4Ants;

    private GuiButtonSizeable portfolio1;
    private GuiButtonSizeable portfolio2;
    private GuiButtonSizeable portfolio3;
    private GuiButtonSizeable portfolio4;
    private GuiButtonSizeable portfolio5;
    private GuiButtonSizeable portfolio6;
    private GuiButtonSizeable portfolio7;

    private GuiButtonSizeable buttonSectionPortfolio;
    private GuiButtonSizeable buttonSectionAntInfo;

    private String selectedScreen = "ants";
    private String selectedSection = "info";

    // TODO
    private int buttonXOffset;
    private int buttonYOffset;

    private int buttonID;

    private ItemAnt selectedAnt;

    private ScaledResolution scaledRes;

    public GuiMyrmopaedia(ContainerMyrmopaedia parcontainer,
	    EntityPlayer parPlayer) {

	super(new ContainerMyrmopaedia(parcontainer.inventory, parPlayer));
	this.player = (Player) parPlayer;
	this.inventory = parcontainer.inventory;
	this.container = parcontainer;
	this.myrmopaedia = parcontainer.containerStack;
	this.selectedScreen = "ants";
	selectedAnt = null;

	xSize = 248;
	ySize = 255;

    }

    @Override
    public void initGui() {
	super.initGui();

	this.displayButtons();
	this.displayButtons();

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

	} else if (par1GuiButton == buttonSectionAntInfo) {

	    this.selectedSection = "info";

	} else if (par1GuiButton == buttonSectionPortfolio) {

	    this.selectedSection = "portfolio";

	} else if (par1GuiButton == portfolio1) {

	    this.selectedScreen = "1";

	} else if (par1GuiButton == portfolio2) {

	    this.selectedScreen = "2";

	} else if (par1GuiButton == portfolio3) {

	    this.selectedScreen = "3";

	} else if (par1GuiButton == portfolio4) {

	    this.selectedScreen = "4";

	} else if (par1GuiButton == portfolio5) {

	    this.selectedScreen = "5";

	} else if (par1GuiButton == portfolio6) {

	    this.selectedScreen = "6";

	} else if (par1GuiButton == portfolio7) {

	    this.selectedScreen = "7";

	}

    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
	super.drawScreen(par1, par2, par3);

	if (this.selectedScreen != "names" && this.selectedScreen != "info"
		&& this.selectedScreen != "breeding"
		&& this.selectedScreen != "ants") {

	    if (Myrmecology.proxy.myrmopaedia.pages[Integer
		    .valueOf(selectedScreen)].getIconItemStacks().length > 0) {

		this.drawImage(Myrmecology.proxy.myrmopaedia.pages[Integer
			.valueOf(selectedScreen)].getIconItemStacks()[0], 290,
			120, 48, 48);

	    }

	}
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {

	printKeyAndValue(EnumChatFormatting.AQUA + "Selected Screen: ",
		this.selectedScreen, 13);

	int a = (this.width - 610) / 2;

	if (this.selectedScreen != "names" && this.selectedScreen != "info"
		&& this.selectedScreen != "breeding"
		&& this.selectedScreen != "ants") {

	    this.fontRenderer.drawSplitString(
		    VALUE_CHAT_FORMAT
			    + Myrmecology.proxy.myrmopaedia.pages[Integer
				    .valueOf(selectedScreen)].getText()[0], a,
		    24, 116, 4210752);

	}

	if (this.selectedScreen == "names" && this.selectedAnt != null) {

	    printKeyAndValue("Species: ", this.selectedAnt.getSpeciesName(), 30);

	    printKeyAndValue("Binomial Name: ",
		    this.selectedAnt.getSpeciesBinomialName(), 45);

	    printKeyAndValue("Behaviour: ",
		    this.selectedAnt.getBehaviourDesc(), 60);

	} else if (this.selectedScreen == "info" && this.selectedAnt != null) {

	    printKeyAndValue(
		    "Lifetime: ",
		    ""
			    + Time.getMinutesFromTicks(this.selectedAnt
				    .getLifetime()), 30);

	    printKeyAndValue(
		    "Maturing Time: ",
		    ""
			    + Time.getMinutesFromTicks(this.selectedAnt
				    .getMaturingTime()), 45);

	    printKeyAndValue("Fertility: ",
		    "" + this.selectedAnt.getFertility(), 60);

	    printKeyAndValue("Winged: ", "" + this.selectedAnt.getWinged(), 75);

	    printKeyAndValue("Nocturnal: ", "" + this.selectedAnt.getWinged(),
		    90);

	    this.printBiomes();

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
				    + this.selectedAnt.getSpeciesName()
					    .replaceAll(" Ant", "")
				    + EnumChatFormatting.WHITE
				    + " + "
				    + EnumChatFormatting.BLUE
				    + partner.getSpeciesName().replaceAll(
					    " Ant", "")
				    + EnumChatFormatting.WHITE
				    + " = "
				    + EnumChatFormatting.YELLOW
				    + ((BreedingRecipe) breedingList.toArray()[k])
					    .getAntOutput().getSpeciesName()
					    .replaceAll(" Ant", ""), 13,
			    30 + offset);

		    control = 1;

		    offset += 13;

		} else {

		    control = 0;

		}

	    }
	}

    }

    private void displayButtons() {

	this.buttonList.clear();

	if (this.selectedSection.equals("info")) {

	    this.buttonList.add(this.button1Names = new GuiButtonSizeable(0,
		    this.width / 2 - 85, this.height / 2 + 20, "Names",
		    infoButtonWidth - 4, infoButtonHeight));
	    this.buttonList.add(this.button2Info = new GuiButtonSizeable(1,
		    this.width / 2 - 40, this.height / 2 + 20, "Info",
		    infoButtonWidth - 10, infoButtonHeight));
	    this.buttonList.add(this.button3Breeding = new GuiButtonSizeable(2,
		    this.width / 2, this.height / 2 + 20, "Breeding",
		    infoButtonWidth + 8, infoButtonHeight));

	    this.buttonID = 2;

	} else if (this.selectedSection.equals("portfolio")) {
	    // TODO
	    this.buttonList.add(this.portfolio1 = new GuiButtonSizeable(5,
		    this.width / 2 - 85, this.height / 2 + 20, "1",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	    this.buttonList.add(this.portfolio2 = new GuiButtonSizeable(6,
		    this.width / 2 - 66, this.height / 2 + 20, "2",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	    this.buttonList.add(this.portfolio3 = new GuiButtonSizeable(7,
		    this.width / 2 - 47, this.height / 2 + 20, "3",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	    this.buttonList.add(this.portfolio4 = new GuiButtonSizeable(8,
		    this.width / 2 - 28, this.height / 2 + 20, "4",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	    this.buttonList.add(this.portfolio5 = new GuiButtonSizeable(9,
		    this.width / 2 - 9, this.height / 2 + 20, "5",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	    this.buttonList.add(this.portfolio6 = new GuiButtonSizeable(10,
		    this.width / 2 + 10, this.height / 2 + 20, "6",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	    this.buttonList.add(this.portfolio7 = new GuiButtonSizeable(10,
		    this.width / 2 + 29, this.height / 2 + 20, "7",
		    infoButtonWidth / 2 - 3, infoButtonHeight));

	}
	/*
	 * if(this.selectedScreen.equals("portfolio")){
	 * 
	 * this.buttonSectionPortfolio.setWidth(this.buttonSectionPortfolio.getWidth
	 * () + 3); this.buttonSectionPortfolio.xPosition =
	 * this.buttonSectionPortfolio.xPosition - 3;
	 * 
	 * }else if(this.selectedScreen.equals("info")){
	 * 
	 * this.buttonSectionAntInfo.setWidth(this.buttonSectionAntInfo.getWidth(
	 * ) + 3); this.buttonSectionAntInfo.xPosition =
	 * this.buttonSectionAntInfo.xPosition - 3;
	 * 
	 * }
	 */

	this.buttonList.add(buttonSectionPortfolio = new GuiButtonSizeable(3,
		(this.width / 2) + 73, (this.height / 2) - 120, "Portfolio",
		infoButtonWidth + 12, infoButtonHeight));
	this.buttonList.add(buttonSectionAntInfo = new GuiButtonSizeable(4,
		(this.width / 2) + 73, (this.height / 2) - 104, "Ant Info",
		infoButtonWidth + 12, infoButtonHeight));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateScreen() {

	super.updateScreen();
	this.displayButtons();
	this.displayButtons();

	container.update();

	if (this.selectedAnt != null) {

	    this.antIcon = this.selectedAnt.getIconFromDamage(Metadata
		    .getMetaQueen());

	}

	if (this.antIcon != null) {

	}

	if (this.getAnt() != null) {

	    this.selectedAnt = this.getAnt();

	    MyrmopaediaProperties.addAntToMyrmopaedia(
		    this.container.containerStack, this.selectedAnt,
		    this.player);

	    this.antIcon = this.getAnt().getIconFromDamage(
		    this.inventory.getStackInSlot(0).getItemDamage());

	    // this.container.inventory.decrStackSize(0, 1);

	}// TODO

	int offset = 23;

	if (this.selectedScreen == "ants") {

	    /*
	     * 
	     * for (int i = 0; i <
	     * AntopediaProperties.getAntopediaAnts(this.container
	     * .containerStack).toArray().length; i++) {
	     * 
	     * this.buttonList.add(new GuiButtonAnt(this.buttonID, this.width /
	     * 3, 24 + offset, ((ItemAnt)
	     * AntopediaProperties.getAntopediaAnts(this
	     * .container.containerStack).toArray()[i]) .getSpeciesName(), 100,
	     * 20, (ItemAnt)
	     * AntopediaProperties.getAntopediaAnts(this.container.
	     * containerStack).toArray()[i]));
	     * 
	     * this.buttonID++;
	     * 
	     * offset += 23;
	     * 
	     * }
	     */

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

    // TODO
    private void printBiomes() {

	if (this.selectedAnt.getAntBiomes() != null) {

	    ArrayList<String> biomeStringList = new ArrayList<String>();

	    for (int k = 0; k < this.selectedAnt.getAntBiomes().length; k++) {

		biomeStringList.add(Strings.getBiomeString(this.selectedAnt
			.getAntBiomes()[k]));

	    }

	    printKeyAndValue("Biomes: ", "---", 105);

	}

    }

    private void printKeyAndValue(String key, String value, int posY) {

	this.drawString(KEY_CHAT_FORMAT + key + VALUE_CHAT_FORMAT + value, 13,
		posY);

    }

    private void drawIcon(Icon icon, int posX, int posY) {

	drawTexturedModelRectFromIcon(posX, posY, icon, 16, 16);

    }

    @Override
    public void onGuiClosed() {
	super.onGuiClosed();

	if (inventory.getStackInSlotOnClosing(0) == null) {

	    return;

	}

	if (Environment.inventoryCanHold(inventory.getStackInSlotOnClosing(0),
		this.mc.thePlayer.inventory.mainInventory, 1)) {

	    Environment.addItemStackToInventory(
		    inventory.getStackInSlotOnClosing(0),
		    this.mc.thePlayer.inventory.mainInventory, 1, null);

	} else {

	    Environment.spawnItem(inventory.getStackInSlotOnClosing(0),
		    this.mc.thePlayer.worldObj, this.mc.thePlayer.posX,
		    this.mc.thePlayer.posY, this.mc.thePlayer.posZ);

	}

	/*
	 * ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
	 * DataOutputStream dos = new DataOutputStream(bos);
	 * 
	 * MyrmecologyPacket packet = new MyrmecologyPacket();
	 * 
	 * try { packet.writeItemStack(inventory.getStackInSlotOnClosing(0),
	 * dos);
	 * packet.writeNBTTagCompound2(inventory.getStackInSlotOnClosing(0)
	 * .stackTagCompound, dos); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * packet.channel = "Myrmecology_Myrmopaedia"; packet.data =
	 * bos.toByteArray(); packet.length = bos.size();
	 * 
	 * PacketHandler.handleMyrmopaediaDropPacket(packet,
	 * (EntityClientPlayerMP) player, inventory);
	 */

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
	    int par3) {
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.renderEngine.bindTexture(Resources.GUI_ANTOPEDIA);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	int i1;

    }

    private void drawImage(ResourceLocation path, int x, int y, int x2, int y2) {

	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.getTextureManager().bindTexture(path);
	Tessellator tessellator = Tessellator.instance;
	tessellator.startDrawingQuads();
	tessellator.addVertexWithUV(x + 0, y + y2, this.zLevel, 0, 1);// Bottom
								      // left
	tessellator.addVertexWithUV(x + x2, y + y2, this.zLevel, 1, 1);// Bottom
								       // right
	tessellator.addVertexWithUV(x + x2, y + 0, this.zLevel, 1, 0);// Top
								      // right
	tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, 0, 0); // Top
								      // left
	tessellator.draw();

    }

}
