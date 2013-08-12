package vivadaylight3.myrmecology.common.block.hills;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.common.block.BlockAntHill;
import vivadaylight3.myrmecology.common.lib.AntDamageSource;

public class AntHillJungle extends BlockAntHill {

    public AntHillJungle(int par1) {
	super(par1, Material.ground);
	// TODO Auto-generated constructor stub
    }
    
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity){
	
	par5Entity.attackEntityFrom(AntDamageSource.antBite, 1.0F);
	
    }
    
}
