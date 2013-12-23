package vivadaylight3.myrmecology.client.renderer;

import java.util.Random;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.util.AntProperties;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;

public class RendererAntFarm extends TileEntitySpecialRenderer {

    private ModelChest model;
    private Random rand;
    private RenderBlocks renderB;
    private RenderItem renderI;

    public RendererAntFarm() {

	renderB = new RenderBlocks();
	rand = new Random();
	renderI = new RenderItem() {

	    @Override
	    public boolean shouldBob() {

		return false;

	    }

	};

	renderI.setRenderManager(RenderManager.instance);

    }

    private void renderSides(TileEntity tileentity, double x, double y, double z) {

	GL11.glPushMatrix();
	GL11.glDisable(2896 /* GL_LIGHTING */);
	GL11.glRotatef(0.0f, 0.0F, 1.0F, 0.0F);
	renderB.renderFaceYNeg(Register.blockAntFarm, x, y, z,
		Register.blockAntFarm.getIcon(
			0,
			Myrmecology.proxy.getClientWorld().getBlockMetadata(
				tileentity.xCoord, tileentity.yCoord,
				tileentity.zCoord)));
	GL11.glEnable(2896 /* GL_LIGHTING */);
	GL11.glPopMatrix();

	// renderB.renderFaceYNeg(Register.blockIncubator, x, y, z,
	// Register.blockIncubator.getIcon(0,
	// Myrmecology.proxy.getClientWorld().getBlockMetadata(tileentity.xCoord,
	// tileentity.yCoord, tileentity.zCoord)));
	/*
	 * GL11.glPushMatrix(); GL11.glDisable(2896 /* GL_LIGHTING );
	 * GL11.glTranslatef((float) x, (float) y, (float) z);
	 * GL11.glTranslatef(0f, 0.35f, 0f);
	 * 
	 * GL11.glBegin(GL11.GL_QUADS); GL11.glColor3f(0, 0, 0.5f);
	 * GL11.glVertex3f(1, 0, 1); GL11.glVertex3f(1, 0, 0);
	 * GL11.glVertex3f(0, 0, 0); GL11.glVertex3f(0, 0, 1); GL11.glEnd();
	 * 
	 * GL11.glEnable(2896 /* GL_LIGHTING ); GL11.glPopMatrix();
	 */

    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y,
	    double z, float f) {

	// renderSides(tileentity, x, y, z);

	if (tileentity != null) {

	    ItemStack itemstack = ((TileEntityAntFarm) tileentity)
		    .getContents()[1];

	    if (itemstack != null
		    && tileentity.getDistanceFrom(
			    this.tileEntityRenderer.playerX,
			    this.tileEntityRenderer.playerY,
			    this.tileEntityRenderer.playerZ) <= Register.incubatorLarvaRenderDistance) {

		if (itemstack.getItem() instanceof ItemAnt
			&& itemstack.getItemDamage() == Metadata.getMetaQueen()
			&& tileentity.worldObj.isAirBlock(tileentity.xCoord,
				tileentity.yCoord + 1, tileentity.zCoord)
			&& AntProperties.getMated(itemstack)) {

		    EntityItem item = new EntityItem(tileentity.worldObj);
		    item.setEntityItemStack(itemstack);
		    item.hoverStart = 0f;
		    float blockScale = 0.8f; // Size of item

		    float timeD = (float) (360.0 * (double) (System
			    .currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

		    GL11.glPushMatrix();
		    GL11.glDisable(2896 /* GL_LIGHTING */);
		    GL11.glTranslatef((float) x, (float) y + 1, (float) z);

		    GL11.glTranslatef(0.5f, 0.0f, 0.5f);
		    GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
		    GL11.glScalef(blockScale, blockScale, blockScale);

		    renderI.doRenderItem(item, 0, 0, 0, 0, 0);

		    GL11.glEnable(2896 /* GL_LIGHTING */);
		    GL11.glPopMatrix();

		}

	    }

	} else {

	}

    }

}
