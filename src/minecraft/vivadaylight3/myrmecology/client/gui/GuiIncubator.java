package vivadaylight3.myrmecology.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.inventory.ContainerIncubator;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIncubator extends GuiContainer {

    private GuiButtonSizeable buttonQueen;
    private GuiButtonSizeable buttonDrone;
    private GuiButtonSizeable buttonWorker;

    private int buttonWidth = 10;
    private int buttonHeight = 15;

    private World world;

    public TileEntityIncubator tile;

    private EntityPlayer player;

    private ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();

    public GuiIncubator(EntityPlayer parPlayer, InventoryPlayer inventory,
	    TileEntityIncubator tileEntity, World parWorld, int x, int y, int z) {
	super(new ContainerIncubator(inventory, tileEntity));
	this.world = parWorld;
	this.tile = tileEntity;
	this.players.add(parPlayer);

	parPlayer.addStat(Register.achieveIncubateAnts, 1);
    }

    @Override
    public void initGui() {

	super.initGui();
	/*
	 * this.buttonList.clear();
	 * 
	 * this.buttonList.add(this.buttonQueen = new GuiButtonSizeable(2,
	 * this.width / 2 - 80, this.height / 2 - 65, "Q", buttonWidth + 8,
	 * buttonHeight)); this.buttonList.add(this.buttonDrone = new
	 * GuiButtonSizeable(2, this.width / 2 - 83 + (buttonWidth * 2),
	 * this.height / 2 - 65, "D", buttonWidth + 8, buttonHeight));
	 * 
	 * this.buttonList.add(this.buttonWorker = new GuiButtonSizeable(2,
	 * this.width / 2 - 65 + (buttonWidth * 2), this.height / 2 - 65, "W",
	 * buttonWidth + 8, buttonHeight));
	 */

    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {

	if (par1GuiButton == this.buttonQueen) {

	    this.sendResultAntMetaPacket(Metadata.getMetaQueen());

	} else if (par1GuiButton == this.buttonDrone) {

	    this.sendResultAntMetaPacket(Metadata.getMetaDrone());

	} else if (par1GuiButton == this.buttonWorker) {

	    this.sendResultAntMetaPacket(Metadata.getMetaWorker());

	}

    }

    @SideOnly(Side.CLIENT)
    private void sendResultAntMetaPacket(int meta) {

	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	DataOutputStream outputStream = new DataOutputStream(bos);
	try {
	    outputStream.writeInt(meta);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	Packet packet = new Packet250CustomPayload();
	packet.channel = Reference.MOD_CHANNEL_INCUBATOR;
	packet.data = bos.toByteArray();
	packet.length = bos.size();

	Side side = FMLCommonHandler.instance().getEffectiveSide();
	if (side == Side.SERVER) {
	    // We are on the server side.
	    EntityPlayerMP player2 = (EntityPlayerMP) this.player;
	} else if (side == Side.CLIENT) {

	    EntityClientPlayerMP player2 = (EntityClientPlayerMP) this.player;

	    player2.sendQueue.addToSendQueue(packet);

	} else {
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
	this.fontRendererObj.drawString(s,
		this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6,
		4210752);
	this.fontRendererObj.drawString(
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
	this.mc.renderEngine.bindTexture(Resources.GUI_INCUBATOR);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void onGuiClosed() {
	if (this.mc.thePlayer != null) {
	    this.inventorySlots.onContainerClosed(this.mc.thePlayer);
	    this.players.remove(this.mc.thePlayer);
	}
    }
}
