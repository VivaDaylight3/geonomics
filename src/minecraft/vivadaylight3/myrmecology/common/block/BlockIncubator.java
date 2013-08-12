package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.lib.Register;

public class BlockIncubator extends Block {
    
    private String name;
    
    public BlockIncubator(int par1, String par2Name) {
	super(par1, Material.wood);
	setStepSound(Block.soundWoodFootstep);
	setUnlocalizedName(Register.BLOCK_INCUBATOR_NAME);
	setCreativeTab(Register.tabMyrmecology);
	setHardness(1.0F);
	setResistance(1.0F);
	name = par2Name;
	func_111022_d(Myrmecology.MOD_ID_LOWER + name);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer player, int par6, float par7, float par8, float par9) {
	
	if (!player.isSneaking() && world.isRemote) {
	    
	    player.openGui(Myrmecology.instance, 2, world, x, y, z);
	    
	    return true;
	    
	}
	
	return false;
	
    }
    
}
