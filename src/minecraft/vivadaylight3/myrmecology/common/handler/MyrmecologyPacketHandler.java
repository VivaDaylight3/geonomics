package vivadaylight3.myrmecology.common.handler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vivadaylight3.myrmecology.common.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

/**
 * Handles packet interaction
 * 
 * @author VivaDaylight3
 * 
 */

public class MyrmecologyPacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
	    Packet250CustomPayload packet, Player player) {

	if (packet.channel.equals(Reference.MOD_CHANNEL)) {

	    handlePacket(packet, player);

	}

    }
    
    private Side getSide(){
	
	return FMLCommonHandler.instance().getEffectiveSide();
	
    }
    
    private EntityPlayer getSidedPlayer(Player parPlayer){
	
	Side side = getSide();
	
	EntityPlayer player = null;
	
	if(side == Side.SERVER){
	    
	    player = (EntityPlayerMP) parPlayer;
	    
	}else if(side == Side.CLIENT){
	    
	    player = (EntityClientPlayerMP) parPlayer;
	    
	}
	
	return player;
	
    }

    public void handlePacket(Packet250CustomPayload parPacket, Player parPlayer) {
	
	Random random = new Random();
	int randomInt1 = random.nextInt();
	int randomInt2 = random.nextInt();

	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	DataOutputStream outputStream = new DataOutputStream(bos);
	try {
	        outputStream.writeInt(randomInt1);
	        outputStream.writeInt(randomInt2);
	} catch (Exception ex) {
	        ex.printStackTrace();
	}

	Packet250CustomPayload packet = new Packet250CustomPayload();
	packet.channel = "GenericRandom";
	packet.data = bos.toByteArray();
	packet.length = bos.size();
	
	if(getSidedPlayer(parPlayer) instanceof EntityClientPlayerMP){
	    
	   ((EntityClientPlayerMP) getSidedPlayer(parPlayer)).sendQueue.addToSendQueue(packet);
	    
	}
		
    }

}
