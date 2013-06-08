package vivadaylight3.myrmecology.client;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends vivadaylight3.myrmecology.common.CommonProxy {
        
        @Override
        public void registerRenderers() {
                MinecraftForgeClient.preloadTexture(ITEMS_PNG);
                MinecraftForgeClient.preloadTexture(BLOCK_PNG);
        }
        
}