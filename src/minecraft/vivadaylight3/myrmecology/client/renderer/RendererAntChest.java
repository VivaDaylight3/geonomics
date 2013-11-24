package vivadaylight3.myrmecology.client.renderer;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.Random;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

import com.google.common.primitives.SignedBytes;

public class RendererAntChest extends TileEntitySpecialRenderer {

    private static ResourceLocation[] resources = { Resources.BLOCK_ANTCHEST,
	    Resources.BLOCK_ANTCHEST_DOUBLE };
    private Random random;

    private RenderBlocks renderBlocks;

    private RenderItem itemRenderer;

    public RendererAntChest() {
	model = new ModelChest();
	random = new Random();
	renderBlocks = new RenderBlocks();
	itemRenderer = new RenderItem() {
	    @Override
	    public byte getMiniBlockCount(ItemStack stack) {
		return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32,
			15) + 1);
	    }

	    @Override
	    public byte getMiniItemCount(ItemStack stack) {
		return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32,
			7) + 1);
	    }

	    @Override
	    public boolean shouldBob() {
		return false;
	    }

	    @Override
	    public boolean shouldSpreadItems() {
		return false;
	    }
	};
	itemRenderer.setRenderManager(RenderManager.instance);
    }

    public void render(TileEntityAntChest tile, double x, double y, double z,
	    float partialTick) {
	if (tile == null) {
	    return;
	}
	int facing = 3;
	if (tile != null && tile.getWorldObj() != null) {
	    facing = tile.getFacing();
	    int typ = tile.getWorldObj().getBlockMetadata(tile.xCoord,
		    tile.yCoord, tile.zCoord);
	}
	bindTexture(resources[0]);
	glPushMatrix();
	glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
	glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
	glScalef(1.0F, -1F, -1F);
	glTranslatef(0.5F, 0.5F, 0.5F);
	int k = 0;
	if (facing == 2) {
	    k = 180;
	}
	if (facing == 3) {
	    k = 0;
	}
	if (facing == 4) {
	    k = 90;
	}
	if (facing == 5) {
	    k = -90;
	}
	glRotatef(k, 0.0F, 1.0F, 0.0F);
	glTranslatef(-0.5F, -0.5F, -0.5F);
	float lidangle = tile.prevLidAngle
		+ (tile.lidAngle - tile.prevLidAngle) * partialTick;
	lidangle = 1.0F - lidangle;
	lidangle = 1.0F - lidangle * lidangle * lidangle;
	model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
	// Render the chest itself
	model.renderAll();
	glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
	glPopMatrix();
	glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity tileentity, double x, double y,
	    double z, float partialTick) {
	render((TileEntityAntChest) tileentity, x, y, z, partialTick);
    }

    private ModelChest model;
}
