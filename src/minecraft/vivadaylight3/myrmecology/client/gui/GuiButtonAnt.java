package vivadaylight3.myrmecology.client.gui;

import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.Metadata;

public class GuiButtonAnt extends GuiButtonSizeable {

    private ItemAnt ant;
    private Icon antIcon;
    private String antName;

    public GuiButtonAnt(int id, int posX, int posY, String string,
	    int parWidth, int parHeight, ItemAnt parAnt) {
	super(id, posX, posY, string, parWidth, parHeight);

	if (parAnt != null) {

	    this.ant = parAnt;
	    this.antIcon = this.ant.getIconFromDamage(Metadata.getMetaQueen());
	    this.antName = this.ant.getSpeciesName();

	}

    }

    public ItemAnt getAnt() {

	return this.ant;

    }

}
