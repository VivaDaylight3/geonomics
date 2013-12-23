package vivadaylight3.myrmecology.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.inventory.ContainerInfuser;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInfuser extends GuiContainer {

    public TileEntityInfuser tile;

    public GuiInfuser(EntityPlayer player, TileEntityInfuser tileEntity,
	    World world, int x, int y, int z) {
	super(new ContainerInfuser(player.inventory, tileEntity));
	this.tile = tileEntity;
	player.addStat(Register.achieveBreedAnts, 1);
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
		this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2,
		6 + 2, 4210752);
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
	this.mc.renderEngine.bindTexture(Resources.GUI_ANTFARM);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

    }
}
