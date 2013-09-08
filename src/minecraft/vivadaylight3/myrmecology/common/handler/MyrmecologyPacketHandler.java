package vivadaylight3.myrmecology.common.handler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vivadaylight3.myrmecology.api.AntopediaProperties;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Nbt;
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

	    handleNBTPacket(packet, player);

	}

    }
    
    public static Side getSide(){
	
	return FMLCommonHandler.instance().getEffectiveSide();
	
    }
    
    public static EntityPlayer getSidedPlayer(Player parPlayer){
	
	Side side = getSide();
	
	EntityPlayer player = null;
	
	if(side == Side.SERVER){
	    
	    player = (EntityPlayerMP) parPlayer;
	    
	}else if(side == Side.CLIENT){
	    
	    player = (EntityClientPlayerMP) parPlayer;
	    
	}
	
	return player;
	
    }

    public void handlePacket(Packet250CustomPayload packet, Player parPlayer) {
	
	DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int randomInt1;
        int randomInt2;
        
        try {
                randomInt1 = inputStream.readInt();
                randomInt2 = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        
        System.out.println(randomInt1 + " " + randomInt2);
        
    }
    
    public void handleNBTPacket(Packet250CustomPayload packet, Player player){
	
	if(packet != null){
	    
	    DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
	    
	    String speciesName = null;
	    int antopediaID = 0;
	    Object antopedia;
	    
	    try{
		
		speciesName = inputStream.readUTF();
		antopediaID = inputStream.readInt();		
		
	    }catch (Exception ex){
		
		ex.printStackTrace();
		return;
		
	    }
	    
	    Nbt.set(AntopediaProperties.getAntopediaFromID(antopediaID), speciesName, true);
	    
	   // AntopediaProperties.clearAntopediaID(antopediaID);
	    
	}
	
    }

}
