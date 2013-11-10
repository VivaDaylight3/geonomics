package vivadaylight3.myrmecology.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import cpw.mods.fml.common.FMLLog;

public class RendererAntChest extends TileEntitySpecialRenderer {

    private static final ResourceLocation RESOURCE_SINGLE = Resources.BLOCK_ANTCHEST;
    private static final ResourceLocation RESOURCE_DOUBLE = Resources.BLOCK_ANTCHEST_DOUBLE;

    /** The normal small chest model. */
    private ModelChest chestModel = new ModelChest();

    /** The large double chest model. */
    private ModelChest largeChestModel = new ModelLargeChest();

    public RendererAntChest() {
    }

    public void renderTileEntityAntChestAt(
	    TileEntityAntChest par1TileEntityAntChest, double par2,
	    double par4, double par6, float par8) {
	int i;

	if (!par1TileEntityAntChest.hasWorldObj()) {
	    i = 0;
	} else {
	    Block block = par1TileEntityAntChest.getBlockType();
	    i = par1TileEntityAntChest.getBlockMetadata();

	    if (block instanceof BlockChest && i == 0) {
		try {
		    ((BlockChest) block).unifyAdjacentChests(
			    par1TileEntityAntChest.getWorldObj(),
			    par1TileEntityAntChest.xCoord,
			    par1TileEntityAntChest.yCoord,
			    par1TileEntityAntChest.zCoord);
		} catch (ClassCastException e) {
		    FMLLog.severe(
			    "Attempted to render a chest at %d,  %d, %d that was not a chest",
			    par1TileEntityAntChest.xCoord,
			    par1TileEntityAntChest.yCoord,
			    par1TileEntityAntChest.zCoord);
		}
		i = par1TileEntityAntChest.getBlockMetadata();
	    }

	    par1TileEntityAntChest.checkForAdjacentChests();
	}

	if (par1TileEntityAntChest.adjacentChestZNeg == null
		&& par1TileEntityAntChest.adjacentChestXNeg == null) {
	    ModelChest modelchest = null;

	    if (par1TileEntityAntChest.adjacentChestXPos == null
		    && par1TileEntityAntChest.adjacentChestZPosition == null) {
		modelchest = this.chestModel;
		this.bindTexture(RESOURCE_SINGLE);
	    } else {

		this.bindTexture(RESOURCE_DOUBLE);
	    }

	    GL11.glPushMatrix();
	    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    GL11.glTranslatef((float) par2, (float) par4 + 1.0F,
		    (float) par6 + 1.0F);
	    GL11.glScalef(1.0F, -1.0F, -1.0F);
	    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	    short short1 = 0;

	    if (i == 2) {
		short1 = 180;
	    }

	    if (i == 3) {
		short1 = 0;
	    }

	    if (i == 4) {
		short1 = 90;
	    }

	    if (i == 5) {
		short1 = -90;
	    }

	    if (i == 2 && par1TileEntityAntChest.adjacentChestXPos != null) {
		GL11.glTranslatef(1.0F, 0.0F, 0.0F);
	    }

	    if (i == 5 && par1TileEntityAntChest.adjacentChestZPosition != null) {
		GL11.glTranslatef(0.0F, 0.0F, -1.0F);
	    }

	    GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
	    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	    float f1 = par1TileEntityAntChest.prevLidAngle
		    + (par1TileEntityAntChest.lidAngle - par1TileEntityAntChest.prevLidAngle)
		    * par8;
	    float f2;

	    if (par1TileEntityAntChest.adjacentChestZNeg != null) {
		f2 = par1TileEntityAntChest.adjacentChestZNeg.prevLidAngle
			+ (par1TileEntityAntChest.adjacentChestZNeg.lidAngle - par1TileEntityAntChest.adjacentChestZNeg.prevLidAngle)
			* par8;

		if (f2 > f1) {
		    f1 = f2;
		}
	    }

	    if (par1TileEntityAntChest.adjacentChestXNeg != null) {
		f2 = par1TileEntityAntChest.adjacentChestXNeg.prevLidAngle
			+ (par1TileEntityAntChest.adjacentChestXNeg.lidAngle - par1TileEntityAntChest.adjacentChestXNeg.prevLidAngle)
			* par8;

		if (f2 > f1) {
		    f1 = f2;
		}
	    }

	    f1 = 1.0F - f1;
	    f1 = 1.0F - f1 * f1 * f1;
	    modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
	    modelchest.renderAll();
	    GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	    GL11.glPopMatrix();
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
	    double par4, double par6, float par8) {
	this.renderTileEntityAntChestAt((TileEntityAntChest) par1TileEntity,
		par2, par4, par6, par8);
    }

}
