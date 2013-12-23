package vivadaylight3.myrmecology.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.item.ItemAnt;

public interface IEntityAnt {

    /**
     * Gets the ItemAnt associated with this entity
     * 
     * @return ItemAnt
     */
    ItemAnt getAnt();

    /**
     * Holds the ant entity's inventory
     */
    ItemStack[] inventory = new ItemStack[1];

    /**
     * Sends a message to the player when left clicking with a myrmopaedia
     * 
     * @param player
     */
    void sendBehaviourErrorMessage(EntityPlayer player);

    double getPosX();

    double getPosY();

    double getPosZ();

    /**
     * Gets the number of passed ticks
     * 
     * @return
     */
    int getTicks();

    /**
     * Drops an itemStack
     * 
     * @param stack
     */
    void dropItemStack(ItemStack stack);

    /**
     * Moves the entity to the x, y and z
     * 
     * @param x
     * @param y
     * @param z
     */
    void moveEntityTo(double posX, double posY, double posZ);
}
