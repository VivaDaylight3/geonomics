package vivadaylight3.myrmecology.common.handler;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Url;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IConnectionHandler {

    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler,
	    INetworkManager manager) {

	Url url = new Url(Reference.VERSION_CHECK_URL);

	if (url.updateIsAvailable()) {

	    netHandler.getPlayer().addChatMessage(
		    Reference.CHAT_PREFIX + " Version "
			    + url.getLatestVersion() + " is now available!");
	    netHandler.getPlayer().addChatMessage(
		    "Update here: " + Reference.MOD_URL);

	}

    }

    @Override
    public String connectionReceived(NetLoginHandler netHandler,
	    INetworkManager manager) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server,
	    int port, INetworkManager manager) {
	// TODO Auto-generated method stub

    }

    @Override
    public void connectionOpened(NetHandler netClientHandler,
	    MinecraftServer server, INetworkManager manager) {
	// TODO Auto-generated method stub

    }

    @Override
    public void connectionClosed(INetworkManager manager) {
	// TODO Auto-generated method stub

    }

    @Override
    public void clientLoggedIn(NetHandler clientHandler,
	    INetworkManager manager, Packet1Login login) {
	// TODO Auto-generated method stub

    }

}
