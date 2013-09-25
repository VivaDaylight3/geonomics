package vivadaylight3.myrmecology.common.item;

import java.util.List;

import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.Icon;

public class ToolExtractor extends ItemTool {

    private static final Block[] blocks = new Block[] { Block.woodDoubleSlab };

    public ToolExtractor(int par1) {
	super(par1, 1.0F, EnumToolMaterial.WOOD, blocks);
	setCreativeTab(Register.tabMyrmecology);
	func_111206_d(Resources.TEXTURE_PREFIX + Reference.ITEM_EXTRACTOR_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
	this.itemIcon = par1IconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.ITEM_EXTRACTOR_NAME);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
	    EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

	par3List.add("Extracts ants from ant hills");

    }

    @Override
    public Icon getIconFromDamage(int par1) {
	return this.itemIcon;
    }

}
