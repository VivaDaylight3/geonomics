package vivadaylight3.myrmecology.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.common.container.ContainerAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;

@SideOnly(Side.CLIENT)
public class GuiAntFarm extends GuiContainer
{
	private World world;
	private int x;
	private int y;
	private int z;
	
    private TileEntityAntFarm tile;

    public GuiAntFarm(InventoryPlayer par1InventoryPlayer, TileEntityAntFarm tileEntity, World world, int x, int y, int z)
    {
        super(new ContainerAntFarm(par1InventoryPlayer, tileEntity));
        this.tile = tileEntity;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.tile.isInvNameLocalized() ? this.tile.getInvName() : StatCollector.translateToLocal(this.tile.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/furnace.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.tile.isBreeding())
        {
            i1 = this.tile.getBurnTimeRemaining();
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

    }
}
