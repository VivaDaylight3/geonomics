package vivadaylight3.myrmecology.api.block;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.item.ToolExtractor;
import vivadaylight3.myrmecology.common.lib.Maths;
import vivadaylight3.myrmecology.common.lib.Resources;

/**
 * Extend this class to create a new ant hill, it is suggested that you override
 * prepareBlock() if you want to set a non-standard unlocalised name, creative
 * tab, step sound, hardness, resistance and resource location.
 * 
 * @author samueltebbs
 */
public class BlockAntHill extends Block {

    private IIcon icon;

    public BlockAntHill(Material material) {
	super(Register.antHill);

	prepareBlock();

	Register.addHill(this);

    }
    
    public BlockAntHill(){
    	this(Material.ground);
    }

    private void prepareBlock() {

	setBlockName(this.getHillSubName());
	setCreativeTab(Register.tabMyrmecology);
	setStepSound(Block.soundTypeGlass);
	setHardness(1.0F);
	setResistance(1.0F);
	// func_111022_d(Resources.TEXTURE_PREFIX + this.getHillSubName());

    }
    
    @Override
    public void registerBlockIcons(IIconRegister register) {

	this.icon = register.registerIcon(Resources.TEXTURE_PREFIX
		+ this.getHillSubName());

    }

    @Override
    public IIcon getIcon(int par1, int par2) {
	return this.icon;
    }

    public String getUnlocalizedName(ItemStack itemStack) {

	return this.getUnlocalizedName() + getHillSubName();

    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x,
	    int y, int z, int metadata) {

	return false;

    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {

	ItemStack tool = player.getCurrentEquippedItem();

	if (tool != null) {

	    if (tool.getItem() instanceof ToolExtractor) {

		player.addStat(Register.achieveExtractAnts, 1);

		return true;

	    }

	}

	return false;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
    	ArrayList<ItemStack> res = new ArrayList<ItemStack>();
    	res.add(new ItemStack(this.getItemDropped(0, new Random(), 0)));
    	return res;
    
    }

    @Override
    public int damageDropped(int par1) {
	return Metadata.getMetaLarva();
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {

	return this.getDropQuantity();

    }

    /**
     * Gets the ant that belongs to this ant hill
     * 
     * @return extends ItemAnt
     */
    public ItemAnt getAnt() {
	return null;
    }

    /**
     * Gets the in-game name used for the ant hill
     * 
     * @return String
     */
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Default";
    }

    /**
     * Gets the internal name used for the ant hill
     * 
     * @return String
     */
    public String getHillSubName() {
	return "default";
    }

    /**
     * Gets the biomes in which this hill can generate. Not used natively
     * 
     * @return BiomeGenBase[]
     */
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[1];

	biomes[0] = BiomeGenBase.forest;

	return biomes;

    }

    /**
     * Returns true if the hill uses Myrmecology's own world generator
     * 
     * @param meta
     * @return boolean
     */
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return false;
    }

    /**
     * Returns the Y offset from the height value at the x and z parameters
     * 
     * @param world
     * @param x
     * @param currentHeightValue
     * @param z
     * @return
     */
    public int getGenerationHeightOffset(World world, int x, int currentHeight,
	    int z) {

	return 0;

    }

    /**
     * Returns true if the ant hill should generate underground (only used if
     * usesNativeGeneration == true)
     * 
     * @return boolean
     */
    public boolean isUnderground() {

	return false;

    }

    /**
     * Returns true if the ant hill can generate at the given coords, only used
     * if usesNativeGeneration returns true
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canGenerate(World world, int x, int y, int z) {

	if (Maths.chanceOf(1, 4)) {

	    int radius = 1;

	    int[] blocks = new int[radius];

	    if (world.getBlock(x, y - 1, z) != Block.blockRegistry.getObject("ice")
		    && world.getBlock(x, y - 1, z) != Block.blockRegistry.getObject("water")
		    && world.getBlock(x, y - 1, z) != Block.blockRegistry.getObject("flowing_water")) {

		for (int k = 0; k < this.getHillBiomes().length; k++) {

		    if (world.getBiomeGenForCoords(x, z) == this
			    .getHillBiomes()[k]) {

			return true;

		    }

		}

	    }

	}

	return false;

    }

    /**
     * Gets the amount of larvae to drop when extracting from the ant hill
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return int
     */
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

}
