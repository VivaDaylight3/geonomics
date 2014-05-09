package vivadaylight3.myrmecology.client;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import org.w3c.dom.Document;

import vivadaylight3.myrmecology.client.gui.content.Book;
import vivadaylight3.myrmecology.client.model.ModelAnt;
import vivadaylight3.myrmecology.client.renderer.ItemRendererAntChest;
import vivadaylight3.myrmecology.client.renderer.RenderAnt;
import vivadaylight3.myrmecology.client.renderer.RendererAntChest;
import vivadaylight3.myrmecology.client.renderer.RendererAntFarm;
import vivadaylight3.myrmecology.client.renderer.RendererIncubator;
import vivadaylight3.myrmecology.common.CommonProxy;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntCarpenter;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntDredger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntFungal;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntMound;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntOdourous;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntScavenger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntSprouter;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    public static int incubatorRenderID;
    public static int incubatorRenderPass;

    @Override
    public World getClientWorld() {
	return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    /**
     * Registers an entity renderer
     */
    public void addAntRenderer(Class<? extends EntityLiving> parClass,
	    RenderLiving render) {

	RenderingRegistry.registerEntityRenderingHandler(
		(Class<? extends Entity>) parClass, render);

    }

    @Override
    public void registerKeyBindings() {
	/*
	 * KeyBinding[] key = { new KeyBinding("Receive " +
	 * Reference.ANTBOOK_TITLE, Keyboard.KEY_L) }; boolean[] repeat = {
	 * false }; KeyBindingRegistry.registerKeyBinding(new
	 * KeyBindingHandler("Receive " + Reference.ANTBOOK_TITLE, key,
	 * repeat));
	 */

    }

    @Override
    public void registerRenderers() {

	addAntRenderer(EntityAntScavenger.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntCarpenter.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntOdourous.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntDredger.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntFungal.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntMound.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntSprouter.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntChest.class,
		new RendererAntChest());
	MinecraftForgeClient.registerItemRenderer(
		Item.getItemFromBlock(Register.blockAntChest), new ItemRendererAntChest());

	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIncubator.class,
		new RendererIncubator());

	/*
	 * MinecraftForgeClient.registerItemRenderer(
	 * Register.blockAntChest.blockID, new ItemRendererAntChest());
	 */

	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntFarm.class,
		new RendererAntFarm());
	/*
	 * MinecraftForgeClient.registerItemRenderer(
	 * Register.blockAntFarm.blockID, new ItemRendererAntChest());
	 */

    }

    public String getCurrentLanguage() {

	return FMLClientHandler.instance().getCurrentLanguage();

    }

    @Override
    public void readBooks() {

	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Book.initIcons();
	this.myrmopaedia = new Book(readDocument(Resources.BOOK_LOCATION
		+ "myrmopaedia.xml", dbf));

    }

    private Document readDocument(String path, DocumentBuilderFactory dbf) {

	InputStream in = Myrmecology.class.getResourceAsStream(path);
	try {
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.parse(in);
	    doc.getDocumentElement().normalize();
	    return doc;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return null;

    }

}