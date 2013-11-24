package vivadaylight3.myrmecology.common.block;

import vivadaylight3.myrmecology.common.Register;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPheromone extends Block {

    public BlockPheromone(int par1) {
	super(par1, Material.ground);
	setCreativeTab(Register.tabMyrmecology);
    }

}
