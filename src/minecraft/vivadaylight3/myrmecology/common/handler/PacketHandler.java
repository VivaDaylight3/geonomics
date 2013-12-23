package vivadaylight3.myrmecology.common.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.MyrmecologyPacket;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.api.util.MyrmopaediaProperties;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Nbt;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Handles packet interaction
 * 
 * @author VivaDaylight3
 * 
 */

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
	    Packet250CustomPayload packet, Player player) {

	if (packet.channel.equals(Reference.MOD_CHANNEL)) {

	    handleNBTPacket(packet, player);

	} else if (packet.channel.equals(Reference.MOD_CHANNEL_INCUBATOR)) {

	    handleTileEntityPacket(packet, player);

	}

    }

    public static Packet getTileEntityPacket(TileEntity te) {

	ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
	DataOutputStream dos = new DataOutputStream(bos);

	Packet250CustomPayload packet = new Packet250CustomPayload();

	int x = te.xCoord;
	int y = te.yCoord;
	int z = te.zCoord;

	try {
	    dos.writeInt(x);
	    dos.writeInt(y);
	    dos.writeInt(z);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	if (te instanceof TileEntityIncubator) {

	    for (int k = 0; k < ((TileEntityIncubator) te).getContents().length; k++) {

		try {
		    packet.writeItemStack(
			    ((TileEntityIncubator) te).getContents()[k], dos);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	    }

	}

	packet.channel = Reference.MOD_CHANNEL_INCUBATOR;
	packet.data = bos.toByteArray();
	packet.length = bos.size();
	packet.isChunkDataPacket = true;

	return packet;

    }

    public static void handleMyrmopaediaDropPacket(MyrmecologyPacket packet,
	    EntityClientPlayerMP player, IInventory inv) {

	ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
	ItemStack stack = null;

	try {
	    stack = packet.readItemStack(data);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	NBTTagCompound comp = null;

	try {
	    comp = packet.readNBTTagCompound(data);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	if (stack == null) {

	    return;

	}

	stack.setTagCompound(comp);
	stack.readFromNBT(comp);

	if (Environment.inventoryCanHold(stack, player.inventory.mainInventory,
		1)) {

	    Environment.addItemStackToInventory(stack,
		    player.inventory.mainInventory, 1, null);

	} else {

	    Environment.spawnItem(stack, player.worldObj, player.posX,
		    player.posY, player.posZ);

	}

    }

    private void handleTileEntityPacket(Packet250CustomPayload packet,
	    Player player) {

	ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
	int x = data.readInt();
	int y = data.readInt();
	int z = data.readInt();

	TileEntity te = Myrmecology.proxy.getClientWorld().getBlockTileEntity(
		x, y, z);

	if (te instanceof TileEntityIncubator) {

	    ItemStack[] stacks = new ItemStack[((TileEntityIncubator) te)
		    .getContents().length];

	    for (int k = 0; k < ((TileEntityIncubator) te).getContents().length; k++) {

		try {
		    stacks[k] = packet.readItemStack(data);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	    }

	    ((TileEntityIncubator) te).handlePacket(stacks);

	}

    }

    public static Side getSide() {

	return FMLCommonHandler.instance().getEffectiveSide();

    }

    @SideOnly(Side.CLIENT)
    public static EntityPlayer getSidedPlayer(Player parPlayer) {

	Side side = getSide();

	EntityPlayer player = null;

	if (side == Side.SERVER) {

	    player = (EntityPlayerMP) parPlayer;

	} else if (side == Side.CLIENT) {

	    player = (EntityClientPlayerMP) parPlayer;

	}

	return player;

    }

    /**
     * Guidance method only used as a guideline, shouldn't be called
     * 
     * @param packet
     * @param parPlayer
     */
    @Deprecated
    public void handlePacket(Packet250CustomPayload packet, Player parPlayer) {

	DataInputStream inputStream = new DataInputStream(
		new ByteArrayInputStream(packet.data));

	int randomInt1;
	int randomInt2;

	try {
	    randomInt1 = inputStream.readInt();
	    randomInt2 = inputStream.readInt();
	} catch (IOException e) {
	    e.printStackTrace();
	    return;
	}

    }

    public void handleNBTPacket(Packet250CustomPayload packet, Player player) {

	if (packet != null) {

	    DataInputStream inputStream = new DataInputStream(
		    new ByteArrayInputStream(packet.data));

	    String speciesName = null;
	    int antopediaID = 0;
	    Object antopedia;

	    try {

		speciesName = inputStream.readUTF();
		antopediaID = inputStream.readInt();

	    } catch (Exception ex) {

		ex.printStackTrace();
		return;

	    }

	    Nbt.set(MyrmopaediaProperties.getMyrmopaediaFromID(antopediaID),
		    speciesName, true);

	}

    }

}
