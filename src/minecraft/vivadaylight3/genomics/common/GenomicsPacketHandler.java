package vivadaylight3.genomics.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Handles packet interation
 * @author VivaDaylight3
 *
 */

public class GenomicsPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		if(packet.channel.equals(Genomics.MOD_CHANNEL)){
			
			handlePacket(packet);
			
		}
		
	}
	
	public void handlePacket(Packet250CustomPayload packet){
		
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			
		int randomInt1;
		int randomInt2;
	
		try{
			
			randomInt1 = inputStream.readInt();
			randomInt2 = inputStream.readInt();
			
		}catch(IOException e){
			
			e.printStackTrace();
			return;
			
		}
		
		System.out.println(randomInt1+""+randomInt2);
	
	}

}
