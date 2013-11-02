package vivadaylight3.myrmecology.common.handler;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.Register;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerTickHandler implements ITickHandler {
    
    private final EnumSet<TickType> ticksToGet;

    public PlayerTickHandler(EnumSet<TickType> ticksToGet) {
	
	this.ticksToGet = ticksToGet;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

	playerTick((EntityPlayer)tickData[0]);
	
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
    
    public static void playerTick(EntityPlayer player){
	
	if(KeyBindingHandler.keyPressed){
	    
	    player.inventory.addItemStackToInventory(new ItemStack(Register.itemAntBook));
	    player.addStat(Register.achieveGetBook, 1);
	    KeyBindingHandler.keyPressed = false;
	    
	}
	
    }

}
