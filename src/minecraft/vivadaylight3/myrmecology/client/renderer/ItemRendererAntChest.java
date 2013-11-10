package vivadaylight3.myrmecology.client.renderer;

import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererAntChest implements IItemRenderer {

    private ModelChest modelChest;

    public ItemRendererAntChest() {

	modelChest = new ModelChest();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
	    ItemRendererHelper helper) {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

	TileEntityRenderer.instance.renderTileEntityAt(
		new TileEntityAntChest(), 0.0, 0.0, 0.0, 0.0f);

    }

}
