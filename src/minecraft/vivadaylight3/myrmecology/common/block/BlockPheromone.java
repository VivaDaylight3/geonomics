package vivadaylight3.myrmecology.common.block;

import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockPheromone extends Block {

    public BlockPheromone(int par1) {
	super(par1, Material.ground);
	setCreativeTab(Register.tabMyrmecology);
    }
    
    @Override
    public void registerIcons(IconRegister reg){
	
	this.blockIcon = reg.registerIcon(Resources.TEXTURE_PREFIX+"pheromone");
	
    }

}
