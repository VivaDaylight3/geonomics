package vivadaylight3.myrmecology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;

public class BlockIncubator extends Block {

    private String name;

    private boolean isPowered;

    private Icon iconTop;
    private Icon iconSideOn;
    private Icon iconSideOff;

    public BlockIncubator(int id, String name) {
		super(id, Material.wood);
		setStepSound(Block.soundWoodFootstep);
		setUnlocalizedName(Register.BLOCK_INCUBATOR_NAME);
		setCreativeTab(Register.tabMyrmecology);
		setHardness(1.0F);
		setResistance(1.0F);
		this.name = name;
		func_111022_d(Myrmecology.MOD_ID_LOWER + this.name);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
		iconTop = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + Register.BLOCK_INCUBATOR_NAME + "_Top");
		iconSideOn = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + Register.BLOCK_INCUBATOR_NAME + "_Side_On");
		iconSideOff = iconRegister.registerIcon(Resources.TEXTURE_PREFIX + Register.BLOCK_INCUBATOR_NAME + "_Side_Off");
    }

    @Override
    public Icon getIcon(int side, int metadata) {
		if (side == 0 || side == 1) {
		    return iconTop;
		}
		else {
		    if (this.isPowered) {
		    	return iconSideOn;
		    } 
		    else {
		    	return iconSideOff;
		    }
		}
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
    	return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if (world.isRemote) {
    		return true;
    	}
    	else {
    		TileEntity te = world.getBlockTileEntity(x, y, z);
    		if (te != null && te instanceof TileEntityIncubator && !player.isSneaking()) {
    		    player.openGui(Myrmecology.instance, Register.GUI_ID_INCUBATOR, world, x, y, z);
    		}
		    return true;
		}
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
    	return new TileEntityIncubator();
    }
}