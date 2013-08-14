package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.lib.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

public class BlockIncubator extends Block {
    
    private String name;
    
    private boolean isPowered;
    
    private Icon iconTop;
    private Icon iconSideOn;
    private Icon iconSideOff;
    
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
    
    @Override
    public void registerIcons(IconRegister iconRegister){
    	
		iconTop = iconRegister.registerIcon(Resources.TEXTURE_PREFIX+Register.BLOCK_INCUBATOR_NAME+"_Top");
		iconSideOn = iconRegister.registerIcon(Resources.TEXTURE_PREFIX+Register.BLOCK_INCUBATOR_NAME+"_Side_On");
		iconSideOff = iconRegister.registerIcon(Resources.TEXTURE_PREFIX+Register.BLOCK_INCUBATOR_NAME+"_Side_Off");
        
    }
    
    @Override
	public Icon getIcon(int side, int metadata)
	{

    	if(side == 0 || side == 1){
    		
    		return iconTop;
    		
    	}else{
    		
    		if(this.isPowered){
    			
    			return iconSideOn;
    			
    		}else{
    			
    			return iconSideOff;
    			
    		}
    		
    	}
    	
    	
	}
    
    /*
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack){

		int metadata = world.getBlockMetadata(x, y, z);
		int angle = MathHelper.floor_double((entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int change = 0;

		switch(angle){

			case 0:
				change = 0;
				break;

			case 1:
				change = 1;
				break;

			case 2:
				change = 2;
				break;

			case 3:
				change = 3;
				break;

		}

		world.setBlockMetadataWithNotify(x, y, z, ENERGISER_BLOCK_METADATA + change, 3);

	}
	*/
    
    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side){
    	
    	return true;
    	
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer player, int par6, float par7, float par8, float par9) {
	
	if (!player.isSneaking() && world.isRemote) {
	    
	    player.openGui(Myrmecology.instance, 2, world, x, y, z);
	    
	    return true;
	    
	}
	
	return false;
	
    }
    
}
