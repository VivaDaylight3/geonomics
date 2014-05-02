package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class BlockPheromone extends Block {

    public BlockPheromone() {
	super(Material.ground);
	setCreativeTab(Register.tabMyrmecology);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister reg){
	
	this.blockIcon = reg.registerIcon(Resources.TEXTURE_PREFIX+"pheromone");
	
    }

}
