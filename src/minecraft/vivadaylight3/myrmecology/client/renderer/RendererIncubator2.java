package vivadaylight3.myrmecology.client.renderer;

import vivadaylight3.myrmecology.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererIncubator2 implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
	    RenderBlocks renderer) {
	
	
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
	    Block block, int modelId, RenderBlocks renderer) {
	
	if(ClientProxy.incubatorRenderPass == 0){
	    
	    drawDiamond(Block.blockDiamond, x, y, z);
	    
	}else{
	    
	    renderer.renderStandardBlock(Block.glass, x, y, z);
	    
	}
	
	return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {
	
	return false;
    }

    @Override
    public int getRenderId() {
	return ClientProxy.incubatorRenderID;
    }
    
    public void drawDiamond(Block par1Block, int x, int y, int z)
    {
                
    }

}
