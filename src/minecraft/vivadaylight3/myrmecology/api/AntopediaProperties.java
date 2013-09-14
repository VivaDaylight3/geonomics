package vivadaylight3.myrmecology.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.handler.MyrmecologyPacketHandler;
import vivadaylight3.myrmecology.common.lib.Nbt;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class AntopediaProperties {

    public static ItemStack[] antopediaIDs = new ItemStack[1000];

    public static void initiateAntopedia(ItemStack itemStack) {

	Nbt.setTag(itemStack);

	for (int k = 0; k < Register.getAntList().toArray().length; k++) {

	    Nbt.set(itemStack, ((ItemAnt) Register.getAntList().toArray()[k])
		    .getSpeciesSubName(), false);

	}

    }

    public static int newAntopediaID(ItemStack itemStack) {

	for (int k = 0; k < antopediaIDs.length; k++) {

	    if (antopediaIDs[k] == null) {

		antopediaIDs[k] = itemStack;

		return k;

	    }

	}

	return -1;

    }

    public static ItemStack getAntopediaFromID(int id) {

	if (antopediaIDs[id] != null) {

	    return antopediaIDs[id];

	} else {

	    return null;

	}

    }

    public static void clearAntopediaID(int id) {

	antopediaIDs[id] = null;

    }

    public static void addAntToAntopedia(ItemStack itemstack, ItemAnt ant,
	    Player parPlayer) {

	boolean bool = true;

	int bytes = ant.getSpeciesSubName().getBytes().length + 4;

	ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes);
	DataOutputStream dos = new DataOutputStream(bos);

	try {

	    dos.writeUTF(ant.getSpeciesSubName());
	    dos.writeInt(newAntopediaID(itemstack));

	} catch (Exception ex) {

	    ex.printStackTrace();

	}

	Packet250CustomPayload packet = new Packet250CustomPayload();

	packet.channel = Reference.MOD_CHANNEL;
	packet.data = bos.toByteArray();
	packet.length = bos.size();

	if (MyrmecologyPacketHandler.getSide() == Side.CLIENT) {

	    EntityClientPlayerMP player = (EntityClientPlayerMP) MyrmecologyPacketHandler
		    .getSidedPlayer(parPlayer);
	    player.sendQueue.addToSendQueue(packet);

	}

    }

    public static ArrayList<ItemAnt> getAntopediaAnts(ItemStack itemStack) {

	ArrayList<ItemAnt> result = new ArrayList<ItemAnt>();

	for (int k = 0; k < Register.getAntList().toArray().length; k++) {

	    if (Nbt.getBoolean(itemStack, ((ItemAnt) Register.getAntList()
		    .toArray()[k]).getSpeciesSubName())) {

		result.add((ItemAnt) Register.getAntList().toArray()[k]);

	    }

	}

	return result;

    }

}
