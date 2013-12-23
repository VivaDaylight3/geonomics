package vivadaylight3.myrmecology.common;

import javax.xml.parsers.DocumentBuilderFactory;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

import org.w3c.dom.Document;

import vivadaylight3.myrmecology.client.gui.content.Book;

public class CommonProxy {

    public Book myrmopaedia;

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

    public World getClientWorld() {
	return null;
    }

    public void readBooks() {

    }

    private Document readDocument(String path, DocumentBuilderFactory dbf) {

	return null;

    }

}