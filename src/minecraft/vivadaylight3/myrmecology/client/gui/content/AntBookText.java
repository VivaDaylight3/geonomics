package vivadaylight3.myrmecology.client.gui.content;

import java.awt.image.BufferedImage;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import vivadaylight3.myrmecology.api.util.Metadata;
import vivadaylight3.myrmecology.common.Reference;

public class AntBookText extends TextContent {
    
    private BufferedImage image;
    
    public String[] getContent(){

	String t7 = EnumChatFormatting.BOLD+"The Book Of";
	String t8 = "Myrmecology";
	String t9 = EnumChatFormatting.RESET+"by William Wheeler";
	String t10 = "First Draft Edition";
	
	String s2 = EnumChatFormatting.BOLD+""+EnumChatFormatting.UNDERLINE+"Contents"+EnumChatFormatting.RESET;
	String s2a = getNewLine(s2);
	String s3 = "1-2 : Introduction";
	String s4 = "3 : Hill Extraction";
	String s5 = "4-6 : Incubation";
	String s6 = "7 : Breeding";
	String s7 = "8 : Behaviours";
	
	String s10 = "Myrmecology is the study and breeding of ants. ";
	String s11 = "The aim of a myrmecologist is to capture, breed and enslave ants for his/her own bidding or scientific research. " + 
	"This is achieved by using four different types of ants; Queens, Drones, Workers and Larvae";
	
	String s12 = EnumChatFormatting.BOLD + "Queens: "+EnumChatFormatting.RESET+"Used for breeding ants into new species.";
	String s13 = EnumChatFormatting.BOLD + "Drones: "+EnumChatFormatting.RESET+"Mate with queen ants when breeding.";
	String s14 = EnumChatFormatting.BOLD + "Workers: "+EnumChatFormatting.RESET+"Perform tasks unique to the species.";
	String s15 = EnumChatFormatting.BOLD + "Larvae: "+EnumChatFormatting.RESET+"The result of breeding and hill extraction, can be incubated into one of the other ant types.";
	
	String s16 = "To capture some ants, you will need to extract them from an ant hill. Craft an extractor and the search the world for an ant hill, you will find these in every biome. Once you find one, break it with the extractor and some larvae will pop out!";
	
	String s17 = "Now that you have some ant larvae, you can incubate them into a mature ant. Craft a solarium, place it and power it with redstone. Once you place a larva in the top slot and a breeding item in the bottom slot, it will start to mature!";
	
	String s18 = "The resulting ant depends on what breeding item you add to the solarium.";
	
	String s19 = EnumChatFormatting.BOLD+"Queen: "+EnumChatFormatting.RESET+"Gold nugget";
	String s20 = getNewLine(s19);
	String s21 = EnumChatFormatting.BOLD+"Drone: "+EnumChatFormatting.RESET+"Bone"+getNewLine(s19);
	String s22 = getNewLine(s21);;
	String s23 = EnumChatFormatting.BOLD+"Worker: "+EnumChatFormatting.RESET+"Stick"+getNewLine(s20);
	
	String s24 = "A myrmecologist access new species by breeding ants. To start breeding, you need a formicarium. Place the formicarium and then put a drone in the bottom slot, a queen in the top slot and the species' breeding chamber in the slot in the middle. They will start breeding if they can breed together.";
			
	String s25 = "#NOT YET IMPLEMENTED# Each ant except ants you extract from ant hills and common ants can be put to work. " +
			"Mature an ant larva into a worker ant and then spawn it by right-clicking with it on the ground. If the ant's species has a behaviour it will begin its work.";
	
	return new String[]{newBookPage(true, t7, t8, t9, t10), newBookPage(true, s2, s2a, s3, s4, s5, s6, s7), newBookPage(true, s10, s11), newBookPage(true, s12, s13, s14, s15)
		,newBookPage(true, s16), newBookPage(true, s17, s18), newBookPage(false, s19, s20, s21, s22, s23), newBookPage(false, s24), newBookPage(false, s25)};
	
    }

}
