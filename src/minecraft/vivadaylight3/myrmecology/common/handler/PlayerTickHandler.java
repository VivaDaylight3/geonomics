package vivadaylight3.myrmecology.common.handler;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.TreeDictionary;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerTickHandler implements ITickHandler {

    private final EnumSet<TickType> ticksToGet;
    private static int ticks = 0;
    private static int tickInterval = 20;

    public PlayerTickHandler(EnumSet<TickType> ticksToGet) {

	this.ticksToGet = ticksToGet;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

	playerTick((EntityPlayer) tickData[0]);
	ticks++;

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	// TODO Auto-generated method stub

    }

    @Override
    public EnumSet<TickType> ticks() {

	return ticksToGet;
    }

    @Override
    public String getLabel() {
	// TODO Auto-generated method stub
	return "MyrmecologyPlayerTicks";
    }

    public static void playerTick(EntityPlayer player) {

    }

}
