package vivadaylight3.myrmecology.client.gui.content;

import java.awt.image.BufferedImage;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Reference;

public class AntBookText extends TextContent {

    private BufferedImage image;

    public String[] getContent() {

	String s10 = "Myrmecology is the study and breeding of ants. ";
	String s11 = "The aim of a myrmecologist is to capture, breed and enslave ants for his/her own bidding or scientific research. "
		+ "This is achieved by using four different types of ants; Queens, Drones, Workers and Larvae";

	String s12 = "Queens are used for breeding new species. ";
	String s13 = "Drones mate with queens when breeding. ";
	String s14 = "Workers perform tasks unique to the species.  ";
	String s15 = "Larvae are the result from hill extraction and are incubated into mature ants.";

	String s16 = "To capture some ants, you will need to extract them from an ant hill. Craft an extractor and the search the world for an ant hill, you will find these in every biome. Once you find one, break it with the extractor and some larvae will pop out!";

	String s17 = "You can now incubate your larvae. Craft a solarium, place it and power it with redstone. Once you place a larva in the top slot and a breeding item in the bottom slot, it will start to mature!";

	String s18 = "The resulting ant depends on what breeding item you add to the solarium.";

	String s19 = "Queen: Gold nugget";
	String s20 = getNewLine(s19);
	String s21 = "Drone: Bone" + getNewLine(s19);
	String s22 = getNewLine(s21);
	String s23 = "Worker: Stick" + getNewLine(s20);

	String s24 = "A myrmecologist access new species by breeding ants. To start breeding, you need a formicarium. Place the formicarium and then put a drone in the bottom slot, a queen in the top slot and the species' breeding chamber in the slot in the middle.";

	String s25 = "Each ant except ants you extract from ant hills and common ants can be put to work. "
		+ "Mature an ant larva into a worker ant and then spawn it by right-clicking with it on the ground. If the ant's species has a behaviour it will begin its work.";

	return new String[] {
		newBookPage(false, s10, s11),
		newBookPage(false, s12, s13, s14, s15), newBookPage(false, s16),
		newBookPage(false, s17, s18),
		newBookPage(false, s19, s20, s21, s22, s23),
		newBookPage(false, s24), newBookPage(false, s25) };

    }

}
