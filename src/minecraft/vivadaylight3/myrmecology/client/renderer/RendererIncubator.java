package vivadaylight3.myrmecology.client.renderer;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererIncubator extends TileEntitySpecialRenderer implements
	ISimpleBlockRenderingHandler {

    private ModelChest model;
    private Random rand;
    private RenderBlocks renderB;
    private RenderItem renderI;

    public RendererIncubator() {

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

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y,
	    double z, float f) {

	ItemStack itemstack = ((TileEntityIncubator) tileentity).getContents()[0];

	if (itemstack != null) {

	    if (itemstack.getItem() instanceof ItemAnt
		    && itemstack.getItemDamage() == Metadata.getMetaLarva()) {

		EntityItem item = new EntityItem(tileentity.worldObj);
		item.setEntityItemStack(itemstack);
		item.hoverStart = 0f;
		float blockScale = 0.8f; // Size of item

		float timeD = (float) (360.0 * (double) (System
			.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

		GL11.glPushMatrix();
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glTranslatef((float) x, (float) y, (float) z);

		GL11.glTranslatef(0.5f, 0.5f, 0.5f);
		GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(blockScale, blockScale, blockScale);

		renderI.doRenderItem(item, 0, 0, 0, 0, 0);

		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glPopMatrix();

	    }

	} else {

	}

    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
	    RenderBlocks renderer) {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
	    Block block, int modelId, RenderBlocks renderer) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean shouldRender3DInInventory() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int getRenderId() {
	// TODO Auto-generated method stub
	return 0;
    }

}
