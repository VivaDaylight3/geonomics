package vivadaylight3.myrmecology.common.blocks;

import vivadaylight3.myrmecology.common.Myrmecology;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAntFarm extends Block
{

	public BlockAntFarm(int par1, String par2Name)
	{
		super(par1, Material.iron);
		setUnlocalizedName(par2Name);
		setCreativeTab(Myrmecology.tabMyrmecology);
		setStepSound(Block.soundMetalFootstep);
		setHardness(1.0F);
		setResistance(1.0F);
	}

}
