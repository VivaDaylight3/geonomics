package vivadaylight3.myrmecology.common.slot;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class SlotAntFarm extends Slot {
    /** The player that is using the GUI where this slot resides. */
    private EntityPlayer thePlayer;
    private int field_75228_b;
    
    public SlotAntFarm(EntityPlayer par1EntityPlayer,
	    IInventory par2IInventory, int par3, int par4, int par5) {
	super(par2IInventory, par3, par4, par5);
    }
    
}
