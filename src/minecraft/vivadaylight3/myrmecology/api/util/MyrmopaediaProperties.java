package vivadaylight3.myrmecology.api.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Nbt;

/**
 * Handles Myrmopaedia properties
 * 
 * @author samueltebbs
 * 
 */
public class MyrmopaediaProperties {

    public static ItemStack[] myrmopaediaIDs = new ItemStack[1000];

    /**
     * Initiates a new antopedia with NBT data
     * 
     * @param itemStack
     */
    public static void initiateMyrmopaedia(ItemStack itemStack) {

	Nbt.setTag(itemStack);

	for (int k = 0; k < Register.getAntList().size(); k++) {

	    Nbt.set(itemStack, ((ItemAnt) Register.getAntList().get(k))
		    .getSpeciesSubName(), false);

	}

    }

    /**
     * Gets a new vacant myrmopaedia ID
     * 
     * @param itemStack
     * @return
     */
    private static int newMyrmopaediaID(ItemStack itemStack) {

	for (int k = 0; k < myrmopaediaIDs.length; k++) {

	    if (myrmopaediaIDs[k] == null) {

		myrmopaediaIDs[k] = itemStack;

		return k;

	    }

	}

	return -1;

    }

    public static ItemStack getMyrmopaediaFromID(int id) {

	if (id >= 0) {

	    if (myrmopaediaIDs[id] != null) {

		return myrmopaediaIDs[id];

	    }

	}

	return null;

    }

    public static void clearMyrmopaediaID(int id) {

	myrmopaediaIDs[id] = null;

    }
/*
    @SideOnly(Side.CLIENT)
    public static void addAntToMyrmopaedia(ItemStack itemstack, ItemAnt ant,
	    Player parPlayer) {

	boolean bool = true;

	int bytes = ant.getSpeciesSubName().getBytes().length + 4;

	ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes);
	DataOutputStream dos = new DataOutputStream(bos);

	try {

	    dos.writeUTF(ant.getSpeciesSubName());
	    dos.writeInt(newMyrmopaediaID(itemstack));

	} catch (Exception ex) {

	    ex.printStackTrace();

	}

	Packet250CustomPayload packet = new Packet250CustomPayload();

	packet.channel = Reference.MOD_CHANNEL;
	packet.data = bos.toByteArray();
	packet.length = bos.size();

	if (PacketHandler.getSide() == Side.CLIENT) {

	    EntityClientPlayerMP player = (EntityClientPlayerMP) PacketHandler
		    .getSidedPlayer(parPlayer);
	    player.sendQueue.addToSendQueue(packet);

	}

    }*/

    /**
     * Gets all ants registered in the myrmopaedia (WIP)
     * 
     * @param itemStack
     * @return ArrayList<ItemAnt>
     */
    @Deprecated
    public static ArrayList<ItemAnt> getMyrmopaediaAnts(ItemStack itemStack) {

	ArrayList<ItemAnt> result = new ArrayList<ItemAnt>();

	for (int k = 0; k < Register.getAntList().size(); k++) {

	    if (Nbt.getBoolean(itemStack,
		    ((ItemAnt) Register.getAntList().get(k))
			    .getSpeciesSubName())) {

		result.add((ItemAnt) Register.getAntList().get(k));

	    }

	}

	return result;

    }

}
