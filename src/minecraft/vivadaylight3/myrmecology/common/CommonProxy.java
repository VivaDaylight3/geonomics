package vivadaylight3.myrmecology.common;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import vivadaylight3.myrmecology.client.model.ModelAnt;
import vivadaylight3.myrmecology.client.renderer.ItemRendererAntChest;
import vivadaylight3.myrmecology.client.renderer.RenderAnt;
import vivadaylight3.myrmecology.client.renderer.RendererAntChest;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntCarpenter;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntDredger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntFungal;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntMound;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntOdourous;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntScavenger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntSprouter;
import vivadaylight3.myrmecology.common.handler.KeyBindingHandler;
import vivadaylight3.myrmecology.common.handler.PlayerTickHandler;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CommonProxy {
    
    public void addAntRenderer(Class<? extends EntityLiving> parClass,
	    RenderLiving render) {

    }
    
    public void registerKeyBindings() {

    }

    public void registerRenderers() {

    }

    public String getCurrentLanguage() {
	
	return null;

    }

}