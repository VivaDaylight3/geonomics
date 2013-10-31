package vivadaylight3.myrmecology.client.gui.content;

import java.awt.image.BufferedImage;

import vivadaylight3.myrmecology.common.Register;

import net.minecraft.util.EnumChatFormatting;

public class AntBookText extends TextContent {
    
    private BufferedImage image;
    
    public String[] getContent(){

	String t7 = EnumChatFormatting.BOLD+"The Book Of";
	String t8 = "Myrmecology";
	String t9 = EnumChatFormatting.RESET+"by William Wheeler";
	
	String s2 = EnumChatFormatting.BOLD+""+EnumChatFormatting.UNDERLINE+"Contents"+EnumChatFormatting.RESET;
	String s3 = "1-2 : Introduction";
	String s4 = "3 : Hill Extraction";
	String s5 = "4-6 : Incubation";
	String s6 = "7-8 : Breeding";
	String s7 = "9-10 : Behaviours";
	String s8 = "11-12 : Equipment";
	String s9 = "13-14 : Ant Properties";
	
	String s10 = "Myrmecology is the study and breeding of ants. ";
	String s11 = "The aim of a myrmecologist is to capture, breed and enslave ants for his/her own bidding or scientific research. " + 
	"This is achieved by using four different types of ants; Queens, Drones, Workers and Larvae";
	
	String s12 = EnumChatFormatting.BOLD + "Queens: "+EnumChatFormatting.RESET+"Used for breeding ants into new species.";
	String s13 = EnumChatFormatting.BOLD + "Drones: "+EnumChatFormatting.RESET+"Mate with queen ants when breeding.";
	String s14 = EnumChatFormatting.BOLD + "Workers: "+EnumChatFormatting.RESET+"Perform tasks unique to the species.";
	String s15 = EnumChatFormatting.BOLD + "Larvae: "+EnumChatFormatting.RESET+"The result of breeding and hill extraction, can be incubated into one of the other ant types.";
	
	String s16 = "To capture some ants, you will need to extract them from an ant hill. Craft an extractor and the search the world for an ant hill, you will find these in every biome. Once you find one, break it with the extractor and some larvae will pop out!";
	
	String s17 = "Now that you have some ant larvae, you can incubate them into a mature ant. Craft a solarium, place it and power it with redstone. Once you place a larva in the top slot and a breeding item in the bottom slot, it will start to mature!";
	
	String s18 = "";
	
	return new String[]{newBookPage(true, t7, t8, t9), newBookPage(true, s2, s3, s4, s5, s6, s7, s8, s9), newBookPage(true, s10, s11), newBookPage(true, s12, s13, s14, s15)
		,newBookPage(true, s16), newBookPage(true, s17), newBookPage(true, s18)};
	
    }

}
