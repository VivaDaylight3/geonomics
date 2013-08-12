package vivadaylight3.myrmecology.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.item.ItemExtractor;
import vivadaylight3.myrmecology.common.lib.Ants;
import vivadaylight3.myrmecology.common.lib.IAntHill;
import vivadaylight3.myrmecology.common.lib.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockAntHill extends Block implements IAntHill{
    
    public BlockAntHill(int par1, Material material) {
	super(par1, material);
	
	prepareBlock();
	
	Register.addHill(this);
	
    }
    
    private void prepareBlock(){
	
	setUnlocalizedName(this.getHillSubName());
	setCreativeTab(Register.tabMyrmecology);
	setStepSound(Block.soundGrassFootstep);
	setHardness(1.0F);
	setResistance(1.0F);
	func_111022_d(Resources.TEXTURE_PREFIX+this.getHillSubName());
	
    }
    
    public String getUnlocalizedName(ItemStack itemStack) {
	
	return this.getUnlocalizedName() + getHillSubName();
	
    }
    
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata){
	
	return false;
	
    }
    
    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta){
	
	ItemStack tool = player.getCurrentEquippedItem();
	
	if(tool != null){
	
	    if(tool.getItem() instanceof ItemExtractor){
	    
		return true;
	    
	    }
	    
	}
	
	return false;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3){
	
	return this.getAnt().itemID;
	
    }
    
    @Override
    public int damageDropped(int par1)
    {
        return Ants.getMetaLarva();
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random){
	
	return this.getDropQuantity(null, 0, 0, 0);
	
    }
    
    public ItemAnt getAnt() {
	return Register.antForest;
    }
    
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Default";
    }
    
    public String getHillSubName() {
	return "default";
    }
    
    public int getDropQuantity(World world, int x, int y, int z) {
	// TODO Auto-generated method stub
	return 2;
    }
    
    public BiomeGenBase[] getHillBiomes() {
	
	BiomeGenBase[] biomes = new BiomeGenBase[1];
	
	biomes[0] = BiomeGenBase.forest;
	
	return biomes;
	
    }
    
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return false;
    }
    
    public int[] getRequiredTouchingBlocks() {
	return null;
    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 0;
    }
    
}
